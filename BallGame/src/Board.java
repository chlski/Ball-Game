import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;

/** Okno z plansza */
public class Board implements Runnable {

    /** Zmienne przechowujace rozmiary okna */
    public static float h, w;
    /**Zmienne przechowujace aktualne rozmiary okna */
    public static float ah,aw;
    /**Zmienne przechowujace zmienne potrzebne do skalowania */
    public static float yh,xh;
    /** Zmienna tablicowa przechwytujaca rozmiar okna z pliku*/
    private int[] size;
    /**Zmienna okreslajaca aktualny poziom*/
    public static int LEVEL = 1;
    /** Zmienna pomocnicza okreslajaca ilosc klatek*/
    public static int frames=0;
    private Handler handler;
    private HUD hud;
    private Thread thread;
    /**Zmienna okreslajaca czy program aktualnie dziala*/
    public boolean running = false;
    private Camera cam;
    Canvas canvas = new Canvas();
    /** Metoda słuzaca wczytywaniu rozmiaru okna z pliku */
    private int[] loadSize(){
        int[] tab = new int[2];
        FileInputStream plik = null;

        try{
            plik = new FileInputStream("rsc/Size.txt");
        }
        catch (FileNotFoundException e){
            System.out.println("Nie odnaleziono pliku" +e);
            System.exit(-1);
        }

        BufferedReader read = new BufferedReader(new InputStreamReader(plik));

        try{
            for (int i = 0; i < 2; i++) {
                String temp1 = read.readLine();
                tab[i] = Integer.parseInt(temp1);

            }

        }
        catch (IOException e){
            System.out.println("Błąd!");
        }
        return tab;
    }

    /** Konstruktor klasy Board */
    public Board(){
        handler = new Handler();
        canvas.addKeyListener(new KeyInput(handler));
        size = loadSize();
        w=size[0];
        h=size[1];
        cam = new Camera(0,0);
        handler.loadlevel();
        Frame okno = new Frame("Kulka");
        okno.setSize(new Dimension((int)w,(int)h));
        okno.setLocationRelativeTo(null);
        okno.add(canvas);
        okno.setVisible(true);
        this.start();
        hud = new HUD();
}
    /** Metoda rozpoczynajaca watek*/
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    /**Metoda zatrzymujaca watek*/
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**Metoda rozpoczynajaca program*/
    public void run(){
        canvas.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    /**Funkcja ktora aktualizuje logike gry*/
    private void tick(){

        handler.tick();
        hud.tick();
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getID() == ID.Ball){
            cam.tick(handler.object.get(i));
            }
        }
    }
    /**Funkcja służąca aktualizacji obrazu z czasem programu*/
    private void render(){
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0,0, (int)(aw=this.canvas.getWidth()), (int)(ah=this.canvas.getHeight()));
        yh=(ah+39)/h;
        xh=(aw+16)/w;
        g2d.translate(cam.getX(),cam.getY());
        handler.render(g);

        hud.render(g);
        g2d.translate(-cam.getX(), -cam.getY());
        g.dispose();
        bs.show();
    }
    /**Funkcja blokujaca mozliwosc przekroczenia z gory zalozonych wartosci */
    public static int clamp(int var, int min, int max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }


    }
