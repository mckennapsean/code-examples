// Creates a line in 3-D space by Sean McKenna
// Or a vector between two points, Ruby code from Leon

class Line(p1c: Point, p2c: Point){
  // Automatic accessors & constructors
  val p1 = p1c
  val p2 = p2c

  // Line/vector between points, from p2 to p1
  def vect(): Point = {
    p1.sub(p2)
  }

  // Length of vector/line
  def len(): Double = {
    this.vect.mag
  }

  // Normalize vector/line
  def dir(): Point = {
    this.vect.norm
  }

  // Describe line with SVG
  def svg(): String = {
    "<line x1= \"" + p1.x.toString + "\" y1= \"" + p1.y.toString + "\" x2= \"" + p2.x.toString + "\" y2= \"" + p2.y.toString + "\" \n\t style=\"stroke:rgb(0,0,0);stroke-width:2\"/>"
  }
}
