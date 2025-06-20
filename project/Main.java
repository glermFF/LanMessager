package project;

// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int listeningPort = 5000;

        TCPServer server = new TCPServer(listeningPort);
        server.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Chat P2P iniciado. Digite IP destino e mensagem para enviar:");

        while (true) {
            System.out.print("IP do destino (ou 'sair'): ");
            String ip = scanner.nextLine();
            if (ip.equalsIgnoreCase("sair")) break;

            System.out.print("Mensagem: ");
            String msg = scanner.nextLine();

            MessageHandler.sendMessage(ip, listeningPort, msg);
        }

        System.out.println("Encerrando aplicação...");
        scanner.close();
    }
}