package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {
    // Adresse IP de la machine SERVEUR
    private static final String IP_SERVEUR = "192.168.100.30";
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        System.out.println("Je suis un client pas encore connecté...");

        InetAddress adresse = InetAddress.getByName(IP_SERVEUR);
        InetSocketAddress adresseSocket = new InetSocketAddress(adresse, PORT);

        Socket socket = new Socket();
        socket.connect(adresseSocket, 5000); // timeout de 5 secondes
        System.out.println("Je suis un client connecté à " + adresseSocket);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        int choix;
        do {
            System.out.println("\n===== CALCULATRICE =====");
            System.out.println("1. Addition");
            System.out.println("2. Soustraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();

            if (choix == 0) {
                dos.writeInt(0);
            } else if (choix >= 1 && choix <= 4) {
                System.out.print("Entrez x : ");
                int x = sc.nextInt();
                System.out.print("Entrez y : ");
                int y = sc.nextInt();

                dos.writeInt(choix);
                dos.writeInt(x);
                dos.writeInt(y);

                boolean ok = dis.readBoolean();
                if (ok) {
                    System.out.println("Résultat : " + dis.readDouble());
                } else {
                    System.out.println(dis.readUTF());
                }
            } else {
                System.out.println("Choix invalide, réessayez.");
            }
        } while (choix != 0);

        socket.close();
        sc.close();
        System.out.println("Sockets fermés côté client");
    }
}