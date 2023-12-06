<?php
$servername = "127.0.0.1";
$port = 3307; // Cambia esto si el puerto es diferente
$username = "root";
$password = ""; // Cambia esto con tu contraseña real
$dbname = "frapdb";

try {
    $mysql = new mysqli($servername, $username, $password, $dbname, $port);
    // Resto del código...

    echo "Conexión exitosa";

    // Cerrar la conexión al final del script
    $mysql->close();
} catch (mysqli_sql_exception $e) {
    die("Error de conexión: " . $e->getMessage());
}
?>
