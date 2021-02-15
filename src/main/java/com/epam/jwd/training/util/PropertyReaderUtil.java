package com.epam.jwd.training.util;

import com.epam.jwd.training.entity.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);

    private static final Properties properties = new Properties();

    private static void loadProperties() {
        LOGGER.info("Loading properties from file");

        final String propertiesFileName = "application.properties";
        try (InputStream iStream = ClassLoader.getSystemClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(iStream);
        } catch (IOException e) {
            LOGGER.error("Exception has occurred: {}", e.toString());
        }
    }

    public static ApplicationProperties populateApplicationProperties() {
        LOGGER.info("Populating application properties");

        loadProperties();
        return new ApplicationProperties(properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"),
                Integer.valueOf(properties.getProperty("initPoolSize")),
                Integer.valueOf(properties.getProperty("maxPoolSize")),
                Integer.valueOf(properties.getProperty("incrementalPoolSize")));
    }

}
