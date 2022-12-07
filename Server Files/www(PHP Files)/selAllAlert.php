<?php
include 'conn.php';


if(false){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "select aID, roomCode, alert, timeOf, dismissed from alerts;";
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