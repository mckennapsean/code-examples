// toon vertex shader

// variable for vertex normal
varying vec3 n;

// store lighting term
varying vec4 light;

void main(){
  // get the vertex normal, adapting to eye-space
  n = gl_NormalMatrix * gl_Normal;
  
  // calculate ambient lighting
  light = gl_LightSource[0].ambient;
  light += gl_LightModel.ambient;
  light += gl_LightSource[0].diffuse;
  
  // keep the position the same
  gl_Position = ftransform();
}
