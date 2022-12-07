<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$percentage = $_GET["percentage"];

if($roomCode == NULL || $percentage == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "insert into batteryPerc(roomCode, percentage) values ($roomCode, $percentage);";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update batteryPerc set percentage = $percentage where roomCode=$roomCode;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>