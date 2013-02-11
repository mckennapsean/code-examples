<?php
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

// works with contact.php
// not intended to be used alone

// get variables to send the email
$email = $_REQUEST['email'];
$message = $_REQUEST['message'];

// for no email provided, send them back to the form
if(!isset($_REQUEST['email'])){
  header("Location: form.php");

// for an empty message, display form error (not the cleanest)
}else if(empty($email) || empty($message) || preg_match("/[\r\n]/", $email)){
  header("Expires: Mon, 20 Dec 1998 01:00:00 GMT");
  header("Last-Modified: " . gmdate("D,d M Y H:i:s") . "GMT");
  header("Cache-Control: no-cache, must-revalidate");
  header("Pragma: no-cache");
?>
  <html>
    <head> <title> Error </title> </head>
    <body>
      <h1> Error </h1>
      <p> You did not properly complete your form. Please try again. </p>
    </body>
  </html>
<?php

// otherwise, send the email to the point of contact
// and then send the user on to a landing page
}else{
  mail("MYEMAIL@DOMAIN.COM", "Feedback Form Results", $message, "From: $email");
  header("Location: thankYou.php");
}
?>
