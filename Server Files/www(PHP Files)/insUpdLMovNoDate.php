<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];

if($roomCode == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$t = time();
	$dateTime = date("Y-m-d H:i:s");
	
	$sql = "insert into lastMovement(roomCode, timeOfMov) values ($roomCode, '$dateTime');";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update lastMovement set timeOfMov = '$dateTime' where roomCode=$roomCode;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>