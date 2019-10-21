package tk.zgiuly.opuswebserver.utils;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class SendData {
    public static void out(int len, byte[] data, String type, PrintWriter out, BufferedOutputStream dataOut) throws Throwable {
        out.println("HTTP/1.1 200 OK");
        out.println("Server: OpusWebServer : 1.0 by zGiuly");
        out.println("Date: " + new Date());
        out.println("Content-type: " + type);
        out.println("Content-length: " + len);
        out.println();
        out.flush();

        dataOut.write(data, 0, len);
        dataOut.flush();
    }
}
