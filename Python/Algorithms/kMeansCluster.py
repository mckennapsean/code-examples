## Copyright 2013 Sean McKenna
## 
##    Licensed under the Apache License, Version 2.0 (the "License");
##    you may not use this file except in compliance with the License.
##    You may obtain a copy of the License at
## 
##        http://www.apache.org/licenses/LICENSE-2.0
## 
##    Unless required by applicable law or agreed to in writing, software
##    distributed under the License is distributed on an "AS IS" BASIS,
##    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
##    See the License for the specific language governing permissions and
##    limitations under the License.
##

# run k-means algorithm on TSV data file
# output the clusters as a new TSV file
# requires numpy for the k-mean algorithm

# defines the TSV data filename & clustering
inFile = "data.tsv"
outFile = "data-cluster.tsv"
clusters = 7

# necessary imports
import csv
import copy
import time
import cv
import numpy as np

# start timer
start = time.time()

# get the TSV data file as input
input = open(inFile, "rU")
reader = csv.reader(input, dialect='excel-tab')

# initialize scanning variables
firstPass = True
numRows = 0
numCols = -1
firstRow = []
rows = []
data = []
cluster = []

# process TSV file,pyt row-by-row
for row in reader:
  if firstPass:
    firstRow = row
    numCols = len(row) - 1
    firstPass = False
  else:
    numRows += 1
    rows.append(row)
data = copy.deepcopy(rows)

# close input file
input.close()

# initialize data & label matrix
samples = cv.CreateMat(numRows, numCols, cv.CV_32F)
labels = cv.CreateMat(numRows, 1, cv.CV_32S)

# remove row name from data
for j in range(0, numRows):
  data[j].pop(0)

# fill data matrix
samples = cv.fromarray(np.array(data, np.float32))

# set ten iterations of the k-means algorithm
criteria = (cv.CV_TERMCRIT_EPS + cv.CV_TERMCRIT_ITER, 10, 1.0)

# k-means algorithm (implementation in OpenCV)
cv.KMeans2(samples, clusters, labels, criteria)

# get the cluster info into an array
for j in range(0, numRows):
  cluster.append(int(cv.Get1D(labels, j)[0]))

# prep output file
output = open(outFile, "wb")
writer = csv.writer(output, dialect='excel-tab')

# write the first row
firstRow.insert(1, "Cluster")
writer.writerow(firstRow)

for j in range(0, numRows):
  row = rows[j]
  row.insert(1, cluster[j])
  writer.writerow(row)

# close output file
output.close()

# stop timer
end = time.time()

# process the time elapsed
elapsed = end - start
min = round(elapsed / 60, 3)

# display time taken
print "k-means clustering algorithm complete after", min, "minutes."
