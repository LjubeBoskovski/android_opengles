uniform mat4 u_MVPMatrix;
uniform mat4 u_MVMatrix;

attribute vec4 aPosition;
attribute vec4 aColor;
attribute vec2 aTextureCoordinate;

varying vec4 vColor;
varying vec2 vTextureCoordinate;

void main() {
    vColor = aColor;
    vTextureCoordinate = aTextureCoordinate;

//    vec3 modelViewVertex = vec3(u_MVMatrix * a_Position);
//    vec3 modelViewNormal = vec3(u_MVMatrix * vec4(a_Normal, 0.0));
    gl_Position = u_MVPMatrix * aPosition;
}