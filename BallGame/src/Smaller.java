import java.awt.*;
import java.util.LinkedList;
/** Bonus zmniejszajacy pilke */
public class Smaller extends GameObject{
    /**Konstruktor bonusu*/
    public Smaller(float x, float y, ID id) {
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
    public void render(Graphics g){
        g.setColor(new Color(120,250,0));
        g.fillOval((int)(x*Board.xh),(int)(y*Board.yh),(int)(20*Board.xh),(int)(20*Board.yh));
    }
}

