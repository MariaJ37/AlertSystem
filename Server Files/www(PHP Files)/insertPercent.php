
<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$percentage = $_GET["percentage"];

if($roomCode == NULL || $percentage == NULL){
	echo "Null parameters case";
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "insert into batteryPerc(roomCode, percentage) values ($roomCode, $percentage);";

	if($conn->query($sql) === TRUE) {
		
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>
