<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $id=$_POST["id"];
    $url=$_POST["URLSERVIDOR"];
    $user=$_POST["USUARIO"];
    $pass=$_POST["PASWORD"];

    $query="INSERT INTO datosservidores (id,URLSERVIDOR,USUARIO,PASWORD) Value('".$id."','".$url."','".$user."','".$pass."')";
    $resultado=$mysql->query($query);
    if($resultado==true)
    {
        echo"El usuario se inserto de forma correcta";
    }
    else{
        echo"Error al insertar el usuario";
    }
}