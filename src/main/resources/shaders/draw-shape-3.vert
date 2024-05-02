#version 330 core

in vec3 aPos;
uniform vec3 uTrans;

void main() {
    vec3 pos = aPos + uTrans;
    gl_Position = vec4(pos, 1.0);
}