/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 8:32 PM
 */
package com.tallninja.exceptions;


import org.tinylog.Logger;

public class GLFWInitException extends RuntimeException {
    public GLFWInitException() {
        String message = "Unable to initialize GLFW";
        Logger.error(message);
    }
}
