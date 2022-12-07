<?php
include 'conn.php';

$username = $_GET["username"];

if($username == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "select * from users where username=$username;";
	$result = $conn->query($sql);

	if($result->num_rows > 0) {
		while($rows = $result->fetch_assoc()) {
			echo "username: " .$rows["username"]. "/ roomCode: " . $rows["roomCode"]. "/ <br>";
		}
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>