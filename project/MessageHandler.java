package project;

import java.io.*;
import java.net.*;

public class MessageHandler {

    public static void sendMessage(String peerIp, int port, String message) {
        try (Socket socket = new Socket(peerIp, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            System.err.println("[Erro ao enviar mensagem] " + e.getMessage());
        }
    }
}