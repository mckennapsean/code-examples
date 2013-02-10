// by Sean McKenna
// displays a data set using parallel coordinates

// dataset info
String dataSet = "cars";
String fileName = dataSet + ".csv";
boolean cluster = true;
FloatTable table;
float[][] data;

// row, column info
String[] colNames;
int col = 0;
int colTot;
String[] rowNames;
int row = 0;
int rowTot;

// data values info
float[] colMin, colMax, colRan, colLow, colHigh;

// color info
color light = color(248, 248, 248);
color lightTransp = color(248, 248, 248, 32);
color dark = color(7, 7, 7);
color color0 = color(228, 26, 28, 32);
color color1 = color(55, 126, 184, 32);
color color2 = color(77, 175, 74, 32);
color color3 = color(152, 78, 163, 32);
color color4 = color(255, 127, 0, 32);
color color5 = color(255, 255, 51, 32);
color color6 = color(166, 86, 40, 32);

// gui variables
int topBorder = 63;
int border = 17;
int axisH;
int axesW;
float axesSep;
float[] axes;

// interactivity variables
boolean[] invert;
boolean brushing;
float brushY;
int brushCol;
boolean moving;
int moveCol;

// initialization
void setup(){
  // set up canvas
  size(720, 405);
  smooth();
  
  // grab our data
  table = new FloatTable(fileName);
  data = table.getData();
  rowNames = table.getRowNames();
  colNames = table.getColNames();
  colTot = table.getColCount();
  rowTot = table.getRowCount();
  
  // store all min/max values and ranges
  colMin = new float[colTot];
  colMax = new float[colTot];
  colRan = new float[colTot];
  colLow = new float[colTot];
  colHigh = new float[colTot];
  invert = new boolean[colTot];
  int j;
  for(j = 0; j < colTot; j++){
    colMin[j] = table.getColMin(j);
    colMax[j] = table.getColMax(j);
    colLow[j] = colMin[j];
    colHigh[j] = colMax[j];
    colRan[j] = colMax[j] - colMin[j];
    invert[j] = false;
  }
  
  // set gui variables
  axisH = height - topBorder - border - 24;
  axesW = width - 2 * border;
  axes = new float[colTot];
  
  // determine axes distance
  axesSep = (float) axesW / colTot;
  int i;
  for(i = 0; i < colTot; i++){
    if(i == 0)
      axes[i] = axesSep / 2 + border;
    else
      axes[i] = axes[i-1] + axesSep;
  }
  
  // set up interactive variables
  brushing = false;
}

// draw cycle
void draw(){
  // redraw dark canvas
  background(dark);
  
  // draw and label all axes
  strokeWeight(3);
  stroke(light);
  textAlign(CENTER);
  int k;
  for(k = 0; k < colTot; k++){
    strokeWeight(3);
    line(axes[k], topBorder, axes[k], topBorder + axisH);
    textSize(8);
    if(!invert[k]){
      text(int(colMax[k]), axes[k], topBorder - 5);
      text(int(colMin[k]), axes[k], topBorder + axisH + 11);
    }else{
      text(int(colMin[k]), axes[k], topBorder - 5);
      text(int(colMax[k]), axes[k], topBorder + axisH + 11);
    }
    textSize(9);
    text(colNames[k], axes[k], topBorder + axisH + 24);
    noFill();
    strokeWeight(1);
    arc(axes[k], topBorder - border - 5, 12, 12, PI, TWO_PI);
    if(!invert[k]){
      line(axes[k] - 6, topBorder - border - 5, axes[k] - 6 - 2, topBorder - border - 5 - 2);
      line(axes[k] - 6, topBorder - border - 5, axes[k] - 6 + 2, topBorder - border - 5 - 2);
    }else{
      line(axes[k] + 6, topBorder - border - 5, axes[k] + 6 - 2, topBorder - border - 5 - 2);
      line(axes[k] + 6, topBorder - border - 5, axes[k] + 6 + 2, topBorder - border - 5 - 2);
    }
  }
    
  // draw data
  strokeWeight(1);
  int i;
  float y0 = 0;
  float y1 = 0;
  for(i = 0; i < rowTot; i++){
    boolean filter = false;
    // set color (varies if clustering data)
    if(!cluster)
      stroke(lightTransp);
    else
      if(data[i][0] == 0)
        stroke(color0);
      else if(data[i][0] == 1)
        stroke(color1);
      else if(data[i][0] == 2)
        stroke(color2);
      else if(data[i][0] == 3)
        stroke(color3);
      else if(data[i][0] == 4)
        stroke(color4);
      else if(data[i][0] == 5)
        stroke(color5);
      else if(data[i][0] == 6)
        stroke(color6);
    int l;
    for(l = 0; l < colTot; l++){
      if(data[i][l] > colHigh[l] || data[i][l] < colLow[l])
        filter = true;
    }
    int j;
    if(!filter)
      for(j = 0; j < colTot; j++){
        y0 = y1;
        if(!invert[j])
          y1 = topBorder + axisH - ((float) axisH * (data[i][j] - colMin[j]) / colRan[j]);
        else
          y1 = topBorder + axisH - ((float) axisH * (colMax[j] - data[i][j]) / colRan[j]);
        if(j != 0)
          line(axes[j-1], y0, axes[j], y1);
      }
  }
  
  // write title
  fill(light);
  textSize(20);
  textAlign(CENTER);
  String title = dataSet;
  text(title, width / 2, border + 5);
}

