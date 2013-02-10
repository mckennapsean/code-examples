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

// toon fragment shader

// normal vector, passed from vertex shader
varying vec3 n;

// grab lighting term
varying vec4 light;

// from main program, get threshold value
uniform float t;

// from main program, get threshold value 2
uniform float t2;

// from main program, get threshold value 3
uniform float t3;

// from main program, get threshold value
uniform float s;

void main(){
  // color for this fragment
  vec4 c;
  
  // get the light's direction
  vec3 l = normalize(vec3(gl_LightSource[0].position));
  
  // compute the fragment intensity
  // be sure to re-normalize n for the fragment
  float i = dot(l, normalize(n));
  
  // for brightly lit fragments
  if(i > (1.00 - t / (15.0 - 7.0 * s)))
    c = vec4(1.00, 0.67, 0.00, 1.00);
  
  // for somewhat lit fragments
  else if(i > (1.00 - 2.0 * t2))
    c = vec4(0.75, 0.50, 0.00, 1.00);
  
  // for barely lit fragments
  else if(i > (1.00 - 3.0 * t3))
    c = vec4(0.50, 0.34, 0.00, 1.00);
  
  // for nearly unlit fragments
  else
    c = vec4(0.25, 0.17, 0.00, 1.00);
  
  // set the fragment color
  gl_FragColor = light * c;
}
