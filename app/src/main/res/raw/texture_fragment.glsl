precision mediump float;

uniform sampler2D uTexture;

varying vec4 vColor;
varying vec2 vTextureCoordinate;

void main() {
    gl_FragColor = (texture2D(uTexture, vTextureCoordinate));
}