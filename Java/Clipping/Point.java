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

public class Point {
  protected double [] e;
  public static Point Zero = new Point(0,0,0);
  
  public Point(double x, double y, double z) {
    e = new double[3];
    e[0] = x;
    e[1] = y;
    e[2] = z;
  }
  
  public double getX() {
    return e[0];
  }
  
  public double getY() {
    return e[1];
  }
  
  public double getZ() {
    return e[2];
  }
  
  public void setX(double x) {
    e[0] = x;
  }
  
  public void setY(double y) {
    e[1] = y;
  }
  
  public void setZ(double z) {
    e[2] = z;
  }
  
  public Point add(Point p) {
    return new Point(this.e[0] + p.e[0], this.e[1] + p.e[1],
      this.e[2] + p.e[2]);
  }
  
  public Vector sub(Point p) {
    return new Vector(this.e[0] - p.e[0], this.e[1] - p.e[1],
      this.e[2] - p.e[2]);
  }
  
  public Point mul(Point p) {
    return new Point(this.e[0] * p.e[0], this.e[1] * p.e[1],
      this.e[2] * p.e[2]);
  }
  
  public Point div(Point p) {
    return new Point(this.e[0] / p.e[0], this.e[1] / p.e[1],
      this.e[2] / p.e[2]);
  }
  
  public Point add(double s) {
    return new Point(e[0] + s, e[1] + s, e[2] + s);
  }
  
  public Point sub(double s) {
    return new Point(e[0] - s, e[1] - s, e[2] - s);
  }
  
  public Point mul(double s) {
    return new Point(e[0] * s, e[1] * s, e[2] * s);
  }
  
  public Point div(double s) {
    double inv = 1.0f / s;
    return new Point(e[0] * inv, e[1] * inv, e[2] * inv);
  }
  
  public Point midPoint(Point p) {
    Point m = this.add(p);
    return m.mul(0.5f);
  }
  
  public Vector toVector() {
    return new Vector(e[0], e[1], e[2]);
  }
 
}

