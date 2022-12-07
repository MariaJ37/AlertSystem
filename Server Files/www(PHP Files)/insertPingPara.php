<?php
include 'conn.php';

$pingPara = $_GET["pingPara"];

$sel = "select * from alertParameters where paraID = 1;";



if($pingPara == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "insert into alertParameters(ping) values ($pingPara);";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update alertParameters set ping = $pingPara where paraID = 1;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>