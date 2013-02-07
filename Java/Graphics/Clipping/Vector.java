// included by Sean McKenna
// this file is under the GPL license
// it is ONLY in this sample code to run Clipping.java 
// this file is NOT covered in the sample code's license

/* 
   This file is part of JHAVE -- Java Hosted Algorithm Visualization
   Environment, developed by Tom Naps, David Furcy (both of the
   University of Wisconsin - Oshkosh), Myles McNally (Alma College), and
   numerous other contributors who are listed at the http://jhave.org
   site

   JHAVE is free software: you can redistribute it and/or modify it under
   the terms of the GNU General Public License as published by the Free
   Software Foundation, either version 3 of the License, or (at your
   option) any later version.

   JHAVE is distributed in the hope that it will be useful, but WITHOUT
   ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
   FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
   for more details.

   You should have received a copy of the GNU General Public License
   along with the JHAVE. If not, see:
   <http://www.gnu.org/licenses/>.
*/

import java.lang.Math;

public class Vector {
  
  protected double x;
  protected double y;
  protected double z;
  
  public Vector(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  public double getZ() {
    return z;
  }
  
  public void setX(double val) {
    x = val;
  }
  
  public void setY(double val) {
    y = val;
  }
  
  public void setZ(double val) {
    z = val;
  }
  
  public Vector add(Vector v) {
    return new Vector(x + v.x, y + v.y, z + v.z);
  }
  
  public Vector sub(Vector v) {
    return new Vector(x - v.x, y - v.y, z - v.z);
  }
  
  public Vector mul(Vector v) {
    return new Vector(x * v.x, y * v.y, z * v.z);
  }
  
  public Vector div(Vector v) {
    return new Vector(x / v.x, y / v.y, z / v.z);
  }
  
  public Vector add(double s) {
    return new Vector(x + s, y + s, z + s);
  }
  
  public Vector sub(double s) {
    return new Vector(x - s, y - s, z - s);
  }
  
  public Vector mul(double s) {
    return new Vector(x * s, y * s, z * s);
  }
  
  public Vector div(double s) {
    double inv = 1.0f / s;
    return new Vector(x * inv, y * inv, z * inv);
  }
  
  public double length() {
   return  (double)Math.sqrt(x * x + y * y + z * z);
  }
  
  public double length2() {
    return (x * x + y * y + z * z);
  }
  
  public Vector cross(Vector v) {
    return new Vector(y * v.z - z * v.y,
                      z * v.x - x * v.z,
                      x * v.y - y * v.x);
  }
  
  public double dot(Vector v) {
    return (x * v.x + y * v.y + z * v.z);  
  }
  
  public Point toPoint() {
    return new Point(x, y, z);
  }
  
  public Vector getInverse() {
   return new Vector(1.0f / x, 1.0f / y, 1.0f / z); 
  }
  
  public double normalize() {
    double len = length();
    double inv = 1.0f / len;
    
    x *= inv;
    y *= inv;
    z *= inv;
    
    return len;
  }
  
  public double angleBetween(Vector v) {
    Vector v1 = new Vector(x, y, z);
    Vector v2 = new Vector(v.x, v.y, v.z);
    v1.normalize();
    v2.normalize();
    return Math.acos(v1.dot(v2));
  }
}
