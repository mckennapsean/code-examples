# take a file with dates and convert to # of days from the start instead

import csv
import datetime as dt

# replace dates with increments of days
with open("output.csv", "w") as output:
  writer = csv.writer(output)
  with open("input.csv") as inp:
    reader = csv.reader(inp)
    firstDay = None
    rowCount = 0
    dateFormat = "%Y-%m-%d"
    for row in reader:
      if rowCount > 0:
        date = row.pop()
        day = dt.datetime.strptime(date, dateFormat)
        if rowCount == 1:
          firstDay = day
        dayNum = (day - firstDay).days
        duration = "<[" + str(dayNum) + ", " + str(dayNum + 1) + "]>"
        row.append(duration)

      writer.writerow(row)
      rowCount += 1
