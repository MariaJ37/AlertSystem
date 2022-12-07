<?php
include 'conn.php';

$movePara = $_GET["movePara"];

$sel = "select * from alertParameters where paraID = 1;";



if($movePara == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "insert into alertParameters(move) values ($movePara);";
	
	if($conn->query($sql) === TRUE) {
	} else {
		$update = "update alertParameters set move = $movePara where paraID = 1;";
		
		if($conn->query($update) === TRUE) {
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}

}
?>