// mouse interaction: invert axes or brushing data or moving axes
void mousePressed(){
  // detect which column / axis was pressed
  int j;
  int col = -1;
  boolean valid = false;
  for(j = 0; j < colTot; j++){
    if(mouseX > (axes[j] - axesSep / 2) && mouseX < (axes[j] + axesSep / 2)){
      col = j;
      valid = true;
    }
  }

  // inverting axes
  if(mouseY > (topBorder - border - 15) && mouseY < topBorder && valid){
    if(!invert[col])
      invert[col] = true;
    else
      invert[col] = false;
  }
  
  // brushing data
  if(mouseY > topBorder && mouseY < (topBorder + axisH) && valid){
    brushing = true;
    brushY = mouseY;
    brushCol = col;
  }
  
  // reset brush data (click on title)
  if(mouseY < 25){
    int k;
    for(k = 0; k < colTot; k++){
      colLow[k] = colMin[k];
      colHigh[k] = colMax[k];
    }
  }
  
  // moving axes
  if(mouseY > (topBorder + axisH)){
    moving = true;
    moveCol = col;
  }
}

// when releasing mouse: brushing data or moving axes
void mouseReleased(){
  // brushing data
  if(brushing && ((mouseY - brushY) > 3 || (mouseY - brushY) < -3)){
    float val1;
    float val2;
    if(!invert[brushCol]){
      val1 = (float) (topBorder + axisH - brushY) / axisH * colRan[brushCol] + colMin[brushCol];
      val2 = (float) (topBorder + axisH - mouseY) / axisH * colRan[brushCol] + colMin[brushCol];
    }else{
      val1 = colMax[brushCol] - (float) (topBorder + axisH - brushY) / axisH * colRan[brushCol];
      val2 = colMax[brushCol] - (float) (topBorder + axisH - mouseY) / axisH * colRan[brushCol];
    }
    if(val1 > val2){
      colLow[brushCol] = val2;
      colHigh[brushCol] = val1;
    }else{
      colLow[brushCol] = val1;
      colHigh[brushCol] = val2;
    }
    brushing = false;
  
  // moving axes
  }else if(moving){
    // detect which column was released
    int k;
    int mcol = -1;
    boolean valid = false;
    for(k = 0; k < colTot; k++){
      if(mouseX > (axes[k] - axesSep / 2) && mouseX < (axes[k] + axesSep / 2)){
        mcol = k;
        valid = true;
      }
    }
    if(mcol == moveCol){
      valid = false;
    }
    
    // if valid, move the data around
    if(valid){
      float[] movingCol = new float[rowTot];
      float movingMinCol;
      float movingMaxCol;
      float movingRanCol;
      float movingLowCol;
      float movingHighCol;
      String movingNameCol;
      float[] movingCol2 = new float[rowTot];
      float movingMinCol2;
      float movingMaxCol2;
      float movingRanCol2;
      float movingLowCol2;
      float movingHighCol2;
      String movingNameCol2;
      int l;
      for(l = 0; l < rowTot; l++){
        movingCol[l] = data[l][moveCol];
      }
      movingMinCol = colMin[moveCol];
      movingMaxCol = colMax[moveCol];
      movingRanCol = colRan[moveCol];
      movingLowCol = colLow[moveCol];
      movingHighCol = colHigh[moveCol];
      movingNameCol = colNames[moveCol];
      int j;
      for(j = 0; j < colTot; j++){
        int i;
        if(j < moveCol && j < mcol){
          // no change
        }else if(j < moveCol && j == mcol){
          for(i = 0; i < rowTot; i++){
            movingCol[i] = data[i][j];
            data[i][j] = data[i][moveCol];
          }
          movingMinCol = colMin[j];
          movingMaxCol = colMax[j];
          movingRanCol = colRan[j];
          movingLowCol = colLow[j];
          movingHighCol = colHigh[j];
          movingNameCol = colNames[j];
          colMin[j] = colMin[moveCol];
          colMax[j] = colMax[moveCol];
          colRan[j] = colRan[moveCol];
          colLow[j] = colLow[moveCol];
          colHigh[j] = colHigh[moveCol];
          colNames[j] = colNames[moveCol];
        }else if(j >= moveCol && j < mcol){
          for(i = 0; i < rowTot; i++){
            data[i][j] = data[i][j+1];
          }
          colMin[j] = colMin[j+1];
          colMax[j] = colMax[j+1];
          colRan[j] = colRan[j+1];
          colLow[j] = colLow[j+1];
          colHigh[j] = colHigh[j+1];
          colNames[j] = colNames[j+1];
        }else if(j < moveCol && j > mcol){
          for(i = 0; i < rowTot; i++)
            movingCol2[i] = movingCol[i];
          movingMinCol2 = movingMinCol;
          movingMaxCol2 = movingMaxCol;
          movingRanCol2 = movingRanCol;
          movingLowCol2 = movingLowCol;
          movingHighCol2 = movingHighCol;
          movingNameCol2 = movingNameCol;
          for(i = 0; i < rowTot; i++){
            movingCol[i] = data[i][j];
            data[i][j] = movingCol2[i];
          }
          movingMinCol = colMin[j];
          movingMaxCol = colMax[j];
          movingRanCol = colRan[j];
          movingLowCol = colLow[j];
          movingHighCol = colHigh[j];
          movingNameCol = colNames[j];
          colMin[j] = movingMinCol2;
          colMax[j] = movingMaxCol2;
          colRan[j] = movingRanCol2;
          colLow[j] = movingLowCol2;
          colHigh[j] = movingHighCol2;
          colNames[j] = movingNameCol2;
        }else if(j > moveCol && j == mcol){
          for(i = 0; i < rowTot; i++){
            data[i][j] = movingCol[i];
          }
          colMin[j] = movingMinCol;
          colMax[j] = movingMaxCol;
          colRan[j] = movingRanCol;
          colLow[j] = movingLowCol;
          colHigh[j] = movingHighCol;
          colNames[j] = movingNameCol;
        }else if(j == moveCol && j > mcol){
          for(i = 0; i < rowTot; i++){
            data[i][j] = movingCol[i];
          }
          colMin[j] = movingMinCol;
          colMax[j] = movingMaxCol;
          colRan[j] = movingRanCol;
          colLow[j] = movingLowCol;
          colHigh[j] = movingHighCol;
          colNames[j] = movingNameCol;
        }else if(j > moveCol && j > mcol){
          // no change
        }else{println("we shoudl never reach this!");}
      }
    }
    moving = false;
  }
}
