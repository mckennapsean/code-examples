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

// supports Date.java to query the user for a date string
// this is a specific object representing the user input of a date

public class MyDate{
  
  // date (string away) holding months, days, years
  // this is used for the DateFormatException
  public String[] date = new String[3];
  
  // combines the data from the date array to create a formatted output
  private String dateString;
  
  // parses input string into the date array, using the date format exception
  public MyDate(String s){
	  s = s.replace(" ", "");
    date = s.split("/");
    try{
      String test = date[0] + "/" + date[1] + "/" + date[2];
    }catch(ArrayIndexOutOfBoundsException a){
      throw new DateFormatException("Slash Error: must have exactly two.");
    }
  }
  
  // check the month for a format exception
  public int getMonth(){
    int month = 0;
    try{
      month = Integer.parseInt(date[0]);
    }catch(NumberFormatException nfe){
      throw new DateFormatException("Month Not A Number: " + date[0]);
    }
    if(month > 12 || month < 1){
      throw new DateFormatException("Month Input Error: "+date[0]);
    }
    return month;
  }
  
  // check the day for a format exception
  public int getDay(){
    int day = 0;
    try{
      day = Integer.parseInt(date[1]);
    }catch(NumberFormatException nfe){
      throw new DateFormatException("Day Not A Number: " + date[1]);
    }
    if(day > 31 || day < 1){
      throw new DateFormatException("Day Input Error: " + date[1]);
    }
    return day;
  }
  
  // check the year for a format exception
  public int getYear(){
    int year = 0;
    try{
      year = Integer.parseInt(date[2]);
    }catch(NumberFormatException nfe){
      throw new DateFormatException("Year Not A Number: " + date[2]);
    }
    return year;
  }
  
  // return a string of the properly formatted date
  @Override
  public String toString(){
    int month = this.getMonth();
    int day = this.getDay();
    int year = this.getYear();
    String m = "";
    String d = "";
    if(month < 10 && month > 0)
      m = "0";
    if(day < 10 && day > 0)
      d = "0";
    dateString = m + month + "/" + d + day + "/" + year;
    return dateString;
  }
}
