import java.awt.*;

/**Interfejs w grze*/
public class HUD {

    /**Zmienna przechowujaca ilosc zyc*/
    public static int HEALTH = 5;
    /**Zmienna przechowujaca nazwe gracza*/
    public static String nazwa = Name.name;
    /**Zmienna przechowujaca czas gry*/
    public static int TIME = 0;
    /**Zmienna przechowujaca tymczasowy czas gry*/
    public static int tempTime = 0;
    /**Zmienna przechowujaca tymczasowe zycie gracza*/
    public static int tempHealth = 5;
    public void tick(){

        HEALTH = Board.clamp(HEALTH, 0,10);
        if(HEALTH!=0 && KeyInput.a==0)
        TIME++;

    }
    /** Metoda odpowiadajaca za rysowanie interfejsu */
    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(13*((Board.yh+Board.xh)/2))));
        g.drawString("Health: " + HEALTH,(int)(3*Board.xh),(int)(20*Board.yh));
        g.drawString("Time: " + TIME,(int)(3*Board.xh),(int)(35*Board.yh));
        g.drawString("Level: " + Board.LEVEL,(int)(3*Board.xh),(int)(50*Board.yh));
        g.drawString(nazwa,(int)(3*Board.xh),(int)(65*Board.yh));
        g.drawString("Quit ESC",(int)(72*Board.xh),(int)(20*Board.yh));
        g.drawString("Pause P",(int)(72*Board.xh),(int)(45*Board.yh));
    }
    /** Metoda odpowiadajaca za resetowanie ustawien */
    public static void reset(){
        HEALTH = 5;
        TIME = 0;
    }

}
