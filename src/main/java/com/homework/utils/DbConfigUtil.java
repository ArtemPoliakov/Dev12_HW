package com.homework.utils;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public final class DbConfigUtil {
//    private static final Logger logger = LogManager.getLogger(DbConfigUtil.class);
    public static final String FILE_STREAM_ERROR_MSG = "FileStream doesn't filestream :(";
    public static final String DEFAULT_CONTENT_MEANING_A_PROBLEM = "UNDEFINED";
    public static final String PATH_TO_PROPERTIES_FOR_DB = "src/main/resources/flyway.properties";

    private DbConfigUtil(){}
    public static String getDbUrl(){
        Properties properties = new Properties();
        String url = DEFAULT_CONTENT_MEANING_A_PROBLEM;
        try(FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES_FOR_DB)){
            properties.load(fis);
            url = properties.getProperty("url");
        } catch(IOException e){
//            logger.error(FILE_STREAM_ERROR_MSG);
        }
        return url;
    }

    public static String getDbUser(){
        Properties properties = new Properties();
        String user = DEFAULT_CONTENT_MEANING_A_PROBLEM;
        try(FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES_FOR_DB)){
            properties.load(fis);
            user = properties.getProperty("user");
        } catch(IOException e){
//            logger.error(FILE_STREAM_ERROR_MSG);
        }
        return user;
    }

    public static String getDbPassword(){
        Properties properties = new Properties();
        String pass = DEFAULT_CONTENT_MEANING_A_PROBLEM;
        try(FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES_FOR_DB)){
            properties.load(fis);
            pass = properties.getProperty("password");
        } catch(IOException e){
//            logger.error(FILE_STREAM_ERROR_MSG);
        }
        return pass;
    }
}
