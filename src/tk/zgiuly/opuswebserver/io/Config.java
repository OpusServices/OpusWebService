package tk.zgiuly.opuswebserver.io;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Config {
    private final File pathFile;
    private BufferedReader reader;
    private JSONParser parser;
    private JSONObject object;

    public Config(String path) {
        this.pathFile = new File(path + "/server.json");
        parser = new JSONParser();


        if(!pathFile.exists()) {
            try {
                generateConfig();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        try {
            this.reader = new BufferedReader(new FileReader(pathFile.getAbsoluteFile()));
            object = (JSONObject) parser.parse(reader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        if(object == null) {
            throw new NullPointerException("Config non valida");
        }
    }

    private void generateConfig() throws Throwable {
        pathFile.createNewFile();
        JSONObject object = new JSONObject();
        object.put("ip", "127.0.0.1");
        object.put("port", "8080");
        object.put("error_file", "err.html");
        object.put("404_file", "404.html");
        object.put("default_file", "index.html");
        object.put("verbose", false);
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile));
        writer.write(object.toJSONString());
        writer.flush();
        writer.close();
    }

    public String getString(String key) throws Throwable {
        return (String)object.get(key);
    }

    public int getInteger(String key) throws Throwable {
        return Integer.parseInt((String)object.get(key));
    }

    public boolean getBoolean(String key) throws Throwable {
        return (Boolean)object.get(key);
    }
}
