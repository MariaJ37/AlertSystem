<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$percentage = $_GET["percentage"];

if($roomCode == NULL || $percentage == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "update batteryPerc set percentage=$percentage where roomCode=$roomCode;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>