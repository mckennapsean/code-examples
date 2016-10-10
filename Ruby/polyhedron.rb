# inspired by Leon Tabak
# several classes to abstract building a polyhedron and outputting it to SVG

# define a vector
class Vector
  
  # access vector components (scalars)
  attr :x
  attr :y
  attr :z
  
  # creating a vector
  def initialize x, y, z 
    @x = x
    @y = y
    @z = z
  end
  
  # vector addition
  def add v
    Vector.new @x + v.x, @y + v.y, @z + v.z
  end
  
  # vector subtraction
  def subtract v
    Vector.new @x - v.x, @y - v.y, @z - v.z
  end
  
  # vector scalar multiplication (scaling)
  def scale scalar
    Vector.new scalar * @x, scalar * @y, scalar * @z
  end
  
  # dot product of two vectors
  def dot v 
    @x * v.x + @y * v.y + @z * v.z
  end
  
  # vector magnitude (from dot product)
  def mag
    Math.sqrt self.dot self
  end
  
  # vector normalization (magnitude & scale)
  def normalize
    mag = self.mag
    scale = 1 / mag
    self.scale scale
  end
  
  # cross product of two vectors
  def cross v
    x = @y * v.z - @z * v.y
    y = @z * v.x - @x * v.z
    z = @x * v.y - @y * v.x
    Vector.new x, y, z
  end
  
  # print out vector
  def to_s
    "(" + x.to_s + "," + y.to_s + "," + z.to_s + ")"
  end
end

# define a line
class Line
  
  # access line components (vectors)
  attr :head
  attr :tail

  # creating a line
  def initialize head, tail
    @head = head
    @tail = tail
  end
  
  # vector from tail to head
  def vector
    head.subtract tail
  end

  # length of the line
  def length
    self.vector.mag
  end
  
  # normalize the line's vector
  def direction
    self.vector.normalize
  end

  # output a line as an SVG element
  def svg
    '<line x1= "' + @head.x.to_s + '" ' + 'y1= "' + @head.y.to_s + '" ' +
          'x2= "' + @tail.x.to_s + '" ' + 'y2= "' + @tail.y.to_s + '" ' +
          "\n\t" + 'style="stroke:rgb(0,0,0);stroke-width:2"/>'
  end
end

# define a triangle
class Triangle
  
  # access triangle components (lines)
  attr :a
  attr :b
  attr :c
  
  # creating a triangle
  def initialize a, b, c
    @a = a
    @b = b
    @c = c
  end
  
  # get the triangle's normal vector
  def normal
    u = (a.subtract b).normalize
    v = (c.subtract b).normalize
    u.cross v
  end
  
  # break the triangle into smaller pieces (4)
  def subdivide
    ab = (midpoint a, b).normalize
    bc = (midpoint b, c).normalize
    ca = (midpoint c, a).normalize
    smallerTriangles = Array.new
    smallerTriangles << (Triangle.new a, ab, ca)
    smallerTriangles << (Triangle.new ab, b, bc)
    smallerTriangles << (Triangle.new bc, c, ca)
    smallerTriangles << (Triangle.new ab, bc, ca)
  end
  
  # output a triangle as an SVG element
  def svg radius
    translation = Vector.new radius, radius, radius
    p0 = (@a.scale radius).add translation
    p1 = (@b.scale radius).add translation
    p2 = (@c.scale radius).add translation
    p01 = Line.new p0, p1
    p12 = Line.new p1, p2
    p20 = Line.new p2, p0
    p01.svg + "\n" + p12.svg + "\n" + p20.svg + "\n"
  end
  
  # determine midpoints along the line
  private
  def midpoint u, v
    (u.add v).scale 0.5
  end
end

# define a polyhedron
class Polyhedron
  
  # access polyhedron components (faces)
  attr :faces
  
  # creating a polyhedron (specifically, an octahedron)
  def initialize
    
    # create an octahedron
    north = Vector.new 0.0, 1.0, 0.0
    east = Vector.new 1.0, 0.0, 0.0
    south = Vector.new 0.0, -1.0, 0.0
    west = Vector.new -1.0, 0.0, 0.0
    near = Vector.new 0.0, 0.0, 1.0
    northeast = Triangle.new north, east, near
    southeast = Triangle.new east, south, near
    southwest = Triangle.new near, south, west
    northwest = Triangle.new north, near, west
    @faces = [northeast, southeast, southwest, northwest] 
  end
  
  # break each face into smaller triangular pieces
  def subdivide
    smallerFaces = Array.new
    @faces.each{|t| smallerFaces.concat t.subdivide}
    @faces = smallerFaces
  end
  
  # output a polyhedron as an SVG element embedded in an HTML page
  def html
    
    # size for the polyhedron
    radius = 256
    
    # set the polyhedron face size
    edges = String.new
    @faces.each{|t| edges << (t.svg radius / 2) << "\n"}
    
    # HTML to output on the page
    "<html>" + "\n" +
    "<body>" + "\n" +
    "<h1>A Polyhedron</h1>" + "\n" +
    
    # creating the SVG embedding tag
    '<svg xmlns="http://www.w3.org/2000/svg"' + "\n" +
    'version="1.1" height="' + (2 * radius).to_s +
      '" width="' + (2 * radius).to_s + '">' + "\n" +
      
    # embed all edges of the polyhedron
    edges + "\n" +
    
    # wrap up the HTML output
    "</svg>" + "\n" + 
    "</body>" + "\n" + 
    "</html>"  
  end
end

# this ends the classes, the rest is a test of the program

# create a polyhedron (technically, only an octahedron)
p = Polyhedron.new

# subdivide the faces a few times
p.subdivide
p.subdivide

# output the SVG as a webpage
puts p.html
