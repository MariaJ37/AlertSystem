<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];

if($roomCode == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "select aID, roomCode, alert, timeOf, dismissed from alerts where roomCode=$roomCode;";
	$result = $conn->query($sql);

	if($result->num_rows > 0) {
		while($rows = $result->fetch_assoc()) {
			echo "aID: " .$rows["aID"]. "/ roomCode: " .$rows["roomCode"]. "/ alert: ". $rows["alert"]. "/ timeOf: " . $rows["timeOf"]. "/ dismissed: " .$rows["dismissed"].  "/ <br>";
		}
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>