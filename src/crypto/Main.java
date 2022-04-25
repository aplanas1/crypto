package crypto;

import crypto.utils.*;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Xifrar xifrar = new Xifrar();

        System.out.println("Contraseña automatica:");
        SecretKey keygen = xifrar.keygenKeyGeneration(256);

        String hello = "Hello";
        byte[] bytes = hello.getBytes();

        System.out.println(xifrar.encryptData(keygen,bytes));


        try {
            String decrypt = new String(xifrar.decryptData(keygen,xifrar.encryptData(keygen,bytes)));
            System.out.println(decrypt);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        System.out.println("Contraseña por texto: ");
        System.out.println("Introduce la contraseña para encriptar: ");
        String password = scanner.nextLine();

        SecretKey keyPass = xifrar.passwordKeyGeneration(password,256);

        String hello2 = "Lolololololoo";
        byte[] bytes2 = hello2.getBytes();

        System.out.println("Introduce la contraseña para desencriptar: ");
        String password2 = scanner.nextLine();

        SecretKey keyPass2 = xifrar.passwordKeyGeneration(password2,256);

        try {
            String decrypt2 = new String(xifrar.decryptData(keyPass2,xifrar.encryptData(keyPass,bytes2)));
            System.out.println(decrypt2);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        System.out.println("Algoritmo: " + keygen.getAlgorithm());
        System.out.println("Formato: " + keygen.getFormat());
        if(!keygen.isDestroyed()) System.out.println("No esta destruido");

    }
}
