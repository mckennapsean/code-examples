# takes in many images as input, converting them based on CSV data

# defines the CSV data file
csvFile = "input.csv"

# necessary imports to convert from *.jpg to *.png
import os, sys
import Image
import ImageOps
import csv

# number of iterations
iter = 0

# run for every file specified as input
for infile in sys.argv[1:]:

  # process the input file for the name/data
  input = os.path.splitext(infile)[0]
  inputVars = input.split('_')
  img = Image.open(infile).convert("RGBA")
  #print("Opening file: " + infile)

  # use the input filename to grab the epoch data
  expStart = inputVars[1]
  expStart = list(expStart)
  expStart.insert(5, '.')
  expStart = ''.join(expStart)
  #print("Unique Identifier Found: " + expStart)

  # get the arcsecond scale info
  finderArcs = float(inputVars[2])

  # measured 60 arcseconds in the Finder to span 1048 pixels (0.057 arcseconds/pixel)
  # I will assume that this Finder is similar output to the other sizes
  # seems to be a good assumption in comparing Jupiter charts
  finderRatio = finderArcs / 1048

  # get the CSV data file
  reader = csv.reader(open(csvFile))

  # process CSV file
  for row in reader:
    try:

      # round the epoch to three decimal places to match
      roundExpStart = "%.3f" % float(row[1])

      # get the full data from the original filename
      if expStart in roundExpStart or expStart in row[1]:

        # generate the output filename, uniquely identified by rootname/dataset
        outfile = row[0] + ".png"
        #print("Now generating output file: " + outfile)

        # amount of degrees to rotate image
        orientat = row[33]

        # ratio of archive image (arcseconds / pixel)
        idcScale = float(row[34])
 
    # might throw an error on first line, processing the column name, count number of files
    except ValueError: iter += 1

  # crop output file to zoom in on object
  box = (155, 170, 1195, 1210)
  img = img.crop(box)

  # further zoom on object to match scaling of archive images
  finderScale = idcScale / finderRatio
  w = img.size[0] * finderScale
  h = img.size[1] * finderScale
  scale = (int(w), int(h))
  img.resize(scale)

  # rotate the image according to the data, keep transparent background
  rot = img.rotate(-float(orientat), expand=1)
  img = Image.new("RGBA", rot.size, (255,255,255,0))
  img.paste(rot, (0,0), rot)

  # invert the full value scale and enable it as the alpha band
  alpha = img.convert("L")
  alpha = ImageOps.invert(alpha)
  img.putalpha(alpha)

  # turn the image to a yummy turquoise color
  img.paste((0,133,134), (0,0), img)

  # save output file if non-existent
  if infile != outfile:
    try:
      img.save(outfile)
    except IOError:
      print("Cannot convert: " + infile)
      iter += -1

# output success
print("You have successfully converted " + str(iter) + " files.")
