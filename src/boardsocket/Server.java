package boardsocket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import whiteboardgui.ServerPanel;



public class Server {
    
   int port;
    
    public Server(){
        this.initialization();
       
    }
    
    public void initialization(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        int inputPort = 0;
        boolean vaildPort = false;
        do {
            String input = "";
//            input = JOptionPane.showInputDialog(null,
//                    "Enter a port for the server(1024 гн 65535):",
//                    "Dialog for Input", JOptionPane.QUESTION_MESSAGE);
            input = JOptionPane.showInputDialog(null,
                  "Enter a port for the server(1024 гн 65535):",
                   "1024" +
                   "");
            if (input == null) {
                System.exit(1);
            }
            try {
                inputPort = Integer.parseInt(input);
                if (!(inputPort >= 1024) && (inputPort <= 65535)) {
                    JOptionPane.showMessageDialog(null,
                            "the port should be in 1024~65535", "warning",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    vaildPort = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "please input a vaild number", "Error",
                        JOptionPane.ERROR_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unkown exception",
                        "warning", JOptionPane.ERROR_MESSAGE);
            }

        } while (!vaildPort);
        System.out.println(inputPort);

        ServerPanel app = new ServerPanel(inputPort); 
        app.setVisible(true);
      
   
    }
    
    
    public static void main(String[] args) {
        Server server=new Server();
    }
}
