// Copyright 2013 Sean McKenna
// 
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
// 
//        http://www.apache.org/licenses/LICENSE-2.0
// 
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

// inspired by Ben Fry
// stores data file in a simple table of floats
// expects column and row names
// not intended to be used alone

// input from data file
class FloatTable{
  
  // data to store
  int rowCount;
  int colCount;
  float[][] data;
  String[] rowNames;
  String[] colNames;
  
  // file delimiter
  char delimiter = ',';
  
  // create a table of float values
  FloatTable(String file){
    
    // load in data
    String[] rows = loadStrings(file);
    String[] cols = split(rows[0], delimiter);
    
    // get rid of the top-left corner
    colNames = subset(cols, 1);
    
    // clean column names (first row)
    removeQuotes(colNames);
    
    // define the column count
    colCount = colNames.length;
    
    // initialize before filling row names and data
    rowNames = new String[rows.length - 1];
    data = new float[rows.length - 1][];
    
    // skip column row, read in data
    for(int i = 1; i < rows.length; i++){
      
      // skip empty & comment rows
      if(trim(rows[i]).length() == 0)
        continue;
      if(rows[i].startsWith("#"))
        continue;
      
      // split the row on the delimiter & clean it
      String[] pieces = split(rows[i], delimiter);
      removeQuotes(pieces);
      
      // store the row name
      rowNames[rowCount] = pieces[0];
      
      // copy in the float data
      data[rowCount] = parseFloat(subset(pieces, 1));
      
      // increase the row count (only if row contained data)
      rowCount++;
    }
    
    // resize the 'data' array in case of empty rows
    data = (float[][]) subset(data, 0, rowCount);
  }
  
  // remove quotes from a string array
  void removeQuotes(String[] arr){
    for(int i = 0; i < arr.length; i++){
      
      // turn " into '
      arr[i] = arr[i].replaceAll("\"\"", "\"");
      
      // only if a pair of quotes can exist in a piece of the string array
      if(arr[i].length() > 2){
        
        // remove the quotes, if they surround the piece of the string array
        if(arr[i].startsWith("\"") && arr[i].endsWith("\""))
          arr[i] = arr[i].substring(1, arr[i].length() - 1);
      }
    }
  }
  
  // how many rows in the data
  int getRowCount(){
    return rowCount;
  }
  
  // the name of a specific row
  String getRowName(int index){
    return rowNames[index];
  }
  
  // all row names
  String[] getRowNames(){
    return rowNames;
  }

  // find a row number from its name, or else -1
  int getRowIndex(String name){
    for(int i = 0; i < rowCount; i++){
      if(rowNames[i].equals(name))
        return i;
    }
    
    // no name found
    return -1;
  }
  
  // how many columns in the data
  int getColCount(){
    return colCount;
  }
  
  // the name of a specific column
  String getColName(int index){
    return colNames[index];
  }
  
  // all column names
  String[] getColNames(){
    return colNames;
  }
  
  // return a data item at the specified location
  float getFloat(int row, int col){
    return data[row][col];
  }
  
  // check if specified location is valid
  boolean isValid(int row, int col){
    if(row < 0)
      return false;
    if(row >= rowCount)
      return false;
    if(col >= data[row].length)
      return false;
    if(col < 0)
      return false;
    return !Float.isNaN(data[row][col]);
  }
  
  // get the minimum column value
  float getColMin(int col){
    float min = Float.MAX_VALUE;
    for(int i = 0; i < rowCount; i++)
      if(!Float.isNaN(data[i][col]))
        if(data[i][col] < min)
          min = data[i][col];
    return min;
  }
  
  // get the maximum column value
  float getColMax(int col){
    float max = -Float.MAX_VALUE;
    for(int i = 0; i < rowCount; i++)
      if(isValid(i, col))
        if (data[i][col] > max)
          max = data[i][col];
    return max;
  }
  
  // get the minimum row value
  float getRowMin(int row){
    float min = Float.MAX_VALUE;
    for(int i = 0; i < colCount; i++)
      if(isValid(row, i))
        if(data[row][i] < min)
          min = data[row][i];
    return min;
  }
  
  // get the maximum row value
  float getRowMax(int row){
    float max = -Float.MAX_VALUE;
    for(int i = 1; i < colCount; i++)
      if(!Float.isNaN(data[row][i]))
        if(data[row][i] > max)
          max = data[row][i];
    return max;
  }
  
  // get the smallest value
  float getTableMin(){
    float min = Float.MAX_VALUE;
    for(int i = 0; i < rowCount; i++)
      for(int j = 0; j < colCount; j++)
        if(isValid(i, j))
          if(data[i][j] < min)
            min = data[i][j];
    return min;
  }
  
  // get the maximum value
  float getTableMax(){
    float max = -Float.MAX_VALUE;
    for(int i = 0; i < rowCount; i++)
      for(int j = 0; j < colCount; j++)
        if(isValid(i, j))
          if(data[i][j] > max)
            max = data[i][j];
    return max;
  }
  
  // output the table of floats
  float[][] getData(){
    return data;
  }
}
