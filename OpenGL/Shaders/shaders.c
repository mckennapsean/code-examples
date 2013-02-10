// by Sean McKenna
// how to set up a shader in OpenGL (C)
// includes sample shaders: base, gouraud, phong, texture-g, texture-p, & toon

// shader variables
GLuint v0, f0, p0;
GLint p0t;

// first, must read in the shader
char* readShader(const char *filename){
  
  // try to open shader file contents
  FILE *file = fopen(filename, "r");
  char *contents;
  int count = 0;
  
  // if missing file
  if(!file){
    fprintf(stderr, "Unable to open %s\n", filename);
    return NULL;
  }
  
  // prepare content to be read
  fseek(file, 0, SEEK_END);
  count = ftell(file);
  rewind(file);
  
  // read content into memory
  if(count > 0){
    contents = (char *) malloc(sizeof(char) * (count + 1));
    count = fread(contents, sizeof(char), count, file);
    contents[count] = '\0';
  }
  
  // clean-up
  fclose(file);
  return contents;
}

// then, must create all shader programs
void createShaders(){
  // info log variables
  GLint Result = GL_FALSE;
  int InfoLogLength;
  
  //
  // p0 - base shader (flat shading)
  //
  
  // store file contents
  char *vs0, *fs0;
  
  // initialize shaders
  v0 = glCreateShader(GL_VERTEX_SHADER);
  f0 = glCreateShader(GL_FRAGMENT_SHADER);
  
  // load shaders from file
  vs0 = readShader("base.vert");
  fs0 = readShader("base.frag");
  const char * vv0 = vs0;
  const char * ff0 = fs0;
  glShaderSource(v0, 1, &vv0, NULL);
  glShaderSource(f0, 1, &ff0, NULL);
  free(vs0);
  free(fs0);
  
  // compile shaders & log errors
  glCompileShader(v0);
  glGetShaderiv(v0, GL_COMPILE_STATUS, &Result);
  glGetShaderiv(v0, GL_INFO_LOG_LENGTH, &InfoLogLength);
  std::vector<char> VertexShaderErrorMessage0(InfoLogLength);
  glGetShaderInfoLog(v0, InfoLogLength, NULL, &VertexShaderErrorMessage0[0]);
  fprintf(stdout, "%s\n", &VertexShaderErrorMessage0[0]);
  glCompileShader(f0);
  glGetShaderiv(f0, GL_COMPILE_STATUS, &Result);
  glGetShaderiv(f0, GL_INFO_LOG_LENGTH, &InfoLogLength);
  std::vector<char> FragmentShaderErrorMessage0(InfoLogLength);
  glGetShaderInfoLog(f0, InfoLogLength, NULL, &FragmentShaderErrorMessage0[0]);
  fprintf(stdout, "%s\n", &FragmentShaderErrorMessage0[0]);
  
  // create shader programs & log errors
  p0 = glCreateProgram();
  glAttachShader(p0, v0);
  glAttachShader(p0, f0);
  glLinkProgram(p0);
  glGetProgramiv(p0, GL_LINK_STATUS, &Result);
  glGetProgramiv(p0, GL_INFO_LOG_LENGTH, &InfoLogLength);
  std::vector<char> ProgramErrorMessage0(max(InfoLogLength, int(1)));
  fprintf(stdout, "%s\n", &ProgramErrorMessage0[0]);
  
  // add program variable
  p0t = glGetUniformLocation(p0, "t");
  
  // clear shaders
  glDeleteShader(v0);
  glDeleteShader(f0);
}

// how to enable a shader program
// how to set shader variables
void setShader(){
  if(true){
    glUseProgram(p0);
    glUniform1f(p0t, someVariable);
  }else{
    glUseProgram(p0);
    glUniform1f(p0t, someOtherVariable);
  }
}
