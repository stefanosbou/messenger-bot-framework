package io.github.stefanosbou.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

    // the configuration file is stored in the root of the class path as a .properties file
    private static final String CONFIGURATION_FILE = "resources/config.properties";

    private static final Properties properties;

    // use static initializer to read the configuration file when the class is loaded
    static {
        properties = new Properties();
        try (InputStream inputStream = Configuration.class.getResourceAsStream(CONFIGURATION_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + CONFIGURATION_FILE, e);
        }
    }

    public static Map<String, String> getConfiguration() {
        // ugly workaround to get String as generics
        Map temp = properties;
        Map<String, String> map = new HashMap<String, String>(temp);
        // prevent the returned configuration from being modified 
        return Collections.unmodifiableMap(map);
    }


    public static String getConfigurationValue(String key) {
        return properties.getProperty(key);
    }

    // private constructor to prevent initialization
    private Configuration() {
    }

}