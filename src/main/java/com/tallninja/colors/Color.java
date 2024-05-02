/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 5/1/24 : 8:56 PM
 */
package com.tallninja.colors;

public class Color {

    private final float r, g, b, a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1.0f;
    }

    public static Color fromHex(String hexCode) {
        if (!hexCode.startsWith("#") || hexCode.length() > 9) {
            throw new RuntimeException("Failed to parse color");
        }

        float r = (float) Integer.parseInt(hexCode.substring(1, 3), 16) / 255;
        float g = (float) Integer.parseInt(hexCode.substring(3, 5), 16) / 255;
        float b = (float) Integer.parseInt(hexCode.substring(5, 7), 16) / 255;
        float a = 1.0f;

        if (hexCode.length() > 7) {
            a = (float) Integer.parseInt(hexCode.substring(7), 16) / 255;
        }

        return new Color(r, g, b, a);
    }

    public float[] values() {
        return new float[] {r, g, b};
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }
}
