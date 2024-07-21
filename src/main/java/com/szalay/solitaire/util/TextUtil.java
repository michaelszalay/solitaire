package com.szalay.solitaire.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class TextUtil {

    public static String getText(final String key) {
        try {
            return ResourceBundle.getBundle("Texts").getString(key);
        }
        catch (MissingResourceException mre) {
            return "#" + key + "#";
        }
    }
}
