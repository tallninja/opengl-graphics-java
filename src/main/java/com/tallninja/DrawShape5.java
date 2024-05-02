/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/2/24 : 5:03 PM
 */
package com.tallninja;

import com.tallninja.colors.Color;
import com.tallninja.colors.Colors;
import com.tallninja.shader.OpenGLAttribute;
import com.tallninja.shader.OpenGLUniform;
import com.tallninja.shader.ShaderProgram;
import com.tallninja.time.EngineTime;
import org.joml.Vector3d;
import org.tinylog.Logger;

import static org.lwjgl.opengl.GL30.*;

public class DrawShape5 extends BaseWindow {
    private ShaderProgram shader;
    private int vaoRef;
    private OpenGLUniform<Vector3d> uTrans, uColor;

    public DrawShape5() {
        super("Draw Shape 5", 600, 600, Colors.BLACK);
    }

    @Override
    public void setup() {
        shader = new ShaderProgram(
                "shaders/draw-shape-5.vert",
                "shaders/draw-shape-5.frag"
        );

        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] vertices = new float[] {
                 0.0f, +0.1f, 0.0f,
                +0.1f, -0.1f, 0.0f,
                -0.1f, -0.1f, 0.0f,
        };
        var aPos = new OpenGLAttribute("vec3", vertices);
        aPos.bindVariable(shader, "aPos");

        uColor = new OpenGLUniform<>("vec3",
                new Vector3d(Colors.ORANGE.values()));
        uColor.bindVariable(shader, "uColor");

        uTrans = new OpenGLUniform<>("vec3",
                new Vector3d(new float[] { 0.5f, 0.0f, 0.0f }));
        uTrans.bindVariable(shader, "uTrans");
    }

    @Override
    public void update() {
        double x = 0.75 * Math.cos(EngineTime.time);
        double y = 0.75 * Math.sin(EngineTime.time);
        Vector3d translated = uTrans.getData().set(x, y, 0.0);

        uTrans.setData(translated);

//        Logger.info("Delta Time: {}", EngineTime.deltaTime);
        Logger.info("FPS: {}", 1/EngineTime.deltaTime);

        shader.bind();
        glBindVertexArray(vaoRef);
        uColor.update();
        uTrans.update();
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }

    public static void main(String[] args) {
        var window = new DrawShape5();
        window.run();
    }

}
