package tk.zgiuly.opuswebserver.threads;

import tk.zgiuly.opuswebserver.OpusWebServer;
import tk.zgiuly.opuswebserver.utils.ContentType;
import tk.zgiuly.opuswebserver.utils.ReadFileData;
import tk.zgiuly.opuswebserver.utils.SendData;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class Client implements Runnable {
    private final Socket socket;
    private final boolean verbose;

    public Client(Socket socket, boolean verbose) {
        this.socket = socket;
        this.verbose = verbose;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedOutputStream dataOut = new BufferedOutputStream(socket.getOutputStream());

            String data = input.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(data);

            String method = stringTokenizer.nextToken().toUpperCase();

            String fileRequested = stringTokenizer.nextToken().toLowerCase();

            if(verbose) {
                OpusWebServer.logger.warning(socket.getInetAddress().getCanonicalHostName() + " ha richiesto " + fileRequested);
            }

            if (!method.equals("GET") && !method.equals("HEAD")) {
                File file = new File(OpusWebServer.ROOT, OpusWebServer.config.getString("error_file"));
                int len = (int) file.length();
                byte[] bytes = ReadFileData.read(file, len);

                SendData.out(len, bytes, "text/html", out, dataOut);

            } else {
                if(fileRequested.endsWith("/")) {
                    fileRequested += OpusWebServer.config.getString("default_file");
                }

                File file = new File(OpusWebServer.ROOT, fileRequested);

                if(!file.exists()) {
                    File err404 = new File(OpusWebServer.ROOT, OpusWebServer.config.getString("404_file"));
                    int lenght = (int)err404.length();
                    byte[] bytesData = ReadFileData.read(err404, lenght);

                    SendData.out(lenght, bytesData, "text/html", out, dataOut);
                }

                int len = (int)file.length();
                String content = ContentType.getContentType(fileRequested);

                if(method.equals("GET")) {
                    byte[] bytes = ReadFileData.read(file, len);

                    SendData.out(len, bytes, content, out, dataOut);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
