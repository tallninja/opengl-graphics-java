/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 4/15/24 : 4:11 PM
 */
package com.tallninja.exceptions;

public class ShaderLinkingException extends RuntimeException {
    public ShaderLinkingException(String log) {
        super("[ERROR] Failed to link shader programs.\n\t" + log);
    }
}
