package project;

import java.net.InetAddress;
import java.util.Objects;

public class Peer {
    private final String name;
    private final InetAddress address;
    private final int port;

    public Peer(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peer)) return false;
        Peer peer = (Peer) o;
        return port == peer.port &&
                Objects.equals(address, peer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port);
    }

    @Override
    public String toString() {
        return name + " (" + address.getHostAddress() + ":" + port + ")";
    }
}