/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 4/15/24 : 3:20 PM
 */
package com.tallninja.shader;

import com.tallninja.exceptions.*;
import com.tallninja.utils.FileResourceUtils;
import org.tinylog.Logger;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private int vertexShaderId, fragmentShaderId;
    private final int programRef;

    public ShaderProgram(String vertexShaderPath, String fragmentShaderPath) {
        // Creates an empty program object, to which shader objects can be
        // attached, and returns a value by which it can be referenced
        programRef = glCreateProgram();
        if (programRef == GL_FALSE) {
            throw new ShaderProgramCreationException();
        }

        createVertexShaderFromFile(vertexShaderPath);
        createFragmentShaderFromFile(fragmentShaderPath);

        linkShaders();
        Logger.info("Successfully Linked shaders");
    }
    public ShaderProgram() {
        programRef = glCreateProgram();
        if (programRef == GL_FALSE) {
            throw new ShaderProgramCreationException();
        }
    }
    
    public void createVertexShaderFromFile(String vertexShaderPath) {
        String vertexShaderSource = FileResourceUtils.readFile(vertexShaderPath);
        createVertexShader(vertexShaderSource);
        Logger.info("Successfully created vertex shader [{}]", vertexShaderPath);
    }
    
    public void createFragmentShaderFromFile(String fragmentShaderPath) {
            String vertexShaderSource = FileResourceUtils.readFile(fragmentShaderPath);
            createFragmentShader(vertexShaderSource);
            Logger.info("Successfully created fragment shader [{}]", fragmentShaderPath);
    }

    public void createVertexShader(String vertexShaderSource) {
        vertexShaderId = createShader(vertexShaderSource, GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String fragmentShaderSource) {
        fragmentShaderId = createShader(fragmentShaderSource, GL_FRAGMENT_SHADER);
    }

    protected int createShader(String shaderSource, int shaderType) {
        // Creates an empty shader object, which is
        // used to store the source code of a shader
        // and returns a value by which it can be referenced
        int shaderRef = glCreateShader(shaderType);

        // Stores the source code in the string parameter shaderSource
        // in the shader object referenced by the parameter shaderRef
        glShaderSource(shaderRef, shaderSource);

        // Compile shader source code
        glCompileShader(shaderRef);

        // Check for errors
        int success = glGetShaderi(shaderRef, GL_COMPILE_STATUS); // get shader info on the compile status
        if (success == GL_FALSE) {
            int len = glGetShaderi(shaderRef, GL_INFO_LOG_LENGTH);
            String log = glGetShaderInfoLog(shaderRef, len);

            // free memory used to store shader program
            glDeleteShader(shaderRef);

            throw new ShaderCreationException(
                    shaderType == GL_VERTEX_SHADER
                            ? "vertex"
                            : "fragment",
                    log
            );
        }

        // Attach shader object to a program object
        glAttachShader(programRef, shaderRef);

        return shaderRef;
    }

    public void linkShaders() {
        // Links the vertex and fragment shaders previously attached to the program object
        //specified by the parameter programRef. Among other things, this process verifies
        //that any variables used to send data from the vertex shader to the fragment shader
        //are declared in both shaders consistently.
        glLinkProgram(programRef);

        // Check for errors while linking
        int success = glGetProgrami(programRef, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(programRef, GL_INFO_LOG_LENGTH);
            String log = glGetProgramInfoLog(programRef, len);

            // free memory used to store program
            glDeleteProgram(programRef);

            throw new ShaderLinkingException(log);
        }

        // Once the shader program has been linked, the compiled vertex and fragment shaders
        // can be freed up
        if (vertexShaderId != 0) {
            glDetachShader(programRef, vertexShaderId);
        }

        if (fragmentShaderId != 0) {
            glDetachShader(programRef, fragmentShaderId);
        }
    }

    public void bind() {
        glUseProgram(programRef);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (programRef != 0) {
            glDeleteProgram(programRef);
        }
    }

    public int getProgramRef() {
        return programRef;
    }
}
