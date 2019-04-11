import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
    /** Okno z instrukcja i zasadami */
public class Help extends JFrame implements ActionListener {
    JButton button1;
    /** Konstruktor klasy Help */
    public Help() {
        try{
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("rsc/3.jpg")))));

        }catch (IOException e)
        {
            System.out.println("Nie ma tła");
        }
        /** Ustawienie wielkości okna */
        setSize(600, 220);
        /** Ustawienie nazwy okna */
        setTitle("Help");
        /** Ustawienie możliwości rozciągania okna */
        setResizable(false);
        /** Nowy przycisk */
        button1 = new JButton("OK");
        /** Nowa etykieta */
        JLabel label1 = new JLabel("<html>Sterowanie tytułową kulką za pomocą przycisków W,A,S,D.<br/>" +
                "Twoim celem jest dotarcie do wyznaczonego zakończenia (żółty kwadrat) na każdym poziomie w jak najkrótszym czasie " +
                "Po drodze będą na Ciebie czekać przeszkody, ale również bonusy w postaci skrócenia czasu (jasnozielony kwadrat), zmniejszenia piłki (czemnozielony kwadrat) i dodatkowego życia (czerwone koło). " +
                "Powodzenia! :)<br/><html>");
        /** Ustawienie koloru tekstu etykiety */
        label1.setForeground(new Color(250,250,250));
        /** Ustawienie rozmiaru przycisku */
        button1.setBounds(250,130,100,25);
        /** Ustawienie rozmiaru tekstu etykiety */
        label1.setBounds(25, 20, 550, 100);
        button1.addActionListener(this);
        /** Dodanie przycisku do okna */
        add(button1);
        /** Dodanie etykiety do okna */
        add(label1);
        /** Ustawienie rozmiaru tekstu etykiety */
        setVisible(true);
        button1.addActionListener(this);
        setLocationRelativeTo(null);
    }
        /**Metoda przechwytujaca zachowanie uzytkownika*/
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()== button1){
                dispose();
            }
        }
    }
