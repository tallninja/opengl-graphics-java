/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 8:44 PM
 */
package com.tallninja.exceptions;

import org.tinylog.Logger;

public class GLFWCreateWindowException extends RuntimeException {
    public GLFWCreateWindowException() {
        String message = "Unable to create GLFW window";
        Logger.error(message);
    }
}
