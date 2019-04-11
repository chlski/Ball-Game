import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
/** Okno z nazwa gracza */
public class Name extends JFrame implements ActionListener {
    JButton button1;
    JButton button2;
    JTextField text1;
    public static String name;
    /** Konstruktor klasy Name */
    public Name(){
        try{
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("rsc/2.jpg")))));

        }catch (IOException e)
        {
            System.out.println("Nie ma tła");
        }
        setSize(400,200);
        setTitle("Name");
        setLayout(null);
        setResizable(false);
        button1 = new JButton("OK");
        button2 = new JButton("Cancel");
        JLabel label = new JLabel("Your name:");
        text1 = new JTextField();
        text1.setBounds(90,50,210,20);
        button1.setBounds(90,80,100,50);
        button2.setBounds(200,80,100,50);
        label.setBounds(90,10,200,50);
        setLocationRelativeTo(null);
        setLayout(null);
        add(button1);
        add(button2);
        add(label);
        add(text1);
        setVisible(true);
        button1.addActionListener(this);
        button2.addActionListener(this);
    }
    /**Metoda przechwytujaca zachowanie uzytkownika*/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            setVisible(false);
            name = text1.getText();
            if (name.equals(""))
                name = "Unknown";
            new Board();
            dispose();
        } else if (e.getSource() == button2) {
            setVisible(false);
            dispose();
            new Menu();
        }
    }}