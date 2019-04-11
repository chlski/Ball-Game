import java.awt.*;
import java.util.LinkedList;

/** Przeszkoda */
public class Obstacle extends GameObject {
    private Handler handler;
    private int type;
    /**Konstruktor przeszkody*/
    public Obstacle(int x, int y,int type, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        this.type = type;

    }
    /**Metoda, ktora wrysowuje w nasz obiekt prostokat, tak, by mozna bylo wprowadzic
     mechanizm kolizji*/
    public Rectangle getBounds(){
        return new Rectangle((int)(x*Board.xh),(int) (y*Board.yh),(int)(66*Board.xh),(int)(66*Board.yh));
    }
    /**Funkcja ktora aktualizuje logike gry*/
    public void tick(LinkedList<GameObject> objects) {

    }

    /** Metoda odpowiadajaca za rysowanie przeszkody */
    public void render(Graphics g){
        g.setColor(new Color(250,250,231));
        g.fillRect((int)((x*Board.xh)),(int) (y*Board.yh),(int)(66*Board.xh),(int)(66*Board.yh));
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
    }
}


