# by Sean McKenna
# converts finder chart images
# with associated filenames from the Planet Pipeline database and to
# create transparent versions of these charts

# TO-DO's
# You must specify every finder chart image as input (like *.jpg)
# The output filename uses the rootname, maybe use the epoch/time data instead
# You might also want to get 'orientat' and 'idscale' from the CSV file
# That information I believe should help you rotate the view as well as
#   match the arcseconds/pixel ratio between the archive and finder images
# It might be useful to chop up the view to what is seen in the archive image

# Defines the CSV data filename
csvFile = "../Database/Jupiter.csv"

# Necessary imports to convert from *.jpg to *.png
import os, sys
import Image
import ImageOps
import csv

# Number of iterations
iter = 0

# Run for every file specified as input
for infile in sys.argv[1:]:

  # Process the input file for the name/data
  input = os.path.splitext(infile)[0]
  inputVars = input.split('_')
  img = Image.open(infile).convert("RGBA")
  #print "Opening file: " + infile

  # Use the input filename to grab the epoch data
  expStart = inputVars[1]
  expStart = list(expStart)
  expStart.insert(5, '.')
  expStart = ''.join(expStart)
  #print "Unique Identifier Found: " + expStart

  # Get the arcsecond scale info
  finderArcs = float(inputVars[2])

  # Measured 60 arcseconds in the Finder to span 1048 pixels (0.057 arcseconds/pixel)
  # I will assume that this Finder is similar output to the other sizes
  # Seems to be a good assumption in comparing Jupiter charts
  finderRatio = finderArcs / 1048

  # Get the CSV data file
  reader = csv.reader(open(csvFile))

  # Process CSV file
  for row in reader:
    try:

      # Round the epoch to three decimal places to match
      roundExpStart = "%.3f" % float(row[1])

      # Get the full data from the original filename
      if expStart in roundExpStart or expStart in row[1]:

        # Unused, old code, used to generate the output with more arguments
        #filter = row[8]
        #filter = list(filter)
        #filter = filter[1] + filter[2] + filter[3]
        #outfile0 = row[5] + "_0_" + row[31] + "_a_" + row[0] + "_" + filter + "_f.png"
        #outfile1 = row[5] + "_0_" + row[31] + "_b_" + row[0] + "_" + filter + "_f.png"
        #outfile2 = row[5] + "_0_" + row[31] + "_c_" + row[0] + "_" + filter + "_f.png"
        #outfile3 = row[5] + "_0_" + row[31] + "_d_" + row[0] + "_" + filter + "_f.png"
        #outfile = outfile0

        # Generate the output filename, uniquely identified by rootname/dataset
        outfile = row[0] + ".png"
        #print "Now generating output file: " + outfile

        # Amount of degrees to rotate image
        orientat = row[33]

        # Ratio of archive image (arcseconds / pixel)
        idcScale = float(row[34])
 
    # Might throw an error on first line, processing the column name, count number of files
    except ValueError: iter += 1

  # Crop output file to zoom in on object
  box = (155, 170, 1195, 1210)
  img = img.crop(box)

  # Further zoom on object to match scaling of archive images
  finderScale = idcScale / finderRatio
  w = img.size[0] * finderScale
  h = img.size[1] * finderScale
  scale = (int(w), int(h))
  img.resize(scale)

  # Rotate the image according to the data, keep transparent background
  rot = img.rotate(-float(orientat), expand=1)
  img = Image.new("RGBA", rot.size, (255,255,255,0))
  img.paste(rot, (0,0), rot)

  # Invert the full value scale and enable it as the alpha band
  alpha = img.convert("L")
  alpha = ImageOps.invert(alpha)
  img.putalpha(alpha)

  # Turn the image to a yummy turquoise color
  img.paste((0,133,134), (0,0), img)

  # Save output file if non-existent
  if infile != outfile:
    try:
      img.save(outfile)
    except IOError:
      print "Cannot convert: " + infile
      iter += -1


print "You have successfully converted " + str(iter) + " files."
