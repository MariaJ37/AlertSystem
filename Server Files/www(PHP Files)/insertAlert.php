<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$alert = $_GET["alert"];
$timeOf = $_GET["timeOf"];

if($roomCode == NULL || $timeOf == NULL || $alert == NULL){
echo "Field NULL";
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "insert into alerts(roomCode, alert, timeOf) values ($roomCode, $alert, $timeOf);";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>