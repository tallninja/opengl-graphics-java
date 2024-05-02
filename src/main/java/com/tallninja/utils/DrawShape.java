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
        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] vertices = new float[] {
                -0.5f, +0.5f, 0.0f, // top left
                +0.5f, +0.5f, 0.0f, // top right
                +0.5f, -0.5f, 0.0f, // bottom right
                -0.5f, -0.5f, 0.0f  // bottom left
        };

        OpenGLAttribute attribute = new OpenGLAttribute("vec3", vertices);
        attribute.bindVariable(shader, "aPos");
    }

    @Override
    public void update() {
        shader.bind();
        glDrawArrays(GL_LINE_LOOP, 0, 4);
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
