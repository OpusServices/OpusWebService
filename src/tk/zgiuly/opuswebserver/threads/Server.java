package tk.zgiuly.opuswebserver.threads;

import tk.zgiuly.opuswebserver.OpusWebServer;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class Server {
    private final String ip;
    private final int port;
    private final boolean verbose;

    public Server(String ip, int port, boolean verbose) {
        this.ip = ip;
        this.port = port;
        this.verbose = verbose;
    }

    public void startServer() throws Throwable {
        ServerSocket serverSocket = new ServerSocket(port, 0, Inet4Address.getByName(ip));
        OpusWebServer.logger.info("Server avviato e in attesa di connessioni!");
        while(true) {
            try(Socket socket = serverSocket.accept()) {
                OpusWebServer.logger.info("Connessione da " + socket.getInetAddress().getCanonicalHostName() + " accettata");
                Thread thread = new Thread(new Client(socket, verbose));
                thread.run();
                OpusWebServer.logger.info("Avvio thread per " + socket.getInetAddress().getCanonicalHostName());
            }
        }
    }
}
