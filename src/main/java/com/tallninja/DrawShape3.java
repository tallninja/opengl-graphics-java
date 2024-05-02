/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/2/24 : 2:56 PM
 */
package com.tallninja;

import com.tallninja.colors.Colors;
import com.tallninja.shader.OpenGLAttribute;
import com.tallninja.shader.OpenGLUniform;
import com.tallninja.shader.ShaderProgram;
import org.joml.Vector3d;

import static org.lwjgl.opengl.GL30.*;

public class DrawShape3 extends BaseWindow {
    private ShaderProgram shader;
    private int vaoRef;
    private OpenGLUniform<Vector3d> uTrans1, uTrans2, uColor1, uColor2;

    public DrawShape3() {
        super("Draw Shape 3", 800, 600, Colors.WHITE);
    }

    @Override
    public void setup() {
        shader = new ShaderProgram(
                "shaders/draw-shape-3.vert",
                "shaders/draw-shape-3.frag"
        );

        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] vertices = new float[] {
                 0.0f,  +0.25f, 0.0f,
                -0.25f, -0.25f, 0.0f,
                 0.25f, -0.25f, 0.0f,
        };

        OpenGLAttribute positions = new OpenGLAttribute("vec3", vertices);
        positions.bindVariable(shader, "aPos");

        // setup uniforms
        uTrans1 = new OpenGLUniform<>(
                "vec3",
                new Vector3d(new float[] {-0.5f, 0.0f, 0.0f})
        );
        uTrans1.bindVariable(shader, "uTrans");

        uTrans2 = new OpenGLUniform<>(
                "vec3",
                new Vector3d(new float[] {0.5f, 0.0f, 0.0f})
        );
        uTrans2.bindVariable(shader, "uTrans");

        uColor1 = new OpenGLUniform<>(
                "vec3",
                new Vector3d(new float[] {1.0f, 0.0f, 0.0f})
        );
        uColor1.bindVariable(shader, "uColor");

        uColor2 = new OpenGLUniform<>(
                "vec3",
                new Vector3d(new float[] {0.0f, 0.0f, 1.0f})
        );
        uColor2.bindVariable(shader, "uColor");
    }

    @Override
    public void update() {
        shader.bind();

        // Draw the first triangle
        uTrans1.update();
        uColor1.update();
        glDrawArrays(GL_TRIANGLES, 0, 3);

        // Draw the second triangle
        uTrans2.update();
        uColor2.update();
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }

    public static void main(String[] args) {
        var window = new DrawShape3();
        window.run();
    }
}
