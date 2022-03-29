<?php
include("LiraRate.php");

$server = "localhost";
$username= "root";
$password= "";
$db_name= "currencyconverter_db";

$conn = mysqli_connect($server, $username, $password, $db_name) or die("Connection failed");


$amount = $_POST["amount"];
$currency = $_POST["currency"];

$decoded_r = json_decode($myjson);

$rate = $decoded_r["buy"];
$updated_amount;

if ($currency == "usd") {
    $updated_amount = $amount/$rate;
}
else{
    $updated_amount = $amount*$rate;
}


$query = $mysqli->prepare("INSERT INTO history (rate,lbp,usd) VALUES (?,?,?)");
$query->bind_param("ddd",$rate,$updated_amount,$amount);
$query->execute();

echo json_encode($updated_amount);


?>