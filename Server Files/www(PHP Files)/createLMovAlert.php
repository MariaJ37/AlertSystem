
<?php
include 'conn.php';

$alertTime = $_GET["alertTime"];

if($alertTime == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$timeNow = time();
	$dateTimeNow = date("Y-m-d H:i:s", $timeNow);
	$dateTimeCheck = date("Y-m-d H:i:s", strtotime($dateTimeNow.'-'.$alertTime.' hours'));

	$sql = "select sub2.roomCode
from (
	select sub.roomCode
	from (
		select lm.roomCode, pi2.patientName, lm.timeOfMov 
		from lastMovement lm 
		left outer join patientInfo pi2 on lm.roomCode = pi2.roomCode) as sub
	where '$dateTimeCheck' > sub.timeOfMov AND sub.patientName is not NULL) as sub2 left outer join alerts a on a.roomCode = sub2.roomcode
where a.alert is null;";
	$result = $conn->query($sql);

	if($result->num_rows > 0) {
		$alert = "insert into alerts(roomCode, alert, timeOf) values ";

		while($rows = $result->fetch_assoc()) {
			$t = time();
			$dateTime = date("Y-m-d H:i:s");
			$roomCode = $rows["roomCode"];
			$alertVal .= "($roomCode, 'No Movement Alert', '$dateTime'),";
		}
		$alert .= substr($alertVal, 0, -1). ";";
		mysqli_query($conn, $alert, MYSQLI_ASYNC);

	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>
