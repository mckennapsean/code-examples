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

// creates a line (or vector) between two points
// works with CreatePoly.scala
// not intended to be used alone

class Line(p1c: Point, p2c: Point){
  
  // automatic accessors & constructors
  val p1 = p1c
  val p2 = p2c
  
  // line / vector between points, from p2 to p1
  def vect(): Point = {
    p1.sub(p2)
  }
  
  // length of vector / line
  def len(): Double = {
    this.vect.mag
  }
  
  // normalize vector / line
  def dir(): Point = {
    this.vect.norm
  }
  
  // describe line with SVG
  def svg(): String = {
    "<line x1= \"" + p1.x.toString + "\" y1= \"" + p1.y.toString + "\" x2= \"" + p2.x.toString + "\" y2= \"" + p2.y.toString + "\" \n\t style=\"stroke:rgb(0,0,0);stroke-width:2\"/>"
  }
}
