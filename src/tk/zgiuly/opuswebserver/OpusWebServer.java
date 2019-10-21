package tk.zgiuly.opuswebserver;

import tk.zgiuly.opuswebserver.io.Config;
import tk.zgiuly.opuswebserver.threads.Server;

import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class OpusWebServer {
    public static final String ROOT = System.getProperty("user.dir");
    public static final Config config = new Config(ROOT);
    public static final Logger logger = Logger.getLogger(OpusWebServer.class.getName());
    public static void main(String[] args) {
        logger.setLevel(Level.ALL);
        logger.info("Avvio del server");
        try {
            new Server(config.getString("ip"), config.getInteger("port"), config.getBoolean("verbose")).startServer();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
