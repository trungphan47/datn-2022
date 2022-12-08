package com.utility;


import com.aventstack.extentreports.ExtentTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.util.Properties;

import static com.common.GlobalVariables.ALL_MAIL_FOLDER;

public class EmailActions extends Utility {

    private Store store;
    private Folder folder, folderInbox;
    private String emailPwd;
    private String emailUser;

    public EmailActions() {
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props, null);
            store = session.getStore("imaps");
            emailPwd = System.getenv("emailPwd");
            emailUser = System.getenv("emailUser");
            store.connect("imap.gmail.com", emailUser, emailPwd);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void openConnection() {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(props, null);
        try {
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", emailUser, emailPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {

        if (store.isConnected()) {
            try {
                System.out.println("Closing connection");
                store.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public Message getEmailObject(String fromEmail, String toEmail, String subject, boolean unRead, ExtentTest logTest){
        try {
            boolean isFrom = false, isTo = false;
            Message[] messages;
            String actualSubject;
            int index;
            if (!emailActions.store.isConnected()) {
                emailActions.openConnection();
            }

            folder = store.getFolder(ALL_MAIL_FOLDER);
            folder.open(Folder.READ_WRITE);

            if (unRead == true) {
                messages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
                // Only check 30 last unread emails
            } else {
                messages = folder.getMessages();
                // Only check 30 last emails
            }
            int totalMsg = messages.length;
            if (totalMsg < 1) return null;
            if (totalMsg > 100) index = 100;
            else index = totalMsg;

            for (int i = totalMsg - 1; i >= totalMsg - index; i--) {
                actualSubject = messages[i].getSubject().trim();
                // Check Subject
                if (actualSubject.contains(subject.trim())) {
                    // Check From
                    if (fromEmail != null) {
                        Address[] fromEmails = messages[i].getFrom();
                        for (Address from : fromEmails) {
                            if (((InternetAddress) from).getAddress().equalsIgnoreCase(fromEmail)) {
                                isFrom = true;
                                break;
                            }
                        }
                    } else {
                        isFrom = true;
                    }

                    // Check To
                    Address[] toEmails = messages[i].getRecipients(Message.RecipientType.TO);
                    for (Address to : toEmails) {
                        if (((InternetAddress) to).getAddress().equals(toEmail)) {
                            isTo = true;
                            break;
                        }
                    }

                    if (isFrom && isTo)
                        return messages[i];
                }
            }

            return null;
        } catch (MessagingException ex) {
            log4j.info("Retry connection... with Exception: " + ex);
            return null;
        } catch (Exception ex) {
            log4j.error("getEmailObject method - ERROR: " + ex);
            TestReporter.logException(logTest, "getEmailObject method - ERROR: ", ex);
        }

        return null;
    }

    public Document getEmailContent(Message message, ExtentTest logTest) {
        try {
            Object emailContent = message.getContent();
            String body = "";
            if (emailContent instanceof String) {
                return (Document) Jsoup.parse((String) emailContent);
            } else if (emailContent instanceof Multipart) {
                Multipart multipart = (Multipart) emailContent;
                for (int x = 0; x < multipart.getCount(); x++) {
                    BodyPart bodyPart = multipart.getBodyPart(x);
                    Object content = bodyPart.getContent();
                    if (content instanceof String) {
                        body = body + content.toString();
                    } else if (content instanceof MimeMultipart) {
                        MimeMultipart mimeMultipart = (MimeMultipart) bodyPart.getContent();
                        for (int j = 0; j < mimeMultipart.getCount(); j++) {
                            body = body + mimeMultipart.getBodyPart(j).getContent().toString();
                        }
                    }
                }
            }

            return (Document) Jsoup.parse(body);
        } catch (Exception e) {
            log4j.error("getEmailContent method - ERROR: ", e);
            TestReporter.logException(logTest, "getEmailContent method - ERROR: ", e);
        }

        return null;
    }

    public synchronized Message verifyEmailExist(String fromEmail, String toEmail, String subject, boolean unRead, ExtentTest logTest) {
        try {
            log4j.info("Verify email exists in Inbox...start");

            // Refresh inbox maximum = 5 times.
            Message message;
            for (int i = 1; i <= 5; i++) {
                log4j.debug("Repeat time: #" + i);
                sleep(10); // Refresh inbox after each 10 seconds
                message = getEmailObject(fromEmail, toEmail, subject, unRead, logTest);
                if (message != null) {
                    TestReporter.logPass(logTest, "Email tồn tại trong hộp thư: " +
                            "<br>From: " + fromEmail +
                            "<br>To: " + toEmail +
                            "<br>Subject: " + subject);
                    return message;
                }
            }

            TestReporter.logFail(logTest, "Email không tồn tại trong hộp thư: " +
                    "<br>From: " + fromEmail +
                    "<br>To: " + toEmail +
                    "<br>Subject: " + subject);
        } catch (Exception e) {
            log4j.error("verifyEmailExist method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyEmailExist method - ERROR: ", e);
        }

        return null;
    }
}
