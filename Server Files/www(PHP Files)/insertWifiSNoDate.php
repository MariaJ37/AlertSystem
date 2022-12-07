<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$sel = "select * from wifiStatus where roomCode = $roomCode;";



if($roomCode == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$t = time();
	$dateTime = date("Y-m-d H:i:s");	
	

	$sql = "insert into wifiStatus(roomCode, lastPing) values ($roomCode, '$dateTime');";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update wifiStatus set lastPing = '$dateTime' where roomCode=$roomCode;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>