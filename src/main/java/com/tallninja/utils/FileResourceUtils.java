/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 9:52 PM
 */
package com.tallninja.utils;

import org.tinylog.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileResourceUtils {

    public static String readFile(String filePath) {
        URL fileUrl = FileResourceUtils.class.getResource("/" + filePath);
        try {
            if (fileUrl != null) {
                byte[] bytes = Files.readAllBytes(Paths.get(fileUrl.toURI()));
                return new String(bytes);
            } else {
                Logger.error("File does not exist");
            }
        } catch (IOException | URISyntaxException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }
}
