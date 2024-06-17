//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Welcome extends JFrame implements ActionListener {
    //Declare Variables
    Register reg; //Register Class
    InterfaceFrame inteface;

    JFrame frame;
    JLabel userlabel, passlabel, bgpicture;
    JTextField textField;
    JPanel panel;
    JPasswordField passwordField;
    JButton loginButton, registerButton, showpwbutton;
    String StringHolder;





    Welcome(){ //Constructor


        //Frame
        frame = new JFrame("The Milk Tea Shop");
        frame.setLayout(null);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        //Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(1, 1, 400, 500);


        //Label
        Font font = new Font("Arial", Font.BOLD, 15);

        userlabel = new JLabel("Username");
        userlabel.setText("Username");
        userlabel.setBounds(10, 50, 100, 30);
        userlabel.setFont(font);

        passlabel = new JLabel("Password");
        passlabel.setText("Password");
        passlabel.setBounds(10, 100, 100, 30);
        passlabel.setFont(font);


        //Text & Password Fields
        textField = new JTextField();
        textField.setBounds(90, 50, 250, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(90, 100, 250, 30);

        //Buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        loginButton = new JButton("Login");
        loginButton.setFont(buttonFont);
        loginButton.setBounds(105, 250, 75, 20);
        loginButton.addActionListener(this);


        registerButton = new JButton("Sign In");
        registerButton.setFont(buttonFont);
        registerButton.setBounds(250, 250, 75, 20);
        registerButton.addActionListener(this);

        showpwbutton = new JButton("Show");
        showpwbutton.setFont(buttonFont);
        showpwbutton.setBounds(105, 150, 75 , 20);
        showpwbutton.addActionListener(this);

        //BackGround
        ImageIcon image = new ImageIcon("C:\\Users\\My PC\\IdeaProjects\\MilkTeaShop\\bg.png");
        Image scaledimage = image.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        JLabel bg = new JLabel(new ImageIcon(scaledimage));
        bg.setBounds(0, 0, 400, 500);



        //Add elements to panel
        panel.add(passlabel);
        panel.add(userlabel);
        panel.add(passwordField);
        panel.add(textField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(showpwbutton);
        panel.add(bg);
        frame.add(panel);
        frame.setVisible(true);
    }

    //Main Method
    public static void main(String[] args) {
       new Welcome();
    }

    //Action Performed Method
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == showpwbutton){
            StringHolder = new String(passwordField.getPassword());
            JOptionPane.showMessageDialog(frame, StringHolder);


        }
        //If Register Button is pressed, create new frame
        if (e.getSource() == registerButton) {
            passwordField.setText("");
            textField.setText("");
            boolean registerdone = true;
            if (registerdone){
                new Register();
                registerdone = false;
            }

        }
        //Login
        if (e.getSource() == loginButton) {
            //Declare variables to hold textfield and file contents
            String username = textField.getText();
            String password = new String(passwordField.getPassword());
            String line = "";
            String[] parts = new String[2];


            //Use scanner to get the password and username from the txt file
            try (BufferedReader reader = new BufferedReader(new FileReader("cashiers.txt"))) {

                //As long as there is an input continue replacing the value of "line" with every string of every line
                while ((line = reader.readLine()) != null) {
                    //Split the String of each line
                    parts = line.split(", ");
                    //Compare the details

                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        new InterfaceFrame();
                        frame.dispose();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect/Unregistered username or password.");

                    }



                }

            } catch (IOException c) {
                c.printStackTrace();

            }

        }

    }
}

