package ru.textanalysis.common.util;

import java.io.IOException;

public class Properties {
    private static final String PROPERTIES_FILE_NAME = "properties.properties";
    private static java.util.Properties properties = null;

    public static synchronized java.util.Properties getProperties() {
        try {
            if (null == properties) {
                properties = new java.util.Properties();
                properties.load(Properties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
                if (properties.isEmpty()) {
                    throw new RuntimeException(PROPERTIES_FILE_NAME + " is empty");
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return properties;
    }

    public static synchronized String getProperty(String string) {
        return getProperties().getProperty(string);
    }

    public static synchronized String getProperty(String string, String defaultValue) {
        String result = getProperty(string);
        return result == null ? defaultValue : result;
    }
}
