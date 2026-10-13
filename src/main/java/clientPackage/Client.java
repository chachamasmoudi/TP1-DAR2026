package clientPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Je suis un client pas encore connecté...");

        // Se connecter au serveur (même port : 1234)
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis un client connecté");

        // Envoyer un nombre au serveur
        OutputStream os = socket.getOutputStream();
        os.write(5);

        // Lire la réponse
        InputStream is = socket.getInputStream();
        int reponse = is.read();
        System.out.println("Réponse du serveur : " + reponse);

        // Fermer la socket
        socket.close();
    }
}
