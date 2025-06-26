package project;

import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class PeerDiscovery {
    private static final int DISCOVERY_PORT = 8888;
    private static final String DISCOVERY_MSG = "LAN_MESSAGER_DISCOVERY";
    private final List<Peer> peers = new CopyOnWriteArrayList<>();
    private final String localName;
    private final int localPort;

    private DatagramSocket socket;
    private Thread listenerThread;

    public PeerDiscovery(String localName, int localPort) {
        this.localName = localName;
        this.localPort = localPort;
    }

    public void start() throws Exception {
        socket = new DatagramSocket(DISCOVERY_PORT, InetAddress.getByName("0.0.0.0"));
        socket.setBroadcast(true);

        listenerThread = new Thread(this::listen);
        listenerThread.setDaemon(true);
        listenerThread.start();

        // Envia broadcast inicial para anunciar presença
        announce();
    }

    private void listen() {
        byte[] buf = new byte[256];
        while (!socket.isClosed()) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                if (msg.startsWith(DISCOVERY_MSG)) {
                    String[] parts = msg.split(";");
                    if (parts.length == 3) {
                        String name = parts[1];
                        int port = Integer.parseInt(parts[2]);
                        InetAddress addr = packet.getAddress();
                        // Ignora a si mesmo
                        if (!addr.isLoopbackAddress() && port != localPort) {
                            Peer peer = new Peer(name, addr, port);
                            if (!peers.contains(peer)) {
                                peers.add(peer);
                            }
                        }
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public void announce() {
        try {
            String msg = DISCOVERY_MSG + ";" + localName + ";" + localPort;
            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(
                data, data.length,
                InetAddress.getByName("255.255.255.255"), DISCOVERY_PORT
            );
            socket.send(packet);
        } catch (Exception ignored) {}
    }

    public List<Peer> getPeers() {
        return new ArrayList<>(peers);
    }

    public void stop() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        if (listenerThread != null && listenerThread.isAlive()) {
            listenerThread.interrupt();
        }
    }
}
