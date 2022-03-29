<?php
$url = 'https://lirarate.org/wp-json/lirarate/v2/rates?currency=LBP&_ver=t202232922';

$ch = curl_init();

curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_URL,$url);

$res = curl_exec($ch);

if ($e = curl_error($ch)) {
  echo $e;
}
else {
  //decode the arry from json
  $decoded = json_decode($res, true);

  //get the size of arrays
  $sizebuy = sizeof($decoded['buy']);
  $sizesell = sizeof($decoded['sell']);

  $response = array();
  //put values in variables
  $buy = $decoded['buy'][$sizebuy-1]['1'];
  $sell = $decoded['sell'][$sizesell-1]['1'];

    //specidy the type of content, which is json
    header("Content-Type: application/json");
    $myobj = array ('buy'=>$buy,'sell'=>$sell);

    //encode the array to json
    $myjson = json_encode($myobj);

    echo $myjson;

}

curl_close($ch);
?>