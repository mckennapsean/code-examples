# by Sean McKenna
# convert FITS files to PNG files for the internet
# Borrowed and uses code from the FITS to Net project: http://obswww.unige.ch/~tewes/f2n_dot_py/
# Converts all FITS files in a directory to PNG files

# You must specify all FITS images as input, like *.fits
# Be sure that you run this in a directory with all the necessary FITS files
# It will generate two for every FITS image in the directory
# Currently creates both a linear and log transformation

# The following two lines are only needed if f2n.py and f2n_fonts
# are not yet copied somewhere into your usual python path!
import sys
import os
import glob
import string
sys.path.append("f2n_dot_py_1.1/") # The directory that contains f2n.py and f2n_fonts !

# Now we can go on as usual :
import f2n

# iterative variable
iter = 0

# Run for every file specified as input
for input in sys.argv[1:]:

    # Get the FITS filename (no extension) and add a new one
    filename = os.path.splitext(input)[0]
    output = filename + '_n.png'
    output2 = filename + '_g.png'

    # The way to read a FITS file.
    image = f2n.fromfits(input)

    # By default, automatic cutoffs will be calculated.
    image.setzscale()

    # Make copy of image for second output, use linear
    image2 = image

    # By default, a log transformation would be used.
    image.makepilimage("lin")

    # Write the png
    image.tonet(output)
    iter += 1

    # Write the second png (log)
    image2.makepilimage("log")
    image2.tonet(output2)

print "You have successfully converted " + str(iter) + " files."
