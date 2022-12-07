<?php
include 'conn.php';

$roomCode = $_GET["roomCode"];

if($roomCode == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sel = "select roomCode, patientName, address, phone, emergencyName, emergencyPhone from patientInfo where roomCode = $roomCode;";
	$result = $conn->query($sel);

	if($result->num_rows > 0) {
		while($rows = $result->fetch_assoc()) {
			echo "roomCode: " .$rows["roomCode"]. "/ patientName: " .$rows["patientName"]. "/ address: ". $rows["address"]. "/ phone: " . $rows["phone"]. "/ emergencyName: " . $rows["emergencyName"]. "/ emergencyPhone: " . $rows["emergencyPhone"]."/ <br>";
		}
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

}
?>