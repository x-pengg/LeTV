package com.maybe.live.kit;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author: Tate
 * @date: 2016/5/10 16:11
 */
public class ResourceKit {

    private static final Logger log = LoggerFactory.getLogger(ResourceKit.class);
    public static final String MSG_PATH = "i18n/message";
    static Table<String, Locale, ResourceBundle> resTable = HashBasedTable.create();

    public static String getStringForLocale(String bundle, Locale locale, String key) {
        Preconditions.checkArgument(locale != null);
        Preconditions.checkArgument(bundle != null);
        ResourceBundle res = resTable.get(locale, bundle);
        if (res == null) {
            res = ResourceBundle.getBundle(bundle, locale);
            if (res != null) {
                resTable.put(bundle, locale, res);
                return res.getString(key);
            }
        }
        return null;
    }

    public static String getErrorMsg(String key) {
        try {
            return getStringForLocale(MSG_PATH, new Locale("zh_CN"), key);
        } catch (Exception e) {
            log.info(MSG_PATH);
            return getStringForLocale("/" + MSG_PATH, new Locale("zh_CN"), key);
        }

    }

    public static String getErrorMsg(String key, String local) {
        try {
            return getStringForLocale(MSG_PATH, new Locale(local), key);
        } catch (Exception e) {
            log.info(MSG_PATH);
            return getStringForLocale("/" + MSG_PATH, new Locale(local), key);
        }

    }


}
