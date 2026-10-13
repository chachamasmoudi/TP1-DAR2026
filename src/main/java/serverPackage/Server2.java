package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws IOException {
        ServerSocket socketServeur = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente de la connexion d'un client");

        Socket socket = socketServeur.accept();
        System.out.println("Un client est connecté");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true) {
            int x = dis.readInt();
            if (x == 0) {
                System.out.println("Le client a envoyé 0 : fin de la communication");
                break;
            }
            System.out.println("Nombre reçu : " + x);
            int resultat = x * 5;
            dos.writeInt(resultat);
            System.out.println("Résultat envoyé : " + resultat);
        }

        socket.close();
        socketServeur.close();
        System.out.println("Sockets fermés côté serveur");
    }
}