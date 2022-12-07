<?php
include 'conn.php';

$percPara = $_GET["percPara"];

$sel = "select * from alertParameters where paraID = 1;";



if($percPara == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "insert into alertParameters(percent) values ($percPara);";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update alertParameters set percent = $percPara where paraID = 1;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>