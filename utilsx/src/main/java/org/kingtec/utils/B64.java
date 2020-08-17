package org.kingtec.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class B64 {
    public static String encode(String string) {
        try {
            byte[] data = string.getBytes(StandardCharsets.UTF_8);
            return Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decode(String base64) {
        try {
            // Sending side
            byte[] data = Base64.decode(base64, Base64.DEFAULT);
            return new String(data, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
