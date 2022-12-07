<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$lastPing = $_GET["lastPing"];
$sel = "select * from wifiStatus where roomCode = $roomCode;";



if($roomCode == NULL || $lastPing == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "insert into wifiStatus(roomCode, lastPing) values ($roomCode, '$lastPing');";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update wifiStatus set lastPing = '$lastPing' where roomCode=$roomCode;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>