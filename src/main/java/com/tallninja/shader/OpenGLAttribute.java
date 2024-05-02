/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/2/24 : 10:36 AM
 */
package com.tallninja.shader;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

// Manages attribute data and related tasks
// The information managed by the class includes the type of data
// (int, float, vec2, vec3, or vec4), an array containing the data,
// and the reference of the vertex buffer where the data is stored.
// The two main tasks handled by the class will be storing the array
// of data in a vertex buffer and associating the vertex buffer to a
// shader variable in a given program
public class OpenGLAttribute {

    // Type of data
    // int | float | vec2 | vec3 | vec4
    private final String dataType;

    // Array containing the data
    private float[] data;

    // Vertex buffer reference
    private final int bufferRef;

    // Store results of generating buffers
    private int result[] = new int[1];


    public OpenGLAttribute(String dataType, float[] data) {
        this.dataType = dataType;
        this.data = data;
        // Generate a single buffer object
        this.bufferRef = glGenBuffers();
        uploadVertexData();
    }

    // Stores the data array in a vertex data
    private void uploadVertexData() {
        // select type of buffer to be used
        glBindBuffer(GL_ARRAY_BUFFER, bufferRef);
        // store data in currently bound buffer
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    // Bind this attribute to the shader program variable name
    public void bindVariable(ShaderProgram shader, String variableName) {
        int variableRef = glGetAttribLocation(shader.getProgramRef(), variableName);

        if (variableRef == -1) return;

        glBindBuffer(GL_ARRAY_BUFFER, bufferRef);

        // specify how data will be read
        // from the currently bound buffer
        // into the specified variable
        switch (dataType) {
            case ("int") -> glVertexAttribPointer(variableRef, 1, GL_INT,
                    false, 0, 0);
            case ("float") -> glVertexAttribPointer(variableRef, 1, GL_FLOAT,
                    false, 0, 0);
            case ("vec2") -> glVertexAttribPointer(variableRef, 2, GL_FLOAT,
                    false, 0, 0);
            case ("vec3") -> glVertexAttribPointer(variableRef, 3, GL_FLOAT,
                    false, 0, 0);
            case ("vec4") -> glVertexAttribPointer(variableRef, 4, GL_FLOAT,
                    false, 0, 0);
            default -> throw new RuntimeException("Attribute " + variableName +
                    " has unknown type " + dataType);

        }

        // Indicate that data will be streamed to this
        // variable from a buffer
        glEnableVertexAttribArray(variableRef);
    }

    public void cleanup() {

    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }
}
