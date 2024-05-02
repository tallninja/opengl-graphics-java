/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/2/24 : 2:28 PM
 */
package com.tallninja.shader;

import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector4d;

import static org.lwjgl.opengl.GL20.*;

public class OpenGLUniform<T> {

    // int | bool | float | vec2 | vec3 | vec4
    private final String dataType;
    private final T data;
    private int[] genBufferResults = new int[1];
    private int uniformRef;

    public OpenGLUniform(String dataType, T data) {
        this.dataType = dataType;
        this.data = data;
    }

    public void bindVariable(ShaderProgram shader, String variableName) {
        // Try and locate the uniform variable
        uniformRef = glGetUniformLocation(shader.getProgramRef(), variableName);
    }

    public void update() {
        if (uniformRef == -1) return;

        switch (dataType) {
            case ("int"), ("bool")  -> glUniform1i(uniformRef, (Integer) data);
            case ("float")          -> glUniform1f(uniformRef, (Float) data);
            case ("vec2")           -> {
                Vector2d vec = (Vector2d) data;
                glUniform2f(uniformRef, (float) vec.x, (float) vec.y);
            }
            case ("vec3")           -> {
                Vector3d vec = (Vector3d) data;
                glUniform3f(uniformRef, (float) vec.x, (float) vec.y, (float) vec.z);
            }
            case ("vec4")           -> {
                Vector4d vec = (Vector4d) data;
                glUniform4f(uniformRef, (float) vec.x, (float) vec.y, (float) vec.z, (float) vec.w);
            }
        }
    }

    public int getUniformRef() {
        return uniformRef;
    }
}
