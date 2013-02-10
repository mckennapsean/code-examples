void main(){
  gl_Position = ftransform();
  
  // can also use:
  //gl_Position = gl_ProjectionMatrix * gl_ModelViewMatrix * gl_Vertex;
  //gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
  
  gl_FrontColor = gl_Color;
}
