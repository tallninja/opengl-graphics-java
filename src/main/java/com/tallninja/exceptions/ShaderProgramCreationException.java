/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 4/15/24 : 3:48 PM
 */
package com.tallninja.exceptions;

public class ShaderProgramCreationException extends RuntimeException {
    public ShaderProgramCreationException() {
        super("[ERROR] Failed to create shader program.");
    }
}
