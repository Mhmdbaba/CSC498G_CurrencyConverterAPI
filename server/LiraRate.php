<?php
/*
this api was done to receive a json object from the api
decode it, get the latest buy and sell rates, then
send them back a json object, but there was a problem in android studio
*/

$url = 'https://lirarate.org/wp-json/lirarate/v2/rates?currency=LBP&_ver=t20224210';

//header("Content-Type: application/json");

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
    $myobj = $buy . " " . $sell;


    echo $myobj;

}

curl_close($ch);
?>