<?php
include 'conn.php';

$aID = $_GET["aID"];

if($aID == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "delete from alerts where aID = $aID;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>