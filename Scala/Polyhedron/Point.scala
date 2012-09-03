// Creates a point in 3-D space by Sean McKenna
// Or vector from (0,0,0), Ruby code from Leon

class Point(xc: Double, yc: Double, zc: Double){
  // Automatic accessors & constructors
  val x = xc
  val y = yc
  val z = zc

  // Add two points
  def add(p: Point): Point = {
    new Point(x + p.x, y + p.y, z + p.z)
  }

  // Subtract a point from first point
  def sub(p: Point): Point = {
    new Point(x - p.x, y - p.y, z - p.z)
  }

  // Scale point from (0,0)
  def scale(d: Double): Point = {
    new Point(x * d, y * d, z * d)
  }

  // Dot product of two points as vectors
  def dot(p: Point): Double = {
    x * p.x + y * p.y + z * p.z
  }

  // Magnitude of current point as a vector
  def mag(): Double = {
    math.sqrt(dot(this))
  }

  // Normalize current point as a vector
  def norm(): Point = {
    val mg = mag
    new Point(x / mg, y / mg, z / mg)
  }

  // Cross product of points as vectors
  def cross(p: Point): Point = {
    val xCross = y * p.z - z * p.y
    val yCross = z * p.x - x * p.z
    val zCross = x * p.y - y * p.x
    new Point(xCross, yCross, zCross)
  }

  // Print out string
  override def toString(): String = "(" + x + ", " + y + ", " + z + ")"
}
