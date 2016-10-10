// texture fragment shader with Gouraud shading

// from main program, grab the input texture
uniform sampler2D tex;

void main(){
  // set the fragment color based on texture
  vec4 col = texture2D(tex, gl_TexCoord[0].st);
  gl_FragColor = gl_Color * gl_Color * col;
}
