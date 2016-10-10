// creates a triangle from three lines / vectors
// works with CreatePoly.scala
// not intended to be used alone

class Triangle(p1c: Point, p2c: Point, p3c: Point){
  
  // automatic accessors & constructors
  val p1 = p1c
  val p2 = p2c
  val p3 = p3c
  
  // perpendicular unit vector (normal)
  def normal(): Point = {
    val u = (p1.sub(p2)).norm
    val v = (p3.sub(p2)).norm
    u.cross(v)
  }
  
  // divide triangle into four pieces into a list
  def subdivide(): List[Triangle] = {
    val p12 = (mid(p1, p2)).norm
    val p23 = (mid(p2, p3)).norm
    val p31 = (mid(p3, p1)).norm
    var pieces = List(new Triangle(p1, p12, p31))
    pieces = pieces :+ (new Triangle(p12, p2, p23))
    pieces = pieces :+ (new Triangle(p23, p3, p31))
    pieces = pieces :+ (new Triangle(p12, p23, p31))
    pieces
  }
  
  // output to SVG format
  def svg(rad: Double): String = {
    val trans = new Point(rad, rad, rad)
    val tp1 = (p1.scale(rad)).add(trans)
    val tp2 = (p2.scale(rad)).add(trans)
    val tp3 = (p3.scale(rad)).add(trans)
    val tp12 = new Line(tp1, tp2)
    val tp23 = new Line(tp2, tp3)
    val tp31 = new Line(tp3, tp1)
    tp12.svg + "\n" + tp23.svg + "\n" + tp31.svg + "\n"
  }
  
  // midpoint of an edge
  def mid(u: Point, v: Point): Point = {
    (u.add(v)).scale(0.5)
  }
}
