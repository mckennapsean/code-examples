# by Sean McKenna
# convert target data from one CSV format
# to the necessary array input for the Jquery autocomplete search

# Defines the CSV data filenames
inFile = "target-names.csv"
outFile = "target-names-search.txt"

# Necessary imports
import csv

# Get the CSV data file as input
input = open(inFile, "rU")
reader = csv.reader(input)

# Prep output file/s
output = open(outFile, "wb")

# Process CSV file, row-by-row
start = 0
for row in reader:

  # format search data & write the data
  if start == 1:
    output.write('"' + row[0] + '"=>"' + row[0] + '",\n')

  # column name, ignore
  else:
    start = 1

# Close all files
input.close()
output.close()

print "Search array generation complete!"
