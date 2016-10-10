// create a polyhedron that is sub-divided a set amount of times

// create polyhedron, subdivide, and output
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
