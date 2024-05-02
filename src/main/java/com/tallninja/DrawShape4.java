/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/2/24 : 3:42 PM
 */
package com.tallninja;

import com.tallninja.colors.Colors;
import com.tallninja.shader.OpenGLAttribute;
import com.tallninja.shader.OpenGLUniform;
import com.tallninja.shader.ShaderProgram;
import org.joml.Vector3d;
import org.tinylog.Logger;

import static org.lwjgl.opengl.GL30.*;

public class DrawShape4 extends BaseWindow {
    private ShaderProgram shader;
    private int vaoRef;
    private OpenGLUniform<Vector3d> uTrans, uColor;

    public DrawShape4() {
        super("Draw Shape 4", 800, 600, Colors.ORANGE);
    }

    @Override
    public void setup() {
        shader = new ShaderProgram(
                "shaders/draw-shape-4.vert",
                "shaders/draw-shape-4.frag"
        );

        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] vertices = new float[] {
                -1.000f, 0.00f, 0.0f,
                -0.875f, 0.25f, 0.0f,
                -0.750f, 0.00f, 0.0f,
        };
        var startPos = new OpenGLAttribute("vec3", vertices);
        startPos.bindVariable(shader, "aPos");

        // set up the uniforms
        uTrans = new OpenGLUniform<>(
                "vec3",
                new Vector3d(new float[] { 0.01f, 0.0f, 0.0f })
        );
        uTrans.bindVariable(shader, "uTrans");

        // set up the uniforms
        uColor = new OpenGLUniform<>(
                "vec3",
                new Vector3d(new float[] { 0.0f, 0.0f, 0.0f })
        );
        uColor.bindVariable(shader, "uColor");
    }

    @Override
    public void update() {
        Vector3d translated = uTrans.getData().add(0.01, 0.0, 0.0);

        if (translated.x > 2.0) {
            Logger.info(translated.x);
            translated.x = -0.25;
        }

        uTrans.setData(translated);

//        Logger.info(uTrans.getData().x);

        shader.bind();
        glBindVertexArray(vaoRef);
        uTrans.update();
        uColor.update();
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }

    public static void main(String[] args) {
        var window = new DrawShape4();
        window.run();
    }
}
