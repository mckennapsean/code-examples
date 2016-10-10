# convert from CSV to Jquery autocomplete search format

# defines the CSV data filenames
inFile = "target-names.csv"
outFile = "target-names-search.txt"

# necessary imports
import csv

# get the CSV data file as input
input = open(inFile, "rU")
reader = csv.reader(input)

# prep output file/s
output = open(outFile, "wb")

# process CSV file, row-by-row
start = 0
for row in reader:

  # format search data & write the data
  if start == 1:
    output.write('"' + row[0] + '"=>"' + row[0] + '",\n')

  # column name, ignore
  else:
    start = 1

# close all files
input.close()
output.close()

# output success
print "Search array generation complete!"
