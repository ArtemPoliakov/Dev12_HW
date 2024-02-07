package com.homework.utils;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public final class DbConfigUtil {
    public static final String FILE_STREAM_ERROR_MSG = "FileStream doesn't filestream :(";
    public static final String DEFAULT_CONTENT_MEANING_A_PROBLEM = "UNDEFINED";
    private static final String PATH_TO_TEST_PROPERTIES_FOR_DB = "src/test/resources/flyway.properties";
    private static final String PATH_TO_REAL_DB_PROPERTIES = "src/main/resources/flyway.properties";

    private DbConfigUtil(){}
    public static String getDbUrl(PropertyType propertyType){
        String path = getPathToProperties(propertyType);
        Properties properties = new Properties();
        String url = DEFAULT_CONTENT_MEANING_A_PROBLEM;
        try(FileInputStream fis = new FileInputStream(path)){
            properties.load(fis);
            url = properties.getProperty("url");
        } catch(IOException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getDbUser(PropertyType propertyType){
        String path = getPathToProperties(propertyType);
        Properties properties = new Properties();
        String user = DEFAULT_CONTENT_MEANING_A_PROBLEM;
        try(FileInputStream fis = new FileInputStream(path)){
            properties.load(fis);
            user = properties.getProperty("user");
        } catch(IOException e){
            e.printStackTrace();
        }
        return user;
    }

    public static String getDbPassword(PropertyType propertyType){
        String path = getPathToProperties(propertyType);
        Properties properties = new Properties();
        String pass = DEFAULT_CONTENT_MEANING_A_PROBLEM;
        try(FileInputStream fis = new FileInputStream(path)){
            properties.load(fis);
            pass = properties.getProperty("password");
        } catch(IOException e){
            e.printStackTrace();
        }
        return pass;
    }

    private static String getPathToProperties(PropertyType propertyType) {
        String path = "";
        if(PropertyType.REAL_MODE.equals(propertyType)){
            path = PATH_TO_REAL_DB_PROPERTIES;
        } else{
            path = PATH_TO_TEST_PROPERTIES_FOR_DB;
        }
        return path;
    }

    public enum PropertyType {
        TEST_MODE,
        REAL_MODE
    }
}
