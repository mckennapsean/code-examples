// Gouraud vertex shader

// from main program, get specular highlight value
uniform float s;

void main(){
  // set variables
  vec3 ldir;
  vec3 lhalf;
  vec3 n;
  vec4 amb;
  vec4 diff;
  vec4 spec;
  float dotProd;
  float dotProd2;
  float ldist;
  
  // calculate ambient and diffuse lighting
  amb = gl_FrontMaterial.ambient * gl_LightSource[0].ambient;
  amb += gl_LightModel.ambient * gl_FrontMaterial.ambient;
  diff = gl_FrontMaterial.diffuse * gl_LightSource[0].diffuse;
  
  // normal for vertex, in eye coords
  n = normalize(gl_NormalMatrix * gl_Normal);
  
  // light position for vertex
  vec4 vPos = gl_ModelViewMatrix * gl_Vertex;
  vec3 aux = vec3(gl_LightSource[0].position - vPos);
  ldir = normalize(aux);
  ldist = length(aux);
  
  // calculate the light half vector
  lhalf = normalize(gl_LightSource[0].halfVector.xyz);
  
  // dot product from 0 to 1
  dotProd = max(dot(n, ldir), 0.0);
  
  // calculate attenuation
  float att = 1.0 / (gl_LightSource[0].constantAttenuation + gl_LightSource[0].linearAttenuation * ldist + gl_LightSource[0].quadraticAttenuation * ldist * ldist);

  // calculate specular term
  if(dotProd > 0.0){
    // compute the specular component
    dotProd2 = max(dot(n, lhalf), 0.0);
    spec = gl_FrontMaterial.specular * gl_LightSource[0].specular * pow(dotProd2, gl_FrontMaterial.shininess * (2.50 - s));
    
    // spec, compute final color
    gl_FrontColor = amb + att * (dotProd * diff) + att * spec;
  
  }else{
    // no spec, compute final color
    gl_FrontColor = amb + att * (dotProd * diff);
  }
  
  // set vertex position
  gl_Position = ftransform();
}
