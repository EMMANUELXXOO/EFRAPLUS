<?php
$mysql = new mysqli("localhost", "root", "Psicologia7487", "frapplus");

if ($mysql->connect_error) {
    die("Error de conexión: ");
} else {
    echo "Conexión exitosa";
}
?>
