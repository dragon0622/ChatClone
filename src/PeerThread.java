import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import jakarta.json.JsonObject;
import jakarta.json.Json;

public class PeerThread extends Thread{
    private BufferedReader bf;
    public PeerThread(Socket socket) throws IOException{
        bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void run(){
        boolean flag = true;
        while(flag){
            try{
                JsonObject jsonObject = Json.createReader(bf).readObject();
                if (jsonObject.containsKey("username")){
                    System.out.println("["+jsonObject.getString("username") + "]: "+jsonObject.getString("message"));
                }
            } catch(Exception e){
                flag = false;
                interrupt();
            }
        }
    }
}
