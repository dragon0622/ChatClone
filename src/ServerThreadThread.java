import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThreadThread extends Thread{
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter printWriter;
    public ServerThreadThread(Socket socket, ServerThread serverThread){
        this.serverThread = serverThread;
        this.socket =  socket;
    }

    public void run(){
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            while(true) serverThread.sendMessage(bf.readLine());
        } catch (Exception e) {serverThread.getServerThreadThreads().remove(this);}
    }
    public PrintWriter getPrintWriter(){
        return printWriter;
    }
}
