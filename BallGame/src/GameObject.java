import java.awt.*;
import java.util.LinkedList;

/**Klasa abstrakcyjna obiektow w grze*/
public abstract class GameObject {
    /**Zmienne okreslajace polozenie obiektu*/
    protected float x,y;
    /**Zmienna przetrzymujaca rodzaj obiektu*/
    protected  ID id;
    /**Zmienna okreslajaca przyspieszenie obiektu w kierunku X*/
    protected static float velX=0;
    /**Zmienna okreslajaca przyspieszenie obiektu w kierunku X*/
    protected static float velY=0;

    /**Zmienna ktora okresla czy obiekt opada z czasem*/
    protected boolean falling = true;

    public GameObject(float x, float y, ID id){
        this.x=x;
        this.y=y;
        this.id=id;

    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setVelX(float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    public float getX(){ return x; }
    public float getY(){
        return y;
    }
    public ID getID(){
        return id;
    }
}
