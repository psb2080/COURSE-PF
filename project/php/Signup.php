<?php

	error_reporting(E_ALL);
	ini_set("display_errors", 1);

	$con = mysqli_connect("AWS mysql db 엔드포인트", "DB username", "DB password", "DB database");
	mysqli_query($con, 'SET NAMES utf8');

    $userID = isset($_POST["member_id"]) ? $_POST["member_id"] : "";
    $userPassword = isset($_POST["member_pw"]) ? $_POST["member_pw"] : "";

	$statement = mysqli_prepare($con, "INSERT into t_shopping_member (member_id,member_pw) values (?,?)");
	mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);

	mysqli_close($con);

?>