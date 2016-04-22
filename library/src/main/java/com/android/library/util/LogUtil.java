package com.android.library.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by liyanan on 16/4/22.
 */
public class LogUtil {
    public static String getLog(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        String str = "";
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            Throwable cause = throwable.getCause();
            while (cause != null) {
                cause.printStackTrace(pw);
                cause = cause.getCause();
            }
            str = sw.toString();
            pw.close();
            sw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
