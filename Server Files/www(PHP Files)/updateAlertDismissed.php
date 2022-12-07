<?php
include 'conn.php';

$aID = $_GET["aID"];
$dismissed = $_GET["dismissed"];

if($aID == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "update alerts set dismissed=$dismissed where aID=$aID;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>