<?php
include 'conn.php';

if(false){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "select percent, ping, move, notification from alertParameters where paraID = 1;";
	$result = $conn->query($sql);

	if($result->num_rows > 0) {
		while($rows = $result->fetch_assoc()) {
			echo "percentPara: " .$rows["percent"]. "/ pingPara: " . $rows["ping"]. "/ movePara: " . $rows["move"]. "/ notificationPara: " . $rows["notification"]."/ <br>";
		}
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>
