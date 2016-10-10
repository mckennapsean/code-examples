# creates an image overlay based on database variables
# currently based on the following parameters: Id, x-Size, y-Size, destination folder
# requires mysqldb, might need to install python-mysqldb)

# needed to create images and connect to databases
import sys
import Image
import ImageDraw
import MySQLdb

# variables for db connection
host = 'database.google.com'
user = 'admin'
passwd = 'password'
dbname = 'main'

# grab parameters from command line
Id = sys.argv[1]
xSize = sys.argv[2]
ySize = sys.argv[3]
outFile = sys.argv[4]

# connect to DB
db = MySQLdb.connect(host, user, passwd, dbname)
c = db.cursor()

# grab all the information to draw the image for some ID
query = "SELECT * FROM table WHERE id=" + Id
c.execute(query)
result = c.fetchall()

# generate lists of where to mark the image
xCoords=[]
yCoords=[]
diams=[]

# for all database records, store the variables
# adjust these numbers as needed for your data
for record in result:
  xCoords.append(record[0])
  yCoords.append(record[1])
  diams.append(record[2])

# create base image (transparent)
# this is an overlay, could input an image instead
size = (int(xSize), int(ySize))
img = Image.new("RGBA", size)
draw = ImageDraw.Draw(img)

# draw circle for each marking
# adjust image operations as needed for the data
iter = 0
for diam in diams:
  x0 = xCoords[iter] - (diam / 2)
  y0 = yCoords[iter] - (diam / 2)
  x1 = x0 + diam + 1
  y1 = y0 + diam + 1
  bbox = (x0, y0, x1, y1)
  circle = (242, 207, 16, 255)
  draw.ellipse(bbox, outline=circle)
  iter += 1

# save generated overlay image to output folder
try:
  img.save(outFile, "PNG")
except IOError:
  print("Cannot save the overlay: " + str(Id) + ".png <br />")
