<?php

	error_reporting(E_ALL);
	ini_set("display_errors", 1);

	$con = mysqli_connect("AWS mysql db 엔드포인트", "DB username", "DB password", "DB database");
	mysqli_query($con, 'SET NAMES utf8');

	$ID = $_POST["ID"];
	$Password = $_POST["Password"];

	$statement = mysqli_prepare($con, "select * from User where ID = ? AND Password = ?");
	mysqli_stmt_bind_param($statement, "ss", $ID, $Password);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $ID, $Password);

	$response = array();
	$response["success"] = false;

	while(mysqli_stmt_fetch($statement)) 
	{
		$response["success"] = true;
		$response["ID"] = $ID;
		$response["Password"] = $Password;
	}

	echo json_encode($response);

	mysqli_close($con);

?>