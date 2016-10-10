# converts a csv file with set info to an Euler permutation list
# also checks for duplicate row names

# defines the CSV data filenames
inFile = "input.csv"
outFile = "output.csv"

# necessary imports
import csv
import time

# start timer
start = time.time()

# number of redundant iterations
iter = 0

# get the CSV data file as input
input = open(inFile, "rU")
reader = csv.reader(input)

# set up CSV data file as output
output = open(outFile, "wb")
writer = csv.writer(output, delimiter="\t")

# initialize row variable
firstPass = True

# initialize lists for item info
itemName = []

# initialize label counting hash
labelCount = dict()

# process CSV file, row-by-row
for row in reader:
  
  # initialize list for creating Euler diagram list
  outRow = []

  # when processing column names only
  if firstPass:
    firstPass = False;
  
  # when processing an item
  else:
    
    # grab which label (last column is the set label)
    label = row[61]
          
    # are there duplicate names?
    if item in itemName:
      
      # add a redundancy
      iter += 1
    
    # no duplicates, store in list
    else:
      itemName.append(item)
    
    # add to label count appropriately
    if item in labelCount:
      labelCount[gene[1]] += 1
    else:
      labelCount[gene[1]] = 1
      
    # store as part of the Euler diagram output
    outRow.append(item.strip())
    outRow.append(label)
    writer.writerow(outRow)

# close all files
input.close()
output.close()

# count the gene labels for histogram
oneLabel = 0
twoLabels = 0
threeLabels = 0
fourLabels = 0
fiveLabels = 0
additionalLabels = 0
for val in labelCount.values():
  if val == 1:
    oneLabel += 1
  elif val == 2:
    twoLabels += 1
  elif val == 3:
    threeLabels += 1
  elif val == 4:
    fourLabels += 1
  elif val == 5:
    fiveLabels += 1
  else:
    additionalLabels += 1

# stop timer
end = time.time()

# process the time elapsed
elapsed = end - start
min = round(elapsed / 60, 3)

# display redundancies (if any)
if iter == 1:
  print("There was " + str(iter) + " redundancy.")
elif iter == 0:
  print("There were no redundancies!")
else:
  print("There were " + str(iter) + " redundancies.")

# display gene label counts
print("There are " + str(oneLabel) + " genes with one label.")
print("There are " + str(twoLabels) + " genes with two labels.")
print("There are " + str(threeLabels) + " genes with three labels.")
print("There are " + str(fourLabels) + " genes with four labels.")
print("There are " + str(fiveLabels) + " genes with five labels.")
print("There are " + str(additionalLabels) + " genes with additional labels.")

# display time taken
print("CSV scanning operation complete after", min, "minutes.")
