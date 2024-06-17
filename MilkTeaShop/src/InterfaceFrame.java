import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InterfaceFrame extends JFrame implements ActionListener {

    JFrame frame;
    JLabel flavor, size, customername, addons, sizechoice, addonprice;
    JTextField textField;
    JPanel panel;
    Choice flavorchoice;
    CheckboxGroup addonsgroup, cbgroup;
    JButton confirm;
    String name, assignedflavor, AddOns, cupsize, concatinfo = " ";



    InterfaceFrame() {

        frame = new JFrame("The Milk Tea Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 500);

        //BackGround
        ImageIcon image = new ImageIcon("C:\\Users\\My PC\\IdeaProjects\\MilkTeaShop\\bg.png");
        Image scaledimage = image.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        JLabel bg = new JLabel(new ImageIcon(scaledimage));
        bg.setBounds(0, 0, 400, 500);



        customername = new JLabel("Customer Name");
        customername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        customername.setBounds(30, 30, 400, 20);

        flavor = new JLabel("Flavor");
        flavor.setFont(new Font("Times New Roman", Font.BOLD, 20));
        flavor.setBounds(30, 90, 400, 30);

        size = new JLabel("Size");
        size.setFont(new Font("Times New Roman", Font.BOLD, 20));
        size.setBounds(30, 159, 40, 20);

        sizechoice = new JLabel("L = 80 / M = 65 / S = 50");
        sizechoice.setFont(new Font("Times New Roman",Font.PLAIN , 15));
        sizechoice.setBounds(75, 160, 400, 20);

        addons = new JLabel("Add Ons");
        addons.setFont(new Font("Times New Roman", Font.BOLD, 20));
        addons.setBounds(30, 229, 400, 20);

        addonprice = new JLabel("Php 15");
        addonprice.setFont(new Font("Times New Roman",Font.PLAIN , 15));
        addonprice.setBounds(120, 230, 400, 20);

        textField = new JTextField();
        textField.setBounds(30, 50, 200, 30);

        flavorchoice = new Choice();
        flavorchoice.setBounds(30, 120, 100, 20);
        flavorchoice.add("Mango");
        flavorchoice.add("Taro");
        flavorchoice.add("Okinawa");
        flavorchoice.add("Strawberry");
        flavorchoice.add("Chocolate");
        flavorchoice.add("Java Chip");


        cbgroup = new CheckboxGroup();
        Checkbox checkbox = new Checkbox("Large", cbgroup, false);
        checkbox.setBounds(30, 190, 50, 20);
        Checkbox checkbox1 = new Checkbox("Medium", cbgroup, false);
        checkbox1.setBounds(100, 190, 50, 20);
        Checkbox checkbox2 = new Checkbox("Small", cbgroup, false);
        checkbox2.setBounds(170, 190, 50, 20);


        addonsgroup = new CheckboxGroup();
        Checkbox addon = new Checkbox("Pearl", addonsgroup, false);
        addon.setBounds(30, 260, 50, 20);

        Checkbox addon1 = new Checkbox("Nata", addonsgroup, false);
        addon1.setBounds(100, 260, 50, 20);

        Checkbox addon2 = new Checkbox("Popping Boba", addonsgroup, false);
        addon2.setBounds(170, 260, 100, 20);

        Checkbox addon3 = new Checkbox("None", addonsgroup, false);
        addon3.setBounds(290, 260, 50, 20);


        confirm = new JButton();
        confirm.setText("Confirm");
        confirm.setBounds(30, 300, 80, 20);
        confirm.addActionListener(this);


        panel.add(customername);
        panel.add(flavor);
        panel.add(size);
        panel.add(sizechoice);
        panel.add(addons);
        panel.add(addonprice);
        panel.add(textField);
        panel.add(flavorchoice);
        panel.add(confirm);
        panel.add(checkbox);
        panel.add(checkbox1);
        panel.add(checkbox2);
        panel.add(addon);
        panel.add(addon1);
        panel.add(addon2);
        panel.add(addon3);
        panel.add(bg);
        frame.add(panel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new InterfaceFrame();

    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {

            if (textField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incomplete Info", "Error", JOptionPane.ERROR_MESSAGE);


            }else {
                name = textField.getText();
                assignedflavor = flavorchoice.getSelectedItem();
                AddOns = addonsgroup.getSelectedCheckbox().getLabel();
                cupsize = cbgroup.getSelectedCheckbox().getLabel();

                if(name != null && assignedflavor !=  null && cupsize != null){
                    concatinfo = name + ", " + assignedflavor + ", " + AddOns + ", " + cupsize;


                    try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("Orders.txt", false))) {
                        writer1.write(name + ", " + assignedflavor + ", " + cupsize + ", " + AddOns );
                        writer1.newLine();
                    } catch (IOException _) {

                    }
                    int x = JOptionPane.showConfirmDialog(frame,
                            "Order Details \nName: " + name + "\nFlavor: " + assignedflavor + "\nCupsize: " + cupsize + "\nAddOns: " + AddOns,
                            "Success", JOptionPane.YES_NO_OPTION);

                    if (x == JOptionPane.YES_OPTION) {

                        new Change();
                        frame.dispose();
                    }

                }
            }

        }

    }

}


