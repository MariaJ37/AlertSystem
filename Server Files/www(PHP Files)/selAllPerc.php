<?php
include 'conn.php';

if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
}

$sql = "select roomCode, percentage from batteryPerc;";

$result = $conn->query($sql);

if($result->num_rows > 0) {
	while($rows = $result->fetch_assoc()) {
		echo "roomCode: " .$rows["roomCode"]. "/ percentage: " . $rows["percentage"]. "/ <br>";
	}
} else {
	echo "Error: " . $sql . "<br>" . $conn->error;
}
?>