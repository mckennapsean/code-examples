// Copyright 2013 Sean McKenna
// 
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
// 
//        http://www.apache.org/licenses/LICENSE-2.0
// 
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

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
