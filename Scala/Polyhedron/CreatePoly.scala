// Runs to create a polyhedron in 3-D space by Sean McKenna
// Input an int to subdivide, Ruby code from Leon

// Create polyhedron, subdivide, and output
object CreatePoly{
  def main(args: Array[String]){
    val arg = args.toList
    val p = new Polyhedron()
    val iter = arg.head.toInt
    for(i<-0 until iter){
      p.subdivide
    }
    println(p.html())
  }
}
