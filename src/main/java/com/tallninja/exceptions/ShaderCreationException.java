/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 4/15/24 : 3:52 PM
 */
package com.tallninja.exceptions;

public class ShaderCreationException extends RuntimeException {
    public ShaderCreationException(String shaderType, String log) {
        super("[ERROR] Failed to create " + shaderType + " shader.\n\t" + log);
    }
}
