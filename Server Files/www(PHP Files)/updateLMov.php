<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$timeOfMov = $_GET["timeOfMov"];

if($roomCode == NULL || $timeOfMov == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "update lastMovement set timeOfMov=$timeOfMov where roomCode=$roomCode;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>