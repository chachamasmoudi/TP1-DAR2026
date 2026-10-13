package serverPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Créer la socket d'écoute sur le port 1234
        ServerSocket socketServeur = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente de la connexion d'un client");

        // 2. Attendre puis accepter la connexion d'un client
        Socket socket = socketServeur.accept();
        System.out.println("Un client est connecté");

        // 3. Lire la requête envoyée par le client
        InputStream is = socket.getInputStream();
        int nb = is.read();

        // 4. Traiter, puis envoyer la réponse
        int x = nb * 3;
        OutputStream os = socket.getOutputStream();
        os.write(x);

        // 5. Fermer les sockets
        socket.close();
        socketServeur.close();
    }
}