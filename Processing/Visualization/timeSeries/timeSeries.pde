// by Sean McKenna
// inspired by Ben Fry's Visualizing Data book
// displays some time-series data using a simple line chart

FloatTable data;
float dataMin, dataMax;

float plotX1, plotY1;
float plotX2, plotY2;

int currentCol = 0;
int colCount;

int rowCount;

int yearMin, yearMax;
int[] years;
int yearInterval = 10;

int volumeInterval = 10;
int volumeIntervalMinor = 5;

PFont titleFont;
PFont dataFont;
float barWidth = 4;

// whether to draw plot lines
boolean plotLines = true;

// whether to plot the highlighted points at start
boolean highlight = false;

// plot mode
int plotMode = 3;

// colors for plot
color c1 = color(117, 112, 179);
color c2 = color(27, 158, 119);
color c3 = color(217, 95, 2);
color c4 = color(141, 160, 203);
color c5 = color(102, 194, 165);
color c6 = color(252, 141, 98);
color c7 = color(231, 41, 138);
color c8 = color(231, 138, 95);

// store tab info
float[] tabLeft, tabRight;
float tabTop, tabBottom;
float tabPad = 10;
float tabSpacing = 25;

// initialize interpolator for interpolation
Interpolator[] interpolators;

// whether in all-data view mode
boolean viewAll = false;

void setup(){
  size(720, 405);
  
  data = new FloatTable("milk-tea-coffee.csv");
  colCount = data.getColCount();
  
  years = int(data.getRowNames());
  yearMin = years[0];
  yearMax = years[years.length - 1];
  
  dataMin = 0;
  dataMax = data.getTableMax();
  
  // grab row count
  rowCount = data.getRowCount();
  
  // set up interpolators for each dataset
  interpolators = new Interpolator[rowCount];
  for(int row = 0; row < rowCount; row++){
    float initialValue = data.getFloat(row, 0);
    interpolators[row] = new Interpolator(initialValue);
  }
  
  // corners of the plotted time series
  plotX1 = 50;
  plotX2 = width - plotX1;
  plotY1 = 60;
  plotY2 = height - plotY1;
  
  titleFont = createFont("Verdana", 20);
  dataFont = createFont("Georgia", 20);
  
  smooth();
}

void draw(){
  // update interpolators
  for(int row = 0; row < rowCount; row++){
    interpolators[row].update();
  }

  background(255);
  
  // plot area is a white box
  drawPlot();
  
  // draw titles on the plot
  drawTitleTabs();
  drawTitles(currentCol);
  
  // draw data for the current column
  if(plotMode == 0){
    drawDataPoints(currentCol);
  }else if(plotMode == 1){
    drawDataCurve(currentCol);
    drawDataPoints(currentCol);
  }else if(plotMode == 2){
    drawDataLine(currentCol);
  }else if(plotMode == 3){
    drawDataArea(currentCol);
    drawDataCurve(currentCol);
  }else{
    highlight = false;
    plotLines = false;
    drawAllData();
  }
  
  // draw axes
  drawYearLabels();
  drawVolumeLabels();
  
  // only draw highlight when dragging mouse
  if(highlight)
    drawDataHighlight(currentCol);
}

void keyPressed(){
  if(key == '[' && !viewAll){
    int col = currentCol - 1;
    if(col < 0)
      col = colCount - 1;
    setColumn(col);
  
  }else if(key == ']' && !viewAll){
    int col = currentCol + 1;
    if(col == colCount)
      col = 0;
    setColumn(col);
  
  }else if(key == '1' && !viewAll){
    plotMode = 0;
    plotLines = false;
  
  }else if(key == '2' && !viewAll){
    plotMode = 1;
    plotLines = false;
  
  }else if(key == '3' && !viewAll){
    plotMode = 2;
    plotLines = false;
  
  }else if(key == '4' && !viewAll){
    plotMode = 3;
    plotLines = true;
  
  }else if(key == '5'){
    plotMode = 4;
    plotLines = false;
    highlight = false;
    viewAll = true;
  }
}

