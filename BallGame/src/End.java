import java.awt.*;
import java.util.LinkedList;

/** Zakonczenie */
public class End extends GameObject {
    /**Konstruktor zakonczenia*/
    public End(float x, float y, ID id) {
        super(x, y, id);
    }
    /**Funkcja ktora aktualizuje logike gry*/
    public void tick(LinkedList<GameObject> objects) {
    }
    /**Metoda, ktora wrysowuje w nasz obiekt prostokat, tak, by mozna bylo wprowadzic
     mechanizm kolizji*/
    public Rectangle getBounds(){
        return new Rectangle((int)(x*Board.xh),(int)(y*Board.yh),(int)(32*Board.xh),(int)(32*Board.yh));
    }

    /** Metoda odpowiadajaca za rysowanie zakonczenia */
    public void render(Graphics g) {
        g.setColor(new Color(255,193,37));
        g.fillRect((int)(x*Board.xh),(int)(y*Board.yh),(int)(32*Board.xh),(int)(32*Board.yh));

    }

}
