package project;

import java.io.*;
import java.net.*;

public class TCPServer extends Thread {
    private final int port;

    public TCPServer(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[Servidor TCP] Escutando na porta " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("[Erro TCP] " + e.getMessage());
        }
    }

    private void handleClient(Socket socket) {
        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))
        ) {
            String mensagem = in.readLine();
            System.out.println("[Nova mensagem de " + socket.getInetAddress().getHostAddress() + "]: " + mensagem);
        } catch (IOException e) {
            System.err.println("[Erro ao receber mensagem] " + e.getMessage());
        }
    }
}