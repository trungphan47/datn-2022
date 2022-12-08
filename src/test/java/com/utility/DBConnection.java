package com.utility;

import com.aventstack.extentreports.ExtentTest;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import static com.common.GlobalVariables.*;

public class DBConnection extends Utility{

    private static String user;
    private static String pwd;
    private static Connection conn;
    private static Statement statement;
    private static Session session;

    //region <get set methods>
    public static Connection getConn(){
        return conn;
    }

    public static void setConn(Connection conn){
        DBConnection.conn = conn;
    }

    public static Statement getStatement(){
        return statement;
    }

    public static void setStatement(Statement setStatementValue){
        DBConnection.statement = setStatementValue;
    }

    //endregion <get set methods>

    //region <common methods>

    public static void sshGo(ExtentTest logTest){
        if(session != null){
            try
            {
                JSch jsch = new JSch();
                session = jsch.getSession(SSH_USERNAME, REMOTE_HOST, SSH_REMOTE_PORT);
                session.setConfig("StrictHostKeyChecking", "no");

                String privateKey = PROJECT_PATH + "/id_rsa";
                jsch.addIdentity(privateKey);

                log4j.info("Establishing SSH Connection...");
                session.connect();
                session.setPortForwardingL(LOCAL_PORT, REMOTE_HOST, DB_REMOTE_PORT);
            }
            catch(Exception e){
                log4j.error("Establishing SSH Connection - ERROR: ", e);
                TestReporter.logException(logTest, "Establishing SSH Connection - ERROR: ", e);
            }
        }
    }

    public static void initialDatabaseAccess(ExtentTest logTest){
        try {
            log4j.debug("Get database access starts ...");
            user = DB_USERNAME;
            pwd = DB_PASSWORD;
        } catch (Exception e){
            log4j.error("initialDatabaseAccess method - ERROR: ", e);
            TestReporter.logException(logTest, "initialDatabaseAccess method - ERROR: ", e);
        }
    }

    public static void initDBConnection(ExtentTest logTest) {
        try {
            sshGo(logTest);

            log4j.debug("initDBConnection method starts ... ");
            TestReporter.logInfo(logTest, "initDBConnection method ... ");

            log4j.info("Close current connection");
            closeDBConnection(logTest, conn);

            log4j.info("Get DB access");
            initialDatabaseAccess(logTest);

            log4j.info("Register JDBC driver");
            Class.forName(JDBC_DRIVER);

            log4j.info(DB_URL);
            Properties pros = new Properties();
            pros.setProperty("user", user);
            pros.setProperty("password", pwd);
            pros.setProperty("socketTimeout", "0");
            pros.setProperty("tcpKeepAlive", "true");

            setConn(DriverManager.getConnection(DB_URL, pros));

        } catch (Exception e){
            log4j.error("initDBConnection method - ERROR: ", e);
            TestReporter.logException(logTest, "initDBConnection method - ERROR: ", e);
        }
    }

    public static void initStatement(ExtentTest logTest, Connection conn){
        try {
            log4j.debug("initStatement method starts ...");
            TestReporter.logInfo(logTest, "initStatement method - starts");

            log4j.info("Create statement");
            TestReporter.logInfo(logTest, "Create statement");
            statement = conn.createStatement();

            log4j.info("Set statement value for DB connection class");
            setStatement(statement);
        } catch (Exception e){
            log4j.error("initStatement method - ERROR: ", e);
            TestReporter.logException(logTest, "initStatement method - ERROR: ", e);
        }
    }

    public static void closeStatement(ExtentTest logTest, Statement statement){
        try {
            log4j.debug("Close statement starts ... ");
            TestReporter.logInfo(logTest, "Close statement");

            if(statement != null) statement.close();

        } catch (Exception e){
            log4j.error("closeStatement method - ERROR: ", e);
            TestReporter.logException(logTest, "closeStatement method - ERROR: ", e);
        }
    }

    public static void closeDBConnection(ExtentTest logTest, Connection conn){
        try {
            log4j.debug("closeDBConnection starts ... ");
            TestReporter.logInfo(logTest, "closeDBConnection");

            if(conn != null) conn.close();

        } catch (Exception e){
            log4j.error("closeDBConnection method - ERROR: ", e);
            TestReporter.logException(logTest, "closeDBConnection method - ERROR: ", e);
        }
    }

    //end region <common methods>

}
