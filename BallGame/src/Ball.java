import java.awt.*;
import java.util.LinkedList;

/**Pilka */
public class Ball extends GameObject {
    /**Zmienne przechowujace tymczasowa predkosc pilki*/
    static float tempVely, tempVelx;
    Handler handler;
    /**Zmienne przechowujace rozmiary pilki */
    private float width =30, height = 30;
    /**Zmienna przechowujaca predkosc grawitacji */
    private static float gravity = Handler.grav/100;
    /**Zmienna przechowujaca maksymalna predkosc pilki */
    private static float MAX_SPEED = Handler.max;
    /**Konstruktor pilki*/
    public Ball(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

    }
    /**Metoda, ktora wrysowuje w nasz obiekt prostokat przy dolnej krawedzi tak, by mozna bylo wprowadzic
     mechanizm kolizji*/
    public Rectangle getBounds(){
        return new Rectangle((int) ((int) ((x + (width / 4))*Board.xh)), (int) ((int) (y + (height / 2)-1)*Board.yh), (int)((width/2)*Board.xh), (int)((height/2)*Board.yh));
    }
    /**Metoda, ktora wrysowuje w nasz obiekt prostokat przy gornej krawedzi tak, by mozna bylo wprowadzic
     mechanizm kolizji*/
    public Rectangle getBoundsTop(){
        return new Rectangle((int) ((int)((x+width/4)*Board.xh)), (int)(y*Board.yh), (int)((width/2)*Board.xh), (int)((height/3)*Board.yh));
    }
    /**Metoda, ktora wrysowuje w nasz obiekt prostokat przy prawej krawedzi tak, by mozna bylo wprowadzic
     mechanizm kolizji*/
    public Rectangle getBoundsRight(){
        return new Rectangle((int) ((int)((x +width-6)*Board.xh)), (int)((y+5)*Board.yh), (int)(5*Board.xh), (int)((height-10)*Board.yh));
    }
    /**Metoda, ktora wrysowuje w nasz obiekt prostokat przy lewej krawedzi tak, by mozna bylo wprowadzic
     mechanizm kolizji*/
    public Rectangle getBoundsLeft(){
        return new Rectangle((int)((x+1)*Board.xh)  , (int)((y+5)*Board.yh), (int)(5*Board.xh), (int)((height-10)*Board.yh));
    }
    /**Funkcja ktora aktualizuje logike gry*/
    public void tick(LinkedList<GameObject> objects) {
        x += velX;
        y += velY;

        if (falling){
            velY += gravity;

            if(velY > MAX_SPEED)
                velY = MAX_SPEED;
        }

        collision();

    }
    /**Metoda do wykrywania kolizji*/
    private void collision(){
        for(int i = 0; i<handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Obstacle){
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY()+width;
                    handler.stayAtLevel();
                    velX=0;
                    velY=0;
                    }
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 1;
                    if (HUD.HEALTH ==0){
                        HUD.reset();
                        handler.clearLevel();
                        Board.LEVEL=1;
                        handler.switchLevel();
                        velX=0;
                        velY=0;
                        break;}
                    y = tempObject.getY() - height;
                    falling = false;
                    handler.stayAtLevel();
                    velX=0;
                    velY=0;
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 1;
                    if (HUD.HEALTH ==0){
                        HUD.reset();
                        handler.clearLevel();
                        Board.LEVEL=1;
                        handler.switchLevel();
                        velX=0;
                        velY=0;
                        break;}
                    x = tempObject.getX() - width;
                    handler.stayAtLevel();
                    velX=0;
                    velY=0;

                }

                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 1;

                    if (HUD.HEALTH ==0){
                        HUD.reset();
                        handler.clearLevel();
                        Board.LEVEL=1;
                        handler.switchLevel();
                        velX=0;
                        velY=0;
                        break;}
                    x = tempObject.getX() +35;
                    handler.stayAtLevel();
                    velX=0;
                    velY=0;

                }

            }else if (tempObject.getID() == ID.End){
                //przejscie do nastepnego poziomu
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(tempObject);
                    Board.LEVEL++;
                    handler.switchLevel();
                }
            } //bonus w postaci odjecia czasu
            else if (tempObject.getID() == ID.moreTime){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.TIME-=300;
                    handler.removeObject(tempObject);
                }
            }
            else if (tempObject.getID() == ID.Smaller){
                if(getBounds().intersects(tempObject.getBounds())){
                    width=15;
                    height=15;
                    handler.removeObject(tempObject);
                }
            }
            else if (tempObject.getID() == ID.Life){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH++;
                    handler.removeObject(tempObject);
                }
            }
        }
    }
    /** Metoda odpowiadajaca za pauze */
    public static void pauza(){
        tempVelx=velX;
        tempVely=velY;
        gravity=0;
        MAX_SPEED=0;
    }
    /** Metoda odpowiadajaca za wznowienie gry */
    public static void wznow(){
        gravity=Handler.grav/100;
        MAX_SPEED=Handler.max;
        velX=tempVelx;
        velY=tempVely;
    }

    /** Metoda odpowiadajaca za rysowanie piłki */
    public void render(Graphics g) {
        g.setColor(new Color(207,41,66));
        g.fillOval((int)(x*Board.xh),(int)(y*Board.yh),(int)(width*Board.xh),(int)(height*Board.yh));
    }
}
