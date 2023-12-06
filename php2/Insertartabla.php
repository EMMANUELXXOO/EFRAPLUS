<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $id=$_POST["id"];
    $Clave=$_POST["Clave"];
    $Status=$_POST["Status"];
    $FechaCreacion=$_POST["FechaCreacion"];
    $FechadeMoficacion=$_POST["FechadeMoficacion"];			
    $query="INSERT INTO fraporden (id,Clave,Status,FechaCreacion,FechadeMoficacion) VALUES('".$id."','".$Clave."','".$Status."','". $FechaCreacion."','".$FechadeMoficacion."')";
    
    $resultado=$mysql->query($query);

    if($resultado==true){
        echo"el usuario se inserto correctamente";
    }else{
        echo"error al insertar";
    }

}