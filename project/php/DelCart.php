<?php     
//    $con = mysqli_connect("localhost", "gdkgate", "kgd951753#", "gdkgate") or die("MySqlDB 접속 실패 !!");
    $con = mysqli_connect("AWS mysql db 엔드포인트", "DB username", "DB password", "DB database") or die("MySqlDB 접속 실패 !!");
    mysqli_query($con,'SET NAMES utf8');

    $cart_id = isset($_POST["cart_id"]) ? $_POST["cart_id"] : "";      
/*   
    $cart_id = isset($_GET["cart_id"]) ? $_GET["cart_id"] : "";    
*/  
    $statement = mysqli_prepare($con, " Delete from t_shopping_cart Where cart_id=? ");
    mysqli_stmt_bind_param($statement, "i", $cart_id); // sssi는 string 3개 int 1개
    mysqli_stmt_execute($statement);

    mysqli_close( $con );            

    // Check if the query was successful
    if (mysqli_stmt_affected_rows($statement) > 0) {
        $response["success"] = true;     
    } else {
        $response["success"] = false;      
    }

    echo json_encode($response);

    /*
         추가 아래 추가하지않으면 키값도 인서트해야됨
         ALTER TABLE t_shopping_cart MODIFY CART_ID BIGINT NOT NULL AUTO_INCREMENT; 
    */
?>