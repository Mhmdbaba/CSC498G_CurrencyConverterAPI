<?php

$server = "localhost";
$username= "root";
$password= "";
$db_name= "currencyconverter_db";

$conn = mysqli_connect($server, $username, $password, $db_name) or die("Connection failed");


/*
connection with database and insert DONE
include('db_info.php');


$sql = "INSERT INTO HISTORY(rate,lbp, usd) VALUES (25000,25001,1)";
$input_stmt = $conn->prepare($sql);
$input_stmt->execute();
*/

?>