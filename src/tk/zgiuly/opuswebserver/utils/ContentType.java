package tk.zgiuly.opuswebserver.utils;

public class ContentType {
    public static String getContentType(String fileRequested) {
        if(fileRequested.endsWith(".htm")) {
            return "text/plain";
        } else if(fileRequested.endsWith(".html")){
            return "text/html";
        }else if(fileRequested.endsWith(".css")) {
            return "text/css";
        }else if(fileRequested.endsWith(".js")) {
            return "text/javascript";
        }else if(fileRequested.endsWith(".jpg")) {
            return "image/jpeg";
        }else if(fileRequested.endsWith(".png")) {
            return "image/png";
        }else if(fileRequested.endsWith(".gif")) {
            return "image/gif";
        }else if(fileRequested.endsWith(".ico")) {
            return "image/x-ico";
        }
        return "application/octet-stream";
    }
}
