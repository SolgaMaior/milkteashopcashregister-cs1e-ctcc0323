//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


public class Register extends JFrame implements ActionListener {
    //Declare Variables
    JFrame registerframe;
    JLabel userlabel, passlabel, newpasswordlabel;
    JTextField textField;
    JPanel panel;
    JPasswordField passwordField, newpasswordField;
    String confirmPassword, password, username;
    JButton signupbutton, showpwbutton;


    Register() {

        //BackGround
        ImageIcon image = new ImageIcon("C:\\Users\\My PC\\IdeaProjects\\MilkTeaShop\\bg.png");
        Image scaledimage = image.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        JLabel bg = new JLabel(new ImageIcon(scaledimage));
        bg.setBounds(0, 0, 400, 500);


        //Frame
        registerframe = new JFrame("Register");
        registerframe.setLayout(null);
        registerframe.setSize(400, 500);
        registerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerframe.setLocationRelativeTo(null);

        //Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(1, 1, 500, 500);

        //Label
        Font font = new Font("Arial", Font.BOLD, 15);
        Font font1 = new Font("Arial", Font.BOLD, 12);

        userlabel = new JLabel();
        passlabel = new JLabel();
        userlabel.setText("Username");
        passlabel.setText("Password");
        newpasswordlabel = new JLabel("Confirm Password");
        userlabel.setFont(font);
        passlabel.setFont(font);
        newpasswordlabel.setFont(font1);
        userlabel.setBounds(10, 50, 100, 30);
        passlabel.setBounds(10, 100, 100, 30);
        newpasswordlabel.setBounds(10, 150, 250, 30);

        //Text & Password Field
        textField = new JTextField();
        textField.setBounds(120, 50, 250, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 100, 250, 30);

        //Additional PasswordField for Checking
        newpasswordField = new JPasswordField();
        newpasswordField.setBounds(120, 150, 250, 30);

        //Button
        Font buttonfont = new Font("Arial", Font.BOLD, 12);
        signupbutton = new JButton("Sign Up");
        signupbutton.setFont(buttonfont);
        signupbutton.setBounds(240, 200, 85, 20);
        signupbutton.addActionListener((ActionListener) this);


        //add elements to the Panel
        panel.add(userlabel);
        panel.add(passlabel);
        panel.add(newpasswordlabel);
        panel.add(textField);
        panel.add(passwordField);
        panel.add(newpasswordField);
        panel.add(signupbutton);
        panel.add(bg);
        registerframe.add(panel);
        registerframe.setVisible(true);
    }

    public static void main(String[] args) {
        Register register = new Register();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == signupbutton) {
            username = textField.getText();
            password = new String(passwordField.getPassword());
            confirmPassword = new String(newpasswordField.getPassword());
        }

        //Checks if there is already an existing account
        try (BufferedReader reader = new BufferedReader(new FileReader("cashiers.txt"))) {
            String line;
            //As long as there is an input continue replacing the value of "line" with every string of every line
            while ((line = reader.readLine()) != null) {
                //Split the String of each line
                String[] parts = line.split(", ");
                //Compare the details
                if (parts[0].equalsIgnoreCase(username)) {
                    JOptionPane.showMessageDialog(registerframe, "Account already Exists");
                    return;
                }
            }


        } catch (IOException c) {
            c.printStackTrace();
        }



        //Checks if Passwords match
        if (password.equals(confirmPassword)) {
            //If passwords are the same, transfer to a txt file
            try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("Cashiers.txt", true))) {
                writer1.write(username + ", " + password);
                writer1.newLine();
                JOptionPane.showMessageDialog(registerframe, "Sign Up successful!");
            } catch (IOException _) {

            }
            //exits
            registerframe.dispose();

        } else {
            //passwords do not match
            JOptionPane.showMessageDialog(registerframe, "Passwords do not match!");
        }
    }

}


