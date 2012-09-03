// Creates a polyhedron in 3-D space by Sean McKenna
// From triangles, Ruby code from Leon

class Polyhedron(){

  // Define points to start polyhedron
  val north = new Point(0.0, 1.0, 0.0)
  val east = new Point(1.0, 0.0, 0.0)
  val south = new Point(0.0, -1.0, 0.0)
  val west = new Point(-1.0, 0.0, 0.0)
  val near = new Point(0.0, 0.0, 1.0)

  // Create faces of octahedron
  val northeast = new Triangle(north, east, near)
  val southeast = new Triangle(east, south, near)
  val southwest = new Triangle(near, south, west)
  val northwest = new Triangle(north, near, west)

  // Store all faces in list
  var faces = List(northeast, southeast, southwest, northwest)

  // Divide a triangular face into four smaller ones
  def subdivide(){
    var smallerFaces = List[Triangle]()
    for(i<-faces){
      smallerFaces = smallerFaces ++ i.subdivide()
    }
    faces = smallerFaces
  }

  // Describe polyhedron in HTML with SVG
  def html(): String = {
    val rad = 256
    
    // grab all triangle code into one string
    var edges = ""
    for(i<-faces){
      edges += i.svg(rad/2)
      edges += "\n"
    }

    // generate output page (HTML)
    val output = "<html> \n <body> \n <h1> A Polyhedron </h1> \n <svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" height=\"" + (2*rad).toString() + "\" width=\"" + (2*rad).toString() + "\"> \n" + edges + "\n </svg> \n </body> \n </html>"
    output
  }
}
