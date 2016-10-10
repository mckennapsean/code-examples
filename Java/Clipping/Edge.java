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

import java.awt.*;
import java.awt.geom.*;

public class Edge {
  protected Point p1;
  protected Point p2;

  public Edge(Point p1, Point p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public Edge(Line2D l) {
    this.p1 = new Point(l.getX1(), l.getY1(), 0);
    this.p2 = new Point(l.getX2(), l.getY2(), 0);
  }

  public Point computeIntersection(Point s, Point p) {
    double edgex = p1.getX() - p2.getX();
    double linex = s.getX() - p.getX();

    double m1 = (s.getY() - p.getY()) / (s.getX() - p.getX());
    double m2 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
    double b1 = s.getY() - m1 * s.getX();
    double b2 = p1.getY() - m2 * p1.getX();

    double x = edgex == 0.0f ? p1.getX() :
      linex == 0.0f ? p.getX() : (b2 - b1) / (m1 - m2);
    double y = linex == 0.0f ? m2 * x + b2 : m1 * x + b1;

    return new Point(x,y,0);
  }

  public Point computeIntersection(Edge e) {
    double edgex = p1.getX() - p2.getX();
    double linex = e.p1.getX() - e.p2.getX();

    double m1 = (e.p1.getY() - e.p2.getY()) / (e.p1.getX() - e.p2.getX());
    double m2 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
    double b1 = e.p1.getY() - m1 * e.p1.getX();
    double b2 = p1.getY() - m2 * p1.getX();

    double x = edgex == 0.0f ? p1.getX() :
      linex == 0.0f ? e.p2.getX() : (b2 - b1) / (m1 - m2);
    double y = linex == 0.0f ? m2 * x + b2 : m1 * x + b1;

    return new Point(x,y,0);
  }

  public static boolean isPointInsideEdge(Edge e, Point ref, Point p) {
    boolean ret = true;
    Vector ve = e.p2.sub(e.p1);
    Vector vr = ref.sub(e.p1);
    Vector vp = p.sub(e.p1);

    Vector A = ve.cross(vr);
    Vector B = ve.cross(vp);

    ret = (A.getZ() < 0 && B.getZ() < 0) || (A.getZ() > 0 && B.getZ() > 0);
    return ret;
  }

  public Point getP1() {
    return p1;
  }

  public Point getP2() {
    return p2;
  }

  public Point generatePointOnEdge(double t) {
    return p1.add(p2.mul(t));
  }

  public double distanceTo(Point p) {
    double u = ((p.getX() - p1.getX())*(p2.getX() - p1.getX()) +
      (p.getY() - p1.getY())*(p2.getY() - p1.getY()))/p2.sub(p1).length2();
    Point ref = new Point(p1.getX() + u * (p2.getX() - p1.getX()),
      p1.getY() + u * (p2.getY() - p1.getY()), 0);
    return ref.sub(p).length();
  }

  public Line2D.Double getLine(){
    Line2D.Double line = new Line2D.Double(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    return line;
  }
}
