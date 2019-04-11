import java.awt.*;
import java.util.LinkedList;
/** Bonus zwiekszajacy zycie */
public class Life extends GameObject{
    /**Konstruktor bonusu*/
    public Life(float x, float y, ID id) {
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
        g.setColor(new Color(250,0,50));
        g.fillOval((int)(x*Board.xh),(int)(y*Board.yh),(int)(20*Board.xh),(int)(20*Board.yh));
    }
}