// if clicking on the tabs, detect which
void mousePressed(){
  if(mouseY > tabTop && mouseY < tabBottom){
    mouseClicked();
    for(int col = 0; col < colCount; col++){
      if(mouseX > tabLeft[col] && mouseX < tabRight[col]){
        if(viewAll){
          plotMode = 3;
          viewAll = false;
        }
        setColumn(col);
      }
    }
    if(mouseX > tabLeft[colCount] && mouseX < tabRight[colCount]){
      plotMode = 4;
      viewAll = true;
    }
  }
}

// activate data highlights with mouse click
void mouseClicked(){
  if(highlight)
    highlight = false;
  else
    highlight = true;
}

// draw the plot background
void drawPlot(){
  fill(255);
  noStroke();
  rectMode(CORNERS);
  rect(plotX1, plotY1, plotX2, plotY2);
}

// draw the plot title
void drawTitles(int col){
  textFont(titleFont);
  fill(0);
  textSize(10);
  String yAxisTitle = "gallons consumed per capita";
  pushMatrix();
  translate(plotX1 - 30, plotY1 - 3 * (plotY1 - plotY2) / 4);
  rotate(-HALF_PI);
  text(yAxisTitle, 0, 0);
  popMatrix();
  textAlign(TOP);
  String xAxisTitle = "year";
  text(xAxisTitle, plotX1 + (plotX2 - plotX1) / 2, plotY2 + 40);
}

// draw the tab titles
void drawTitleTabs(){
  rectMode(CORNERS);
  noStroke();
  textSize(20);
  textAlign(LEFT);
  
  // on first use, allocate space
  // stores values for the tab edges
  if(tabLeft == null){
    tabLeft = new float[colCount + 1];
    tabRight = new float[colCount + 1];
  }
  
  float runningX = plotX1 + 125;
  tabTop = plotY1 - textAscent() - 25;
  tabBottom = plotY1 - 10;
  
  for(int col = 0; col < colCount; col++){
    String title = data.getColName(col);
    tabLeft[col] = runningX;
    float titleWidth = textWidth(title);
    tabRight[col] = tabLeft[col] + tabPad + titleWidth + tabPad;
    
    // if current tab, set background color
    setDataFillColor(col);
    if(!viewAll)
      if(col != currentCol)
        fill(230);
    rect(tabLeft[col], tabTop, tabRight[col], tabBottom, 25);
    
    // if current tab, white text
    if(!viewAll)
      fill(col == currentCol ? 255 : 175);
    else
      fill(255);
    text(title, runningX + tabPad, plotY1 - 20);
    
    runningX = tabRight[col] + tabSpacing;
  }
  
  // for viewing all datasets
  String title = "All";
  tabLeft[colCount] = runningX + 50;
  float titleWidth = textWidth(title);
  tabRight[colCount] = tabLeft[colCount] + tabPad + titleWidth + tabPad;
  if(viewAll)
    fill(75);
  else
    fill(230);
  rect(tabLeft[colCount], tabTop, tabRight[colCount], tabBottom, 25);
  if(viewAll)
    fill(255);
  else
    fill(175);
  text(title, runningX + tabPad + 50, plotY1 - 20);
}

// draw the year labels
void drawYearLabels(){
  textFont(dataFont);
  fill(0);
  textSize(10);
  textAlign(CENTER, TOP);
  
  // use thin, white lines to draw grid
  stroke(255);
  strokeWeight(1);
  
  if(plotLines){
    for(int row = 0; row < rowCount; row++){
      if(years[row] % yearInterval == 0){
        float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
        text(years[row], x, plotY2 + 10);
        line(x, plotY1, x, plotY2);
      }
    }
  
  }else{
    for(int row = 0; row < rowCount; row++){
      if(years[row] % yearInterval == 0){
        float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
        text(years[row], x, plotY2 + 10);
      }
    }
  }
}

// draw the volume labels
void drawVolumeLabels(){
  textFont(dataFont);
  fill(0);
  textSize(10);
  stroke(128);
  strokeWeight(1);
  float dataFirst = dataMin + volumeInterval;
  for(float v = dataMin; v <= dataMax; v += volumeIntervalMinor){
    // minor tick mark
    if(v % volumeIntervalMinor == 0){
      float y = map(v, dataMin, dataMax, plotY2, plotY1);
      // major tick mark (and label)
      if(v % volumeInterval == 0){
        if(v == dataMin)
          textAlign(RIGHT);
        else if(v == dataMax)
          textAlign(RIGHT, TOP);
        else
          textAlign(RIGHT, CENTER);
        text(floor(v), plotX1 - 10, y);
        line(plotX1 - 5, y, plotX1, y);
      }else{
        //line(plotX1 - 2, y, plotX1, y);
      }
    }
  }
}

