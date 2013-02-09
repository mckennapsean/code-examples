// by Sean McKenna
// creates a point (or vector)
// works with CreatePoly.scala
// not intended to be used alone

class Point(xc: Double, yc: Double, zc: Double){
  
  // automatic accessors & constructors
  val x = xc
  val y = yc
  val z = zc
  
  // add two points / vectors
  def add(p: Point): Point = {
    new Point(x + p.x, y + p.y, z + p.z)
  }
  
  // subtract a point / vector from first point / vector
  def sub(p: Point): Point = {
    new Point(x - p.x, y - p.y, z - p.z)
  }
  
  // scale point / vector from (0,0)
  def scale(d: Double): Point = {
    new Point(x * d, y * d, z * d)
  }
  
  // dot product of two points as vectors
  def dot(p: Point): Double = {
    x * p.x + y * p.y + z * p.z
  }
  
  // magnitude of current point as a vector
  def mag(): Double = {
    math.sqrt(dot(this))
  }
  
  // normalize current point as a vector
  def norm(): Point = {
    val mg = mag
    val scl = 1.0 / mg
    scale(scl)
  }
  
  // cross product of points as vectors
  def cross(p: Point): Point = {
    val xCross = y * p.z - z * p.y
    val yCross = z * p.x - x * p.z
    val zCross = x * p.y - y * p.x
    new Point(xCross, yCross, zCross)
  }
  
  // print out string
  override def toString(): String = "(" + x + ", " + y + ", " + z + ")"
}
