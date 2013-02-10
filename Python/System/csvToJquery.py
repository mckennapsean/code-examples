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
