<?php

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
