/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 10:04 PM
 */
package com.tallninja.utils;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import static org.junit.jupiter.api.Assertions.*;


class FileResourceUtilsTest {

    @Test
    public void testReadFileFromResourceFolder() {
        String text = FileResourceUtils.readFile("test.txt");
        Logger.info(text);
    }

}