<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];
$patientName = $_GET["patientName"];
$address = $_GET["address"];
$phone = $_GET["phone"];
$emergencyName = $_GET["emergencyName"];
$emergencyPhone = $_GET["emergencyPhone"];

if($roomCode == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$update = "update patientInfo set address = $address, patientName = $patientName, 
		phone = $phone, emergencyName=$emergencyName, emergencyPhone=$emergencyPhone where roomCode=$roomCode;";
	
	if($conn->query($update) === TRUE) {
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

}
?>