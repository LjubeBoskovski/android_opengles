precision mediump float;

uniform sampler2D uTexture;

varying vec4 vColor;
varying vec4 vTextureCoordinate;

void main() {
//    gl_FragColor = vColor;
    glFragColor = texture2D(uTexture, vTextureCoordinate);
}