package whiteboardgui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.font.*;

//attention: the class com.sun.image.codec is not a common class in eclipse,

import boardsocket.PanelClient;
import boardsocket.PanelServer;

import com.sun.image.codec.*;
import com.sun.image.codec.jpeg.*;
import java.net.*;
import java.io.*;
import java.text.Collator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class ServerPanel extends JFrame implements ActionListener, Runnable{
    private Container c = getContentPane();
    
    private JLabel serverText;
    private int isFilled;
    private WindowAdapter quit;
    private int change = 0;
    private String fileName;
    private String filePath;
    private int x2, y2;
    private int saveAs = 0;
    private JList userList;
    private InetAddress inet = null;
    private JPanel serverInfo;
    private JPanel userInfo;
    private JPanel serverOper;
    private ArrayList<User> users = new ArrayList<User>(30);
    private ListData model = new ListData(users);
    private Comparator cmp = Collator.getInstance(java.util.Locale.ENGLISH);
    public ServerSocket serverSocket = null;
    public int port = 0;
    public Socket socket = null;
    private JPanel serverPanel=new JPanel();
    
    public InetAddress getInet() {
        return inet;
    }

    public ServerPanel(int port) {
        this.port=port;
        this.run();
        PanelServer a = new PanelServer(model, port);
        a.start();
    }
    public void setInet(InetAddress inet) {
        this.inet = inet;
    }

   
    public ServerPanel() { 
        this.run();
    }
    
    public void run() {
        // set the port for the server;

        try {
            this.setInet(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // set the server;

        // set the users list.
        this.users.clear();
        // set as the system maximum screen
        this.setDefault();
        // make the screen the maximum screen base on the system
        // this part for the menu bar
        // this.setMenu();
        this.setScreen();
        this.setPanel();
        // this.pack();
        this.setVisible(true);
    }
    
    private void setDefault() {
        this.fileName = "ImageServer";
        setTitle(fileName);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {
            this.addWindowListener(quit = new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    int response = JOptionPane.showConfirmDialog(null,
                            "Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.NO_OPTION) {

                    } else if (response == JOptionPane.YES_OPTION) {
                        System.exit(1);
                    } else if (response == JOptionPane.CLOSED_OPTION) {

                    }
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unkown Error", "warning",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(
                "src/img/logo.gif"));

    }
    private void setScreen() {
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width = 0;
        int height = 0;
        width = screen.width;
        height = screen.height;
        if (width < 1024 || height < 680) {
            this.setSize(width, height);
            this.setExtendedState(Frame.MAXIMIZED_BOTH);
        } else {          
                this.setSize(600, 600);            
            this.setLocationRelativeTo(null);
        }
    }
    public void setPanel() {
        serverPanel.setLayout(new BorderLayout());
        serverInfo = new JPanel();
        this.serverText = new JLabel("new server has been start on port <br>");
        String text = "<html><b>IP:</b>";
        text += this.inet.getHostAddress();
        text += "<br><b>Port:</b>";
        text += this.port;
        text += "<br></html>";
        this.serverText.setText(text);
        // serverText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        serverInfo.add(serverText);
        serverInfo.setLayout(new GridLayout(1, 0, 15, 15));
        serverInfo.setBounds(new Rectangle(0, 0, 100, 160));
        serverInfo.setBorder(new TitledBorder(null, "Server Info",
                TitledBorder.LEFT, TitledBorder.TOP));
        serverPanel.add(serverInfo, BorderLayout.NORTH);

        userInfo = new JPanel();
        userList = new JList(model);
        userInfo.setLayout(new GridLayout(1, 0, 15, 15));
        userInfo.setBounds(new Rectangle(0, 0, 100, 160));
        userInfo.setBorder(new TitledBorder(null, "User List",
                TitledBorder.LEFT, TitledBorder.TOP));
        // userList.add(1, index)
        userList.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        userInfo.add(userList);

        serverOper = new JPanel();
        JButton kick = new JButton("kick out");
        JButton fresh = new JButton("refresh");
        kick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.removeElement(userList.getSelectedIndex());
            }
        });
        fresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                model.refresh();
            }
        });

        userInfo.add(new JScrollPane(userList), BorderLayout.CENTER);
        serverOper.add(kick, BorderLayout.NORTH);
        serverOper.add(fresh, BorderLayout.SOUTH);

        serverPanel.add(userInfo, BorderLayout.CENTER);
        serverPanel.add(serverOper, BorderLayout.SOUTH);
        c.add(serverPanel);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
