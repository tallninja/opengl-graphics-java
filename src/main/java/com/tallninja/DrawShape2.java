/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/2/24 : 12:24 PM
 */
package com.tallninja;

import com.tallninja.colors.Colors;
import com.tallninja.shader.OpenGLAttribute;
import com.tallninja.shader.ShaderProgram;

import static org.lwjgl.opengl.GL30.*;

public class DrawShape2 extends BaseWindow {
    private ShaderProgram shader;
    private int vaoRef;

    public DrawShape2() {
        super("Draw Shape 2", 800, 600, Colors.WHITE);
    }

    @Override
    public void setup() {
        shader = new ShaderProgram(
          "shaders/draw-shape-2.vert",
          "shaders/draw-shape-2.frag"
        );

        glPointSize(5);

        // create vertex array object
        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] positions = new float[] {
                +0.25f, +0.75f, 0.00f,
                +0.75f, +0.25f, 0.00f,
                +0.75f, -0.25f, 0.00f,
                +0.25f, -0.75f, 0.00f,
                -0.25f, -0.75f, 0.00f,
                -0.75f, -0.25f, 0.00f,
                -0.75f, +0.25f, 0.00f,
                -0.25f, +0.75f, 0.00f
        };
        OpenGLAttribute positionAttribute = new OpenGLAttribute("vec3", positions);
        positionAttribute.bindVariable(shader, "aPos");

        float[] colors = new float[] {
                +1.0f, +0.5f, 0.0f,
                +0.0f, +0.0f, 1.0f,
                +0.0f, +1.0f, 0.0f,
                +0.0f, +1.0f, 1.0f,
                +1.0f, +0.0f, 0.0f,
                +1.0f, +0.0f, 1.0f,
                +1.0f, +1.0f, 0.0f,
                +0.0f, +0.5f, 1.0f,
        };
        OpenGLAttribute colorAttribute = new OpenGLAttribute("vec3", colors);
        colorAttribute.bindVariable(shader, "aColor");
    }

    @Override
    public void update() {
        shader.bind();

        glBindVertexArray(vaoRef);
        glDrawArrays(GL_TRIANGLE_FAN, 0, 8);
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }

    public static void main(String[] args) {
        var window = new DrawShape2();
        window.run();
    }
}
