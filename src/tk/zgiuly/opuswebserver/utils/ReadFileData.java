package tk.zgiuly.opuswebserver.utils;

import tk.zgiuly.opuswebserver.OpusWebServer;

import java.io.*;

public class ReadFileData {
    public static byte[] read(File file, int fileLength) throws IOException {
        if(!file.exists()) return new byte[0];
        FileInputStream stream = null;
        byte[] bytes = new byte[fileLength];
        try {
            stream = new FileInputStream(file);
            stream.read(bytes);
        } finally {
            if(stream != null) {
                stream.close();
            }
        }
        return bytes;
    }
}
