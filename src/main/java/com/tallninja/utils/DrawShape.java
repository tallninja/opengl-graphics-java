/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/2/24 : 9:31 AM
 */
package com.tallninja.utils;

import com.tallninja.BaseWindow;
import com.tallninja.colors.Color;
import com.tallninja.shader.OpenGLAttribute;
import com.tallninja.shader.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class DrawShape extends BaseWindow {
    private ShaderProgram shader;
    private int vaoSquare, vaoTriangle;

    public DrawShape() {
        super("Draw Shape", 800, 600, Color.fromHex("#000000"));
    }

    @Override
    public void setup() {
        shader = new ShaderProgram(
                "shaders/draw-shape.vert",
                "shaders/draw-shape.frag"
        );

        glLineWidth(3);

        // Set up vertex array object
        vaoSquare = glGenVertexArrays();
        glBindVertexArray(vaoSquare);

        float[] squareVertices = new float[] {
                -0.8f, +0.8f, 0.0f, // top left
                -0.3f, +0.8f, 0.0f, // top right
                -0.3f, +0.3f, 0.0f, // bottom right
                -0.8f, +0.3f, 0.0f  // bottom left
        };

        OpenGLAttribute squareAttribute = new OpenGLAttribute("vec3", squareVertices);
        squareAttribute.bindVariable(shader, "aPos");

        // Set up vertex array object
        vaoTriangle = glGenVertexArrays();
        glBindVertexArray(vaoTriangle);

        float[] triangleVertices = new float[] {
                +0.5f,  -0.3f, 0.0f, // top
                +0.25f, -0.8f, 0.0f, // left
                +0.75f, -0.8f, 0.0f, // right
        };

        OpenGLAttribute triangleAttribute = new OpenGLAttribute("vec3", triangleVertices);
        triangleAttribute.bindVariable(shader, "aPos");
    }

    @Override
    public void update() {
        shader.bind();

        // Draw square
        glBindVertexArray(vaoSquare);
        glDrawArrays(GL_LINE_LOOP, 0, 4);

        // Draw triangle
        glBindVertexArray(vaoTriangle);
        glDrawArrays(GL_LINE_LOOP, 0, 3);
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }

    public static void main(String[] args) {
        var window = new DrawShape();
        window.run();
    }
}
