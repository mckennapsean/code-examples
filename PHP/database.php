<?php
// how to connect to a MySQL database and a variety of database functions



//// Connect to Database ////

// database parameters
$db_host = "database.google.com";
$db_user = "admin";
$db_pswd = "password";
$db_name = "main";

// establish database connection
mysql_connect($db_host, $db_user, $db_pswd);
mysql_select_db($db_name)
  or die( "Cannot select database " . $db_name . ".");



//// General Database Functions ////

// standard database query function
function dbQuery($query, $msg = ""){
  $result = mysql_query($query);
  dbError(mysql_error(), $msg);
  return $result;
}

// displaying error from database query
function dbError($error, $msg = ""){
  if($error){
    echo $error . "<br/>";
    die();
  }else{
    echo $msg;
  }
}

// write information to the database
function dbWrite($table, $fields, $values){
  $query = "INSERT INTO " . $table . " (" . $fields . ") VALUES (" . $values . ")";
  mysql_query($query);
  echo mysql_error();
}

// clean input given by user
function cleanInputs($str){
  return strip_tags(addslashes($str));
}

// clean inputs to be inserted into the database
function cleanForDB($str){
  return trim(mysql_real_escape_string(strip_tags($str)));
}



//// Miscellaneous Functions ////

// current time, midwest time-zone
function getTime(){
  $dateTime = new DateTime(null, new DateTimeZone('America/Chicago'));
  $time = date_format($dateTime, 'Y-m-d H:i:s');
  return $time;
}

// give a number of pages, based on how many items and items per page
function getNumPages($numRows, $n){
  $tot = ceil($numRows / $n);
  return $tot;
}



//// Grab a Database Value ////

// get a name from some ID
function getName($id){
  $query = "SELECT name FROM table WHERE id=$id";
  $result = dbQuery($query);
  while($row = mysql_fetch_array($result)){
    return $row[0];
  }
}

// get a count from some ID
function getCount($id){
  $query = "SELECT COUNT(DISTINCT name) FROM table WHERE id=$id";
  $result = dbQuery($query);
  while($row = mysql_fetch_array($result)){
    return $row[0];
  }
  return 0;
}

// get the most recent time this ID was updated
function getLastUpdate($id){
  $query = "SELECT MAX(time) FROM table WHERE id=$id";
  $result = dbQuery($query);
  while($row = mysql_fetch_array($result)){
    return $row[0];
  }
}



//// Grab a Database Array ////

// get all ID's for a user
function getIDs($user){
  $query = "SELECT DISTINCT id FROM table WHERE user=$user";
  $result = dbQuery($query);
  $IDs = array();
  while($row = mysql_fetch_array($result)){
    array_push($IDs, $row[0]);
  }
  return $IDs;
}



//// Grab a Database Boolean ////

// check for item existence
function getExists($id){
  $query = "SELECT name FROM table WHERE id=$id";
  $result = dbQuery($query);
  while($row = mysql_fetch_array($result)){
    return TRUE;
  }
  return FALSE;
}

// check to see if enough users are tied to an item (3 or more)
function getEnoughUsers($id){
  $query = "SELECT COUNT(DISTINCT user) FROM table WHERE id=$id";
  $result = dbQuery($query);
  while($row = mysql_fetch_array($result)){
    if($row[0] > 2)
      return TRUE;
    else
      return FALSE;
  }
  return FALSE;
}



//// Modify Database Values ////

// create a new item
function createItem($id, $location, $time){
  $table = "table";
  $fields = "id, file_location, created_at, updated_at";
  $values = '"' . $id . '", "' . $location . '", "' . $time . '", "' . $time . '"';
  dbWrite($table, $fields, $values);
}

// update an item
function updateItem($id, $location, $time){
  $query = 'UPDATE table SET file_location="' . $location . '", updated_at="' . $time . '" WHERE id="' . $id . '"';
  mysql_query($query);
  echo mysql_error();
}

// delete an item
function removeItem($user, $id){
  $table = "table";
  $query = "DELETE FROM $table WHERE user=$user AND id=$id";
  $result = mysql_query($query);
  echo mysql_error();
}

?>
