/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 8:22 PM
 */
package com.tallninja;

import com.tallninja.colors.Color;
import com.tallninja.colors.Colors;
import com.tallninja.exceptions.*;
import com.tallninja.time.EngineTime;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.tinylog.Logger;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class BaseWindow {

    private final String title;
    private final int width, height;
    private final Color backgroundColor;
    private long windowRef;

    private long previousTime, currentTime;

    public BaseWindow(String title, int width, int height, Color backgroundColor) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
    }

    public BaseWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.backgroundColor = Colors.BLACK;
    }

    public abstract void setup();
    public abstract void update();
    public abstract void cleanup();

    // This method creates objects, initializes values and loads external files
    public void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // initialize GLFW
        boolean initSuccess = glfwInit();
        if (!initSuccess) {
            throw new GLFWInitException();
        }

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        // Create the window
        windowRef = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( windowRef == NULL ) {
            throw new GLFWCreateWindowException();
        }

        EngineTime.time = 0;
        EngineTime.deltaTime = 1/60f;
        currentTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();

        // apply to this context instance
        glfwMakeContextCurrent(windowRef);

        /*
         * V-Sync
         * specify number of screen updates
         * to wait before swapping buffers.
         * setting to 1 synchronizes application frame rate
         * with display refresh rate;
         * prevents visual "screen tearing" artifacts
         **/
        glfwSwapInterval(1);

        // detect current context and makes
        // OpenGL bindings available for use
        GL.createCapabilities();

        // Check versions
        Logger.info("[OpenGL] Version: {}, Renderer: {}, Vendor: {}",
                glGetString(GL_VERSION), glGetString(GL_RENDERER), glGetString(GL_VENDOR));
        Logger.info("LWJGL Version: {}", Version.getVersion());

        // Make the window visible
        glfwShowWindow(windowRef);
    }
    public void run() {
        // call the init method
        init();

        // Set background color
        glClearColor(backgroundColor.getR(), backgroundColor.getG(),
                backgroundColor.getB(), backgroundColor.getA());

        // application specific init method
        setup();

        // main loop
        while (!glfwWindowShouldClose(windowRef)) {
            // poll input events
            glfwPollEvents();

            currentTime = System.currentTimeMillis();
            EngineTime.deltaTime = (currentTime - previousTime) / 1000f;
            EngineTime.time += EngineTime.deltaTime;
            previousTime = currentTime;

            // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // application specific update code
            update();

            // swap the color buffers
            // to display rendered graphics on screen
            glfwSwapBuffers(windowRef);
        }

        // terminate the window
        terminate();
    }
    public void terminate() {
        // application specific cleanup function
        cleanup();

        // stop window monitoring for user input
        glfwFreeCallbacks(windowRef);
        // close the window
        glfwDestroyWindow(windowRef);
        // stop GLFW
        glfwTerminate();
        // stop error callback
        glfwSetErrorCallback(null).free();
    }


}
