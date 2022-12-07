<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];

if($roomCode == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "delete from wifiStatus where roomCode = $roomCode;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>