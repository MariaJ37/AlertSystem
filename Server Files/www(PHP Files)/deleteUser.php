<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$username = $_GET["username"];

if($roomCode == NULL || $username == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "delete from users where username = $username and roomCode=$roomCode;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>