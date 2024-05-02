/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 11:04 PM
 */
package com.tallninja;

import com.tallninja.colors.Colors;
import com.tallninja.shader.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class DrawPoint extends BaseWindow {

    private ShaderProgram shaderProgram;

    public DrawPoint(String title, int width, int height) {
        super(title, width, height, Colors.WHITE);
    }

    @Override
    public void setup() {
        // Create the shader program
        shaderProgram = new ShaderProgram(
                "shaders/draw-point.vert",
                "shaders/draw-point.frag"
        );

        // set up vertex array object
        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        // set point width and height
        glPointSize(50);
    }

    @Override
    public void update() {
        shaderProgram.bind();

        // render geometric shapes
        glDrawArrays(GL_POINTS, 0, 1);
    }

    @Override
    public void cleanup() {
        // unbind the shader program and delete from OpenGL context
        shaderProgram.cleanup();
    }

    public static void main(String[] args) {
        var window = new DrawPoint("Shader Basics", 800, 600);
        window.run();
    }
}