// draw the data as a series of points
void drawDataPoints(int col){
  setDataColor(col);
  strokeWeight(5);
  for(int row = 0; row < rowCount; row++){
    if(data.isValid(row, col)){
      float value;
      if(viewAll)
        value = data.getFloat(row, col);
      else
        value = interpolators[row].value();
      float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
      float y = map(value, dataMin, dataMax, plotY2, plotY1);
      point(x, y);
    }
  }
}

// draw the data as a line
void drawDataLine(int col){
  setDataColor(col);
  strokeWeight(3);
  noFill();
  beginShape();
  for(int row = 0; row < rowCount; row++){
    if(data.isValid(row, col)){
      float value = interpolators[row].value();
      float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
      float y = map(value, dataMin, dataMax, plotY2, plotY1);
      vertex(x, y);
    }
  }
  endShape();
}

// draw the data as a curve
void drawDataCurve(int col){
  setDataColor(col);
  strokeWeight(3);
  noFill();
  beginShape();
  for(int row = 0; row < rowCount; row++){
    if(data.isValid(row, col)){
      float value;
      if(viewAll)
        value = data.getFloat(row, col);
      else
        value = interpolators[row].value();
      float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
      float y = map(value, dataMin, dataMax, plotY2, plotY1);
      
      // double curve points for start and stop
      curveVertex(x, y);
      if((row == 0) || (row == rowCount - 1))
        curveVertex(x, y);
    }
  }
  endShape();
}

// draw the data as a filled area
void drawDataArea(int col){
  setDataFillColor(col);
  noStroke();
  beginShape();
  for(int row = 0; row < rowCount; row++){
    if(data.isValid(row, col)){
      float value = interpolators[row].value();
      float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
      float y = map(value, dataMin, dataMax, plotY2, plotY1);
      vertex(x, y);
    }
  }
  vertex(plotX2, plotY2);
  vertex(plotX1, plotY2);
  endShape();
}

// draw the data as a bar graph
void drawDataBars(int col){
  setDataColor(col);
  setDataFillColor(col);
  beginShape();
  for(int row = 0; row < rowCount; row++){
    if(data.isValid(row, col)){
      float value = interpolators[row].value();
      float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
      float y = map(value, dataMin, dataMax, plotY2, plotY1);
      rect(x - barWidth / 2, y, x + barWidth / 2, plotY2);
    }
  }
  endShape();
}

// draw data highlight for a point
void drawDataHighlight(int col){
  setDataColor(col);
  for(int row = 0; row < rowCount; row++){
    if(data.isValid(row, col)){
      float value = interpolators[row].value();
      float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
      float y = map(value, dataMin, dataMax, plotY2, plotY1);
      
      // only highlight point if near mouse
      if(dist(mouseX, 0, x, 0) < 3){
        strokeWeight(10);
        point(x, y);
        fill(0);
        textFont(dataFont);
        textSize(14);
        textAlign(CENTER);
        text(nf(value, 0, 2) + "  (" + years[row] + ")", x, y-8);
      }
    }
  }
}

// display all data at once
void drawAllData(){
  // milk data
  drawDataCurve(0);
  drawDataPoints(0);
  
  // tea data
  drawDataCurve(1);
  drawDataPoints(1);
  
  // coffee data
  drawDataCurve(2);
  drawDataPoints(2);
}

// set the color for the data and title
void setDataColor(int col){
  if(col == 0){
    fill(c1);
    stroke(c1);
  }else if(col == 1){
    fill(c2);
    stroke(c2);
  }else{
    fill(c3);
    stroke(c3);
  }
}

// set color for filled areas
void setDataFillColor(int col){
  if(col == 0){
    fill(c4);
  }else if(col == 1){
    fill(c5);
  }else{
    fill(c6);
  }
}

// set the current column
void setColumn(int col){
  if(col != currentCol)
    currentCol = col;
  
  if(!viewAll)
    for(int row = 0; row < rowCount; row++){
      interpolators[row].enable(data.getFloat(row, col));
    }
}
