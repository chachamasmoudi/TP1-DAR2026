package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Je suis un client pas encore connecté...");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis un client connecté");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        int x;
        do {
            System.out.print("Tapez un entier (0 pour quitter) : ");
            x = sc.nextInt();
            dos.writeInt(x);
            if (x != 0) {
                int resultat = dis.readInt();
                System.out.println("Résultat reçu du serveur (x * 5) : " + resultat);
            }
        } while (x != 0);

        socket.close();
        sc.close();
        System.out.println("Sockets fermés côté client");
    }
}