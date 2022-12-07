<?php
include 'conn.php';

if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
}

$sql = "select sub.roomCode 
from (
	select roomCode 
	from batteryPerc
	union
	select roomCode 
	from alerts
	union
	select roomCode
	from lastMovement lm 
	union select roomCode 
	from wifiStatus ws 
	union select roomCode 
	from patientInfo) as sub
order by roomCode ASC;";

$result = $conn->query($sql);

if($result->num_rows > 0) {
	while($rows = $result->fetch_assoc()) {
		echo "roomCode: " .$rows["roomCode"]. "/ <br>";
	}
} else {
	echo "Error: " . $sql . "<br>" . $conn->error;
}
?>