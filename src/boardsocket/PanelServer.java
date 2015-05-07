package boardsocket;

import java.io.*;

import java.net.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import whiteboardgui.ListData;
import whiteboardgui.ServerPanel;
import whiteboardgui.User;
import whiteboardgui.WhiteBoardFrame;

public class PanelServer extends Thread {
    public final static int PORT = 4988; 
    private BufferedReader in;
    public BufferedWriter out;
    ListData model = null;
    int id = 0;
    int port = 0;
    public PanelServer(ListData model,int port){
        this.port=port;
        this.model=model;
    }
    public void run() {
        OutputList output = new OutputList();
        ServerSocket s = null;
        try {
            s = new ServerSocket(port);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        do {
            System.out.print(id);
            try { 
              
                while (true) {
                    id++;
                    Socket socket = s.accept(); // Wait and accept a connection
                    User newUser = new User(id, socket);
                    new Mult(socket, output, newUser, model);
                }
            } catch (SocketException e) {
                System.out
                        .println("Socket error, this may cauased by network communication (broken connection, address not reachable, bad data..)\n"
                                + e.getMessage());
                System.exit(0);
            } catch (IOException ioe) {
                System.out
                        .println("IOException ,this maybe caused by wrong input data\n "
                                + ioe);
                ioe.printStackTrace();
            } catch (NumberFormatException ne) {
                System.out.println("A Correct Number is needed"
                        + "\nNumberFormatException" + ne);
                ne.printStackTrace();
            } finally {
            }

        } while (true);
    }


}

class Mult extends Thread {
    private Socket client; 

    private BufferedReader in; 
    private BufferedWriter out; 
    private static int count; 
    private int id;
    public OutputList outputList; 
    private User user;
    private ListData model;
    public String[] ss;

    public Mult(Socket s, OutputList output,User newUser,ListData model) throws IOException {

        this.client = s;
        this.outputList = output;
        this.user= newUser;
        this.model=model;
        if (handleLogonMsg()) {
            this.start();
            model.add(this.user);
            model.refresh();  
        }else{
            out.write("quit");
            out.newLine();
            out.flush();
         
        }
    }

    public void run() {

        try {
          
                outputList.append(out); 
                handleDrawMsg(); 
                outputList.remove(out);
                System.out.println("quit success£ºid = " + this.id + " , "
                        + this.client);
            

        } catch (IOException e) {

        } finally {
            try {
                if (client != null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

 
    public void handleDrawMsg() throws IOException {

        String dataBuffer = null;
        while (true) {
            dataBuffer = in.readLine(); 
            if (dataBuffer.equals("quit"))
                break;
            outputList.update(dataBuffer); 
        }
    }

    public boolean handleLogonMsg() throws IOException {
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(
                client.getOutputStream()));

        user.setName(in.readLine());
        int response = JOptionPane.showConfirmDialog(null, user.getName()+" want to join in?", "allow",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
              System.out.println(user.getName()+"quit!");
              return false;
            } else if (response == JOptionPane.YES_OPTION) {
              System.out.println(user.getName()+"join!");
            } else if (response == JOptionPane.CLOSED_OPTION) {
              System.out.println(user.getName()+"join!");
            }
            this.id = count++; 
            out.write(new Integer(id).toString()); 
            out.newLine();
            out.flush();
        System.out.println("login success: id = " + this.id + " , " + this.client);
        return true;
    }
}
