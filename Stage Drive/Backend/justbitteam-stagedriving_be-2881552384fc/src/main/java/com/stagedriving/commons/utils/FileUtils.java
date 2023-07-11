/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.commons.utils;

import java.io.*;

/**
 * @author manuel
 */
public class FileUtils {

    public static File writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new File(uploadedFileLocation);

    }

    public static ByteArrayOutputStream writeToOutputStream(InputStream uploadedInputStream) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return out;

    }

}
