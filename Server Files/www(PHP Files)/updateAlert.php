<?php
include 'conn.php';

$aID = $_GET["aID"];
$alert = $_GET["alert"];
$timeOf = $_GET["timeOf"];
$dismissed = $_GET["dismissed"];

if($aID == NULL || $timeOf == NULL || $alert == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "update alerts set timeOf=$timeOf, alert=$alert, dismissed=$dismissed where aID=$aID;";

	if($conn->query($sql) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>