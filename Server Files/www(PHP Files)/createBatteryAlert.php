
<?php
include 'conn.php';

$alertPerc = $_GET["alertPerc"];

if($alertPerc == NULL){
} else {
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}

	$sql = "select sub2.roomCode 
from (
	select sub.roomCode
	from (
		select bp.roomCode, pi2.patientName, bp.percentage 
		from batteryPerc bp
		left outer join patientInfo pi2 on bp.roomCode = pi2.roomCode) as sub
	where $alertPerc > sub.percentage AND sub.patientName is not NULL) as sub2 left outer join alerts a on sub2.roomCode = a.roomCode
	where a.alert is null;";

	$result = $conn->query($sql);

	if($result->num_rows > 0) {
		$alert = "insert into alerts(roomCode, alert, timeOf) values ";
		while($rows = $result->fetch_assoc()) {
			$t = time();
			$dateTime = date("Y-m-d H:i:s");
			$roomCode = $rows["roomCode"];
			$alertVal .= "($roomCode, 'Battery Percent Low', '$dateTime'),";			
		}
		$alert .= substr($alertVal, 0, -1). ";";
		mysqli_query($conn, $alert, MYSQLI_ASYNC);
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
}
?>
