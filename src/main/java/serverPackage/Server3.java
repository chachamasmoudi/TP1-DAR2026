package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
    // Adresse IPv4 de la carte Wi-Fi de CE PC (voir ipconfig)
    private static final String IP_SERVEUR = "192.168.100.30";
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        InetAddress adresse = InetAddress.getByName(IP_SERVEUR);
        InetSocketAddress adresseSocket = new InetSocketAddress(adresse, PORT);

        ServerSocket socketServeur = new ServerSocket();
        socketServeur.bind(adresseSocket);
        System.out.println("Serveur en attente sur " + adresseSocket);

        Socket socket = socketServeur.accept();
        System.out.println("Un client est connecté : " + socket.getInetAddress().getHostAddress());

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true) {
            int choix = dis.readInt();
            if (choix == 0) {
                System.out.println("Le client a quitté : fin de la communication");
                break;
            }
            int x = dis.readInt();
            int y = dis.readInt();
            System.out.println("Reçu : choix=" + choix + ", x=" + x + ", y=" + y);

            switch (choix) {
                case 1:
                    dos.writeBoolean(true);
                    dos.writeDouble(x + y);
                    break;
                case 2:
                    dos.writeBoolean(true);
                    dos.writeDouble(x - y);
                    break;
                case 3:
                    dos.writeBoolean(true);
                    dos.writeDouble((double) x * y);
                    break;
                case 4:
                    if (y == 0) {
                        dos.writeBoolean(false);
                        dos.writeUTF("Erreur : division par zéro impossible");
                    } else {
                        dos.writeBoolean(true);
                        dos.writeDouble((double) x / y);
                    }
                    break;
                default:
                    dos.writeBoolean(false);
                    dos.writeUTF("Erreur : opération inconnue");
            }
        }

        socket.close();
        socketServeur.close();
        System.out.println("Sockets fermés côté serveur");
    }
}