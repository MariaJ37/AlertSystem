<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$lastPing = $_GET["lastPing"];

if($roomCode == NULL || $lastPing == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "update wifiStatus set lastPing=$lastPing where roomCode=$roomCode;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>