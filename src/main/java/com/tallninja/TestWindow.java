/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 9:17 PM
 */
package com.tallninja;

import com.tallninja.colors.Colors;


public class TestWindow extends BaseWindow {
    public TestWindow(String title, int width, int height) {
        super(title, width, height, Colors.BLUE);
    }

    @Override
    public void setup() {
    }

    @Override
    public void update() {

    }

    @Override
    public void cleanup() {

    }

    public static void main(String[] args) {
        var testWindow = new TestWindow("Test Window", 800, 600);
        testWindow.run();
    }
}
