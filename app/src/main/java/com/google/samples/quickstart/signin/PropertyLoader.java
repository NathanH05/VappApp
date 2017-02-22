package com.google.samples.quickstart.signin;

/**
 * Created by nathanhampshire on 19/02/17.
 */

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyLoader {

    public static Properties loadProperties(String name, ClassLoader loader) throws Exception {
        if (name.startsWith("/"))
            name = name.substring(1);
        if (name.endsWith(SUFFIX))
            name = name.substring(0, name.length() - SUFFIX.length());
        Properties result = new Properties();
        InputStream in = null;
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();
        if (LOAD_AS_RESOURCE_BUNDLE) {
            name = name.replace('/', '.');
            ResourceBundle rb = ResourceBundle.getBundle(name, Locale.getDefault(), loader);
            for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
                result.put((String) keys.nextElement(), rb.getString((String) keys.nextElement()));
            }
        } else {
            name = name.replace('.', '/');
            if (!name.endsWith(SUFFIX))
                name = name.concat(SUFFIX);
            in = loader.getResourceAsStream(name);
            if (in != null) {
                result = new Properties();
                result.load(in); // can throw IOException
            }
        }
        in.close();
        return result;
    }

    public static Properties loadProperties(final String name) throws Exception {
        return loadProperties(name, Thread.currentThread().getContextClassLoader());
    }

    private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;

    private static final String SUFFIX = ".properties";

}
