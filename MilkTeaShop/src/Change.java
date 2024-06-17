import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Change implements ActionListener {

        //Declare Variables
        Frame frame;
        TextField textfield;
        Button[] FunctionButtons = new Button[4];
        Button equalbutton, clearButton;
        Panel panel;
        //Font
        Font FontStyle = new Font("Arial", Font.PLAIN, 10);
        InterfaceFrame order;

        //Info from Interface class
        InterfaceFrame interfaceFrame;
        String name, flavor, cupsize, addons;
        int addOnsprice, sizeprice, payment, change, orderprice,length = 0;


        //Constructor Method for Calculator
        Change(){

            //empties the receipt file
            try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("Receipt.txt", false))) {

            } catch (IOException _) {

            }

            //Frame
            frame = new Frame("The Milk Tea Shop");
            frame.setSize(450, 200);
            frame.setLayout(null);
            frame. setLocationRelativeTo(null);
            frame.addWindowListener(new WindowAdapter() {  //Add close function
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            //Panel
            panel = new Panel();
            panel.setBounds(65, 100, 300, 300);
            panel.setLayout(null);


            //TextField
            textfield = new TextField("Enter Payment");
            textfield.setFont(FontStyle);
            length  = textfield.getText().length();
            textfield.setSelectionStart(0);
            textfield.setSelectionEnd(length);
            textfield.requestFocus();
            textfield.setBounds(65, 75, 300, 20);
            textfield.setEditable(true); //Set if TextField is editable



            //Buttons
            equalbutton = new Button("Enter");
            clearButton = new Button("C");

            //Store function buttons in an array
            FunctionButtons[0] = equalbutton;
            FunctionButtons[1] = clearButton;

            //Add ActionListeners to all funtional buttons
            for (int i = 0; i < 2; i++) {
                FunctionButtons[i].addActionListener(this);
                FunctionButtons[i].setFont(FontStyle);


            }


            //Get orders from the interface frame
            try (BufferedReader reader = new BufferedReader(new FileReader("Orders.txt"))) {
                String line;
                //As long as there is an input continue replacing the value of "line" with every string of every line
                while ((line = reader.readLine()) != null) {
                    //Split the String of each line
                    String[] parts = line.split(", ");
                    name = parts[0];
                    flavor = parts[1];
                    cupsize = parts[2];
                    addons = parts[3];


                }


            } catch (IOException c) {
                c.printStackTrace();
            }





            frame.add(clearButton);
            //Add elements to Frame
            frame.add(textfield);
            frame.add(equalbutton);
            frame.add(panel);
            equalbutton.setBounds(125, 110, 55, 20);
            clearButton.setBounds(65, 110, 55, 20);
            frame.setVisible(true);
        }




    public static void main(String[] args) {
        new Change();

        }

        //Functionality of each button

        int EnterNumber = 1; //regulates that the TextField is only cleared once
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == equalbutton) {

                payment = Integer.parseInt(textfield.getText());
                if(addons.equals("Nata") || addons.equals("Pearl") || addons.equals("Popping Boba")){
                    addOnsprice = 15;
                }
                switch (cupsize) {
                    case "Large":
                        sizeprice = 80;
                        break;
                    case "Medium":
                        sizeprice = 65;
                        break;
                    case "Small":
                        sizeprice = 50;
                        break;
                }
                //Calculate
                orderprice = sizeprice + addOnsprice;
                change = (payment - orderprice);

                if(payment < (sizeprice + addOnsprice)){
                    JOptionPane.showMessageDialog(frame, "Insuffiecient Payment", "Order Summary", JOptionPane.INFORMATION_MESSAGE);

                }else{
                    String change_amount = "Change is: Php " + change;
                    JOptionPane.showMessageDialog(frame, change_amount, "Order Summary", JOptionPane.INFORMATION_MESSAGE);


                    try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("Receipt.txt", true))) {
                        writer1.write("The Milk Tea Shop" + "\n~~~~~~~~~~~~~~~~~" + "\nOrder Details" + "\nName: "+name + "\nFlavor: " + flavor + "\nSize: " + cupsize + "\nAdd Ons: " + addons + "\nTotal: " + orderprice + "\nPayment: " + payment + "\nChange: " + change);
                        writer1.newLine();
                    } catch (IOException _) {

                    }


                    int response = JOptionPane.showConfirmDialog(null, "Make new Order??", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        new InterfaceFrame();
                        frame.dispose();

                    } else if (response == JOptionPane.NO_OPTION) {
                        frame.dispose();
                    }
                }

            }

            if (e.getSource() == clearButton) {
                textfield.setText("");
            }

        }

}
