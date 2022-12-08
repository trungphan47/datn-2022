package com.common;

import com.utility.PropertiesUtils;

public class GlobalVariables {

    //project path
    public static String PROJECT_PATH = System.getProperty("user.dir");
    public static String JSON_FILE_PATH = PROJECT_PATH + "/src/test/java/";
    public static String OUTPUT_PATH =  PROJECT_PATH + "/resources/output/";

    //Run parameters
    public static String THREAD_COUNT = PropertiesUtils.getPropValue("threadCount");
    public static String BROWSER = PropertiesUtils.getPropValue("browserName");

    //Defaul wait time
    public static final int WAIT_TIME_60 = 60;

    //Report data
    public static int TOTAL_TESTCASES = 0;
    public static int TOTAL_EXECUTED = 0;
    public static int TOTAL_PASSED = 0;
    public static int TOTAL_FAILED = 0;
    public static int TOTAL_SKIPPED = 0;

    //Gmail
    public static final String ALL_MAIL_FOLDER = "Inbox";

    //  ssh
    public static final int SSH_REMOTE_PORT = 22;
    public static final int LOCAL_PORT = 3306;
    public static final String REMOTE_HOST =  "allfilm.mediadnnb.codes";
    public static final String SSH_USERNAME = "root";

    //Database
//    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://" + REMOTE_HOST+ ":" + LOCAL_PORT+ "/db_main";
//    public static final String DB_URL = "jdbc:mysql://" + REMOTE_HOST+ ":" + LOCAL_PORT+ "/db_main?useSSL=false&serverTimezone=UTC";
    public static final int DB_REMOTE_PORT = 3306;
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";
}
