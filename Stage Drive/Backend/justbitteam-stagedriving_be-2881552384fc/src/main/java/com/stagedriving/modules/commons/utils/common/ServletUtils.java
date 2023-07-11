/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.utils.common;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class ServletUtils {

    public static String readFromInput(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                return null;
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        String toUtf8 = stringBuilder.toString();
        return utf8Convert(toUtf8);
    }

    public static String utf8Convert(String utf8String) {
        String ret = "";
        try {
            byte[] bytes = new byte[utf8String.length()];
            for (int i = 0; i < utf8String.length(); i++) {
                bytes[i] = (byte) utf8String.charAt(i);
            }
            ret = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServletUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
