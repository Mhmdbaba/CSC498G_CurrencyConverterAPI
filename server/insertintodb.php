<?php
include("LiraRate.php");

$server = "localhost";
$username= "root";
$password= "";
$db_name= "currencyconverter_db";

$conn = mysqli_connect($server, $username, $password, $db_name) or die("Connection failed");

//Get the variables from the user in android studio
$amount = $_GET["amount"];
$currency = $_GET["currency"];

//decode json array in LiraRate got from scraping the website
$decoded_r = json_decode($myjson);

//getting the rate
$rate = $decoded_r["buy"];
$updated_amount;

//preparing the query to insert in database
$query = $mysqli->prepare("INSERT INTO history (rate,lbp,usd) VALUES (?,?,?)");

//in the case of USD insert the updated amount in usd column
if ($currency == "usd") {
    $updated_amount = $amount/$rate;
    $query->bind_param("ddd",$rate,$amount,$updated_amount);
}
else{ //in case of lbp inser the updated amount in lbp column
    $updated_amount = $amount*$rate;
    $query->bind_param("ddd",$rate,$updated_amount,$amount);
}

//execute the query to write variables in database
$query->execute();

//encode the updated amount to json and pass
echo json_encode($updated_amount);


?>