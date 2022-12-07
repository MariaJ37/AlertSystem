<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$alert = $_GET["alert"];

if($roomCode == NULL || $alert == NULL){
echo "Field NULL";
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$t = time();
	$dateTime = date("Y-m-d H:i:s");	


	$sql = "insert into alerts(roomCode, alert, timeOf) values ($roomCode, $alert, '$dateTime');";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>