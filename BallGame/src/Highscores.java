import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
    /** Okno z najlepszymi wynikami */
public class Highscores extends JFrame implements ActionListener {
    /** Zmienna przechowująca wynik pierwszego gracza */
    JButton button1;
    String wynik1;
    String wynik2;
    String wynik3;
    String wynik4;
    String wynik5;
    String gracz1;
    String gracz2;
    String gracz3;
    String gracz4;
    String gracz5;
    /** Konstruktor klasy Highscores */
    public Highscores(){

        try{
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("rsc/2.jpg")))));

        }catch (IOException e)
        {
            System.out.println("Nie ma tła");
        }
        try {
            File plik = new File("rsc/HighScores.txt");
            Scanner odczyt = new Scanner(plik);
            String pomoc = odczyt.nextLine();
            gracz1 = pomoc;
            pomoc = odczyt.nextLine();
            wynik1 = pomoc;
            pomoc = odczyt.nextLine();
            gracz2 = pomoc;
            pomoc = odczyt.nextLine();
            wynik2 = pomoc;
            pomoc = odczyt.nextLine();
            gracz3 = pomoc;
            pomoc = odczyt.nextLine();
            wynik3 = pomoc;
            pomoc = odczyt.nextLine();
            gracz4 = pomoc;
            pomoc = odczyt.nextLine();
            wynik4 = pomoc;
            pomoc = odczyt.nextLine();
            gracz5 = pomoc;
            pomoc = odczyt.nextLine();
            wynik5 = pomoc;

        }catch(FileNotFoundException a)
        {
            System.out.println("Plik nie istnieje");
        }

        setSize(200,350);
        setTitle("High Scores");
        setResizable(false);
        button1 = new JButton("OK");
        JLabel label1 = new JLabel(gracz1+" "+wynik1);
        JLabel label2 = new JLabel(gracz2+" "+wynik2);
        JLabel label3 = new JLabel(gracz3+" "+wynik3);
        JLabel label4 = new JLabel(gracz4+" "+wynik4);
        JLabel label5 = new JLabel(gracz5+" "+wynik5);
        label1.setForeground(new Color(0,0,255));
        label2.setForeground(new Color(0,90,255));
        label3.setForeground(new Color(0,140,255));
        label4.setForeground(new Color(0,170,255));
        label5.setForeground(new Color(0,200,255));
        button1.setBounds(45,220,100,50);
        label1.setBounds(50,40,100,20);
        label2.setBounds(50,70,100,20);
        label3.setBounds(50,100,100,20);
        label4.setBounds(50,130,100,20);
        label5.setBounds(50,160,100,20);
        button1.addActionListener(this);
        add(button1);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        setVisible(true);
        setLocationRelativeTo(null);
    }

        /**Metoda przechwytujaca zachowanie uzytkownika*/
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()== button1) {
                setVisible(false);
                dispose();
                new Menu();
        }
    }}
