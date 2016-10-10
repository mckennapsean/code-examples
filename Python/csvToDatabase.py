# convert from several CSV to STScI database input files

# defines the CSV data filenames
inFile = "processed.csv"
outFile = "processed_targets.csv"
outFile2 = "processed_pp.csv"
piFile = "pi.csv"
descrFile = "descr.csv"

# necessary imports
import csv
import time

# start timer
start = time.time()

# number of error iterations
iter = 0

# get the CSV data file as input
input = open(inFile, "rU")
reader = csv.reader(input)

# prep output file/s
output = open(outFile, "wb")
output2 = open(outFile2, "wb")
writer = csv.writer(output)
writer2 = csv.writer(output2)

# initialize row variable
firstPass = 1

# process CSV file, row-by-row
for row in reader:

  # open CSV files to process PI name and target descriptions
  inputPI = open(piFile, "rU")
  inputDescr = open(descrFile, "rU")
  readerPI = csv.reader(inputPI)
  readerDescr = csv.reader(inputDescr)

  # row to be output
  outRow = row

  # split filename column
  rootname = str.split(outRow[0], "_")
  rootname[0] = rootname[0].lower()

  # detect improper extension, if any
  if len(rootname) > 1:
    if rootname[1] != "c0m.fits":
      iter += 1

  # when processing column name, define new column name
  if firstPass == 1:
    PI = "PI"
    descr = "target_descr"

    # check for the JPL_ tag, remove it
    for i,column in enumerate(outRow):
      if column.startswith("JPL_"):
        column = column.split("_", 1)
        outRow[i] = column[1].lower()
      else:
        outRow[i] = column.lower()

    # be sure we do this processing for the first row only
    firstPass = 0

  # turn filename into just the rootname
  outRow[0] = rootname[0]

  # turn n.a. and -1 values into NULL/blank values!
  for i,val in enumerate(outRow):

    # watch out for headers!
    try:
      j = int(val)
    except ValueError:
      j = 0

    # see if there are supposed to be null values and make it so
    if val.find("n.a.") != -1:
      outRow[i] = ""
    elif val.find("photif error") != -1:
      outRow[i] = ""
    elif val.find("#VALUE!") != -1:
      outRow[i] = ""
    elif j == -1:
      outRow[i] = ""

  # start second CSV file data, with rootname
  outRow2 = [rootname[0]]

  # find the PI name
  foundPI = 0
  for rowPI in readerPI:
    if rowPI[1] == row[3]:
      PI = rowPI[0].lower()
      foundPI = 1

  # error detection for no PI found
  if foundPI == 0:
    PI = "NOT FOUND"
    iter += 1

  # add PI name, matched with proposal ID
  outRow2.append(PI)

  # find the target description (adds about 2 minutes to compile time!)
  foundDescr = 0
  for rowDescr in readerDescr:
    if rowDescr[0] == row[0]:
      descr = rowDescr[1].lower()
      foundDescr = 1

  # error detection for no description found
  if foundDescr == 0:
    descr = "NOT FOUND"
    iter += 1

  # add the target description
  outRow2.append(descr)

  # remove unneeded columns from ephemeris target data
  outRow.pop(1)
  outRow.pop(1)
  outRow.pop(1)
  outRow.pop(1)
  outRow2.append(outRow.pop(1))
  outRow.pop(1)
  outRow.pop(1)
  outRow.pop(1)
  outRow.pop(1)
  outRow.pop(1)
  outRow.pop(1)
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow2.append(outRow.pop(1))
  outRow.pop(1)

  # move target column near the beginning
  outRow.insert(1, outRow.pop(23))

  # get the RA and DEC values
  RA = str.split(outRow[2], " ")
  DEC = str.split(outRow[3], " ")

  # convert RA, DEC if not in header column
  if len(RA) > 1 and len(DEC) > 1:

    # define RA and DEC values
    hRA = float(RA[0])
    mRA = float(RA[1])
    sRA = float(RA[2])
    hDEC = float(DEC[0])
    mDEC = float(DEC[1])
    sDEC = float(DEC[2])

    # convert RA
    dRA = (hRA * 15) + (mRA * 15 / 60) + (sRA * 15 / 3600)

    # convert DEC
    dDEC = (hDEC) + (mDEC / 60) + (sDEC / 3600)

    # save converted RA and DEC values
    outRow[2] = dRA
    outRow[3] = dDEC

  # write each row
  writer.writerow(outRow)
  writer2.writerow(outRow2)

  # close the PI file
  inputPI.close()

# close all files
input.close()
output.close()
output2.close()

# stop timer
end = time.time()

# process the time elapsed
elapsed = end - start
min = round(elapsed / 60, 3)

# display important info
if iter == 1:
  print("There was " + str(iter) + " error.")
elif iter == 0:
  print("There were no errors!")
else:
  print("There were " + str(iter) + " errors.")
print("CSV conversion operation complete after", min, "minutes.")
