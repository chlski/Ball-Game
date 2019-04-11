import java.awt.*;
import java.util.LinkedList;

/** Bonus skracajacy czas */
public class moreTime extends GameObject {
    /**Konstruktor bonusu*/
    public moreTime(float x, float y, ID id) {
        super(x, y, id);
    }
    /**Funkcja ktora aktualizuje logike gry*/
    public void tick(LinkedList<GameObject> objects) {

    }
    /**Metoda, ktora wrysowuje w nasz obiekt prostokat, tak, by mozna bylo wprowadzic
     mechanizm kolizji*/
    public Rectangle getBounds(){
        return new Rectangle((int)(x*Board.xh),(int)(y*Board.yh),(int)(20*Board.xh),(int)(20*Board.yh));
    }

    /** Metoda odpowiadajaca za rysowanie bonusu */
    public void render(Graphics g) {
        g.setColor(new Color(188,226,127));
        g.fillRect((int)(x*Board.xh),(int)(y*Board.yh),(int)(20*Board.xh),(int)(20*Board.yh));
    }

}
