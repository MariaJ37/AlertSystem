<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];

if($roomCode == NULL){;
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "select roomCode, lastPing from wifiStatus where roomCode=$roomCode;";
	$result = $conn->query($sql);
	
	if($result->num_rows > 0) {
		while($rows = $result->fetch_assoc()) {
			echo "roomCode: " .$rows["roomCode"]. "/ lastPing: " . $rows["lastPing"]. "/ <br>";
		}
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

}

?>