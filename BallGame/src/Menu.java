import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/** Okno z glownym menu */
public class Menu extends JFrame implements ActionListener {
    JButton button1, button2, button3, button4;

    /** Konstruktor klasy Menu*/
    public Menu() {
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("rsc/1.jpg")))));

        } catch (IOException e) {
            System.out.println("Nie ma tła");
        }
        /** Ustawienie wielkości okna */
        setSize(800, 800);
        /** Ustawienie nazwy okna */
        setTitle("Menu");
        /** Ustawienie możliwości rozciągania okna */
        setResizable(false);
        /** Ustawienie wyjścia z programu poprzez zamknięcie tego okna*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        button1 = new JButton("New Game");
        button2 = new JButton("High Scores");
        button3 = new JButton("Help");
        button4 = new JButton("Exit");
        button1.setBounds(200, 280, 400, 100);
        button2.setBounds(200, 390, 400, 100);
        button3.setBounds(200, 500, 195, 100);
        button4.setBounds(405, 500, 195, 100);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        setLocationRelativeTo(null);
    }
    /**Metoda przechwytujaca zachowanie uzytkownika*/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== button1) {
            setVisible(false);
            dispose();
            new Name();
        } else if (e.getSource()== button2) {
            setVisible(false);
            dispose();
            new Highscores();
        } else if (e.getSource()== button3) {
            new Help();
        } else if (e.getSource()== button4) {
            setVisible(false);
            dispose();
        }
    }
}

