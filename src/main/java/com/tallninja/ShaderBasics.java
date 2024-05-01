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

public class ShaderBasics extends BaseWindow {

    private ShaderProgram shaderProgram;

    public ShaderBasics(String title, int width, int height) {
        super(title, width, height, Colors.WHITE);
    }

    @Override
    public void setup() {
        // Create the shader program
        shaderProgram = new ShaderProgram(
                "shaders/test-1.vert",
                "shaders/test-1.frag"
        );

        // set up vertex array object
        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        // set point width and height
        glPointSize(10);
    }

    @Override
    public void update() {
        shaderProgram.bind();

        // render geometric shapes
        glDrawArrays(GL_POINTS, 0, 1);
    }

    public static void main(String[] args) {
        var window = new ShaderBasics("Shader Basics", 800, 600);
        window.run();
    }
}
