<?php
include 'conn.php';

$notificationPara = $_GET["notificationPara"];

$sel = "select * from alertParameters where paraID = 1;";



if($notificationPara == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "insert into alertParameters(notification) values ($notificationPara);";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update alertParameters set notification = $notificationPara where paraID = 1;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>