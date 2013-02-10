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

// texture fragment shader with Gouraud shading

// from main program, grab the input texture
uniform sampler2D tex;

void main(){
  // set the fragment color based on texture
  vec4 col = texture2D(tex, gl_TexCoord[0].st);
  gl_FragColor = gl_Color * gl_Color * col;
}
