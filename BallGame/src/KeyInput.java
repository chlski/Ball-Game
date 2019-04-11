import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**Klasa przechwytujaca klawisze wcisniete przez uzytkownika*/
public class KeyInput extends KeyAdapter {
    private Handler handler;
    /**Zmienna przechowujaca wartosc odpowiedzialna za pauze*/
    public static int a = 0;
    public KeyInput(Handler handler){
        this.handler = handler;
    }

/**Metoda okreslajaca zmiane przyspieszenie pod wplywem wcisnietego klawisa*/
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int  i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Ball){
                if (a==0) {
                    if (key == KeyEvent.VK_W) tempObject.setVelY(-5);
                    if (key == KeyEvent.VK_S) tempObject.setVelY(5);
                    if (key == KeyEvent.VK_D) tempObject.setVelX(4);
                    if (key == KeyEvent.VK_A) tempObject.setVelX(-4);
                }
            }

        }
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(1);
        if (key == KeyEvent.VK_P) {
            if(a==0) {
                Ball.pauza();
                a=1;
            }
            else{
                Ball.wznow();
                a=0;
            }
        }

    }

/**Metoda okreslajaca zmiane przyspieszenia pod wyplwem puszczenia klawisza*/
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int  i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Ball){
                if (a==0){
                if (key == KeyEvent.VK_W) tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) tempObject.setVelX(0);
                if (key == KeyEvent.VK_A) tempObject.setVelX(0);
            }}


        }

    }





}
