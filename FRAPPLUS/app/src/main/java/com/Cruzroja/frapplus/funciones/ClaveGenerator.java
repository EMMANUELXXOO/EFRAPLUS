package com.Cruzroja.frapplus.funciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ClaveGenerator {

    public static String generateUniqueKey() {
        String prefix = "ROS-";
        String generatedKey = generateRandomKey(); // Aquí deberías implementar tu algoritmo de generación de claves

        return prefix + generatedKey;
    }

    private static String generateRandomKey() {
        // Puedes definir aquí tu propio algoritmo para generar una clave
        // Por ejemplo, puedes usar un timestamp y algunos caracteres aleatorios

        // Generar un timestamp único como parte de la clave
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        // Generar una parte aleatoria de la clave
        Random random = new Random();
        String randomPart = String.format("%04d", random.nextInt(10000));

        return timestamp + "-" + randomPart;
    }
}