import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


/**Klasa pomocnicza tak zwany posrednik*/
public class Handler {
    /**Zmienna przechowujaca grawitacje poziomu */
    static float grav;
    /**Zmienna przechowujaca maksymalna predkosc pilki */
    static int max;
    /**Zmienne przechowujące graczy potrzebnych do listy najlepszych wyników */
    private static Player A;
    private static Player B;
    private static Player C;
    private static Player D;
    private static Player E;
    private static Player F;
    /**Lista obiektow*/
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    /**Macierz przechowująca planszę*/
    private int[][] map;
    /**Zmienna przechowujaca szerokosc macierzy */
    static int w;
    /**Zmienna przechowujaca wysokosc macierzy */
    static int h;
    /**Zmienna przechowujaca numer planszy */
    private int nr;
    /**Metoda ladujaca mape z pliku*/
    public int[][] loadMap(int nr) {
        String file = Utils.loadFileAsString("rsc/Map"+nr+".txt");
        String[] tokens = file.split("\\s+");
        w = Utils.parseInt(tokens[0]);
        h = Utils.parseInt(tokens[1]);
        grav = Utils.parseInt(tokens[2]);
        max = Utils.parseInt(tokens[3]);
        int[][] tab1 = new int[h][w];

        for (int x = 0; x < h; x++)
            for (int y = 0; y < w; y++) {
                tab1[x][y] = Utils.parseInt(tokens[(x*w) + y +4]);
                System.out.println();
            }
        return tab1;
    }
    /**Konstruktor Handlera */
    public Handler(){
        nr=Board.LEVEL;
        map = loadMap(nr);
    }

    /**Metoda dodajaca nowy obiekt do listy*/
    public void addObject (GameObject object){
        this.object.add(object);
    }

    /**Metoda usuwajaca obiekt z listy*/
    public void removeObject (GameObject object) {
        this.object.remove(object);
    }

    /**Metoda ladujaca dany poziom, tworzy obiekty na podstawie mapy*/
    public void loadlevel() {
        map = loadMap(nr);
        nr=Board.LEVEL;
        for (int row= 0; row < map.length; row++)
            for (int col = 0; col < map[0].length; col++) {
                switch (map[row][col]) {
                    case 1:
                        addObject(new Obstacle((col*70),(row*70),0,ID.Obstacle,this));
                        break;
                    case 2:
                       addObject(new Ball((col*70)+20,(row*70)+25,ID.Ball,this));
                        break;
                    case 9:
                        addObject(new End((col*70)+20,(row*70)+25,ID.End));
                        break;
                    case 5:
                        addObject(new Smaller((col*70)+20,(row*70)+25,ID.Smaller));
                        break;
                    case 4:
                        addObject(new moreTime((col*70)+25,(row*70)+25,ID.moreTime));
                        break;
                    case 3:
                        addObject(new Life((col*70)+25,(row*70)+25,ID.Life));
                        break;
                    default:
                        break;
                }
            }
    }

    /**Funkcja ktora aktualizuje logike gry*/
    public void tick() {
        for (int i = 0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick(object);
        }
    }
    /**Metoda odpowiedzialna za rysowanie wszystkich obiektow*/
    public void render(Graphics g) {
        for (int i = 0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    /**Metoda czyszczaca liste z obiektow*/
    public void clearLevel(){
        object.clear();

    }
    /**Metoda porownajaca wyniki graczy*/
    public static boolean compare(Player g1, Player g2) {
        if (g1.Zwroc_Wynik() < g2.Zwroc_Wynik()) {
            return true;
        } else{
            return false;
        }
    }
    /**Metoda sprawdzajaca liste najlepszych wynikow*/
    public void SprawdzWyniki() {
            File plik = new File("rsc/HighScores.txt");
        Scanner odczyt = null;
        try {
            odczyt = new Scanner(plik);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String pomoc = odczyt.nextLine();
            int pomoc2 = Utils.parseInt(odczyt.nextLine());
            A = new Player(pomoc2,pomoc);
            pomoc = odczyt.nextLine();
            pomoc2 = Utils.parseInt(odczyt.nextLine());
            B = new Player(pomoc2,pomoc);
            pomoc = odczyt.nextLine();
            pomoc2 = Utils.parseInt(odczyt.nextLine());
            C = new Player(pomoc2,pomoc);
            pomoc = odczyt.nextLine();
            pomoc2 = Utils.parseInt(odczyt.nextLine());
            D = new Player(pomoc2,pomoc);
            pomoc = odczyt.nextLine();
            pomoc2 = Utils.parseInt(odczyt.nextLine());
            E = new Player(pomoc2,pomoc);
            F = new Player(HUD.TIME, HUD.nazwa);
            if(compare (F,E)){
                ZapiszWyniki();
            }
    }
    /**Metoda zmieniajaca poziom*/
    public void switchLevel(){

        switch (Board.LEVEL)
        {
            case 1:
                loadlevel();
                clearLevel();
                loadlevel();
                break;

            case 2:
                HUD.tempTime=HUD.TIME;
                HUD.tempHealth=HUD.HEALTH;
                loadlevel();
                clearLevel();
                loadlevel();
                break;

            case 3:
                HUD.tempTime=HUD.TIME;
                HUD.tempHealth=HUD.HEALTH;
                loadlevel();
                clearLevel();
                loadlevel();
                HUD.HEALTH++;
                break;
            case 4:
                HUD.tempTime=HUD.TIME;
                HUD.tempHealth=HUD.HEALTH;
                loadlevel();
                clearLevel();
                loadlevel();
                HUD.HEALTH++;
                break;
            case 5:
                clearLevel();
                KeyInput.a=1;
                SprawdzWyniki();
                Dialogkoncowy();
                System.exit(1);
                break;

        }

    }

    /**Metoda pozwalajaca powtorzyc poziom*/
    public void stayAtLevel() {

        switch (Board.LEVEL) {
            case 1:
                HUD.TIME=0;
                HUD.HEALTH=5;
                clearLevel();
                loadlevel();
                break;
            case 2:
                HUD.TIME=HUD.tempTime;
                clearLevel();
                loadlevel();
                break;
            case 3:
                HUD.TIME=HUD.tempTime;
                clearLevel();
                loadlevel();
                break;
            case 4:
                HUD.TIME=HUD.tempTime;
                clearLevel();
                loadlevel();
                break;

        }


    }

    /**Metoda aktualizujaca liste najlepszych wynikow*/
    public static void ZapiszWyniki() {
        try {
            PrintWriter wyniki = new PrintWriter("rsc/HighScores.txt");
            BufferedWriter wynik = new BufferedWriter(wyniki);
            if(compare(F,A)) {
                wynik.write(F.Zwroc_Nazwe());
                wynik.newLine();
                wynik.write(Integer.toString(F.Zwroc_Wynik()));
                wynik.newLine();
                wynik.write(A.Zwroc_Nazwe());
                wynik.newLine();
                wynik.write(Integer.toString(A.Zwroc_Wynik()));
                wynik.newLine();
                wynik.write(B.Zwroc_Nazwe());
                wynik.newLine();
                wynik.write(Integer.toString(B.Zwroc_Wynik()));
                wynik.newLine();
                wynik.write(C.Zwroc_Nazwe());
                wynik.newLine();
                wynik.write(Integer.toString(C.Zwroc_Wynik()));
                wynik.newLine();
                wynik.write(D.Zwroc_Nazwe());
                wynik.newLine();
                wynik.write(Integer.toString(D.Zwroc_Wynik()));}
            else{
                wynik.write(A.Zwroc_Nazwe());
                wynik.newLine();
                wynik.write(Integer.toString(A.Zwroc_Wynik()));
                wynik.newLine();
                if(compare(F,B)) {
                    wynik.write(F.Zwroc_Nazwe());
                    wynik.newLine();
                    wynik.write(Integer.toString(F.Zwroc_Wynik()));
                    wynik.newLine();
                    wynik.write(B.Zwroc_Nazwe());
                    wynik.newLine();
                    wynik.write(Integer.toString(B.Zwroc_Wynik()));
                    wynik.newLine();
                    wynik.write(C.Zwroc_Nazwe());
                    wynik.newLine();
                    wynik.write(Integer.toString(C.Zwroc_Wynik()));
                    wynik.newLine();
                    wynik.write(D.Zwroc_Nazwe());
                    wynik.newLine();
                    wynik.write(Integer.toString(D.Zwroc_Wynik()));
                }
                else{
                    wynik.write(B.Zwroc_Nazwe());
                    wynik.newLine();
                    wynik.write(Integer.toString(B.Zwroc_Wynik()));
                    wynik.newLine();
                    if(compare(F,C)){
                        wynik.write(F.Zwroc_Nazwe());
                        wynik.newLine();
                        wynik.write(Integer.toString(F.Zwroc_Wynik()));
                        wynik.newLine();
                        wynik.write(C.Zwroc_Nazwe());
                        wynik.newLine();
                        wynik.write(Integer.toString(C.Zwroc_Wynik()));
                        wynik.newLine();
                        wynik.write(D.Zwroc_Nazwe());
                        wynik.newLine();
                        wynik.write(Integer.toString(D.Zwroc_Wynik()));
                    }
                    else {
                        wynik.write(C.Zwroc_Nazwe());
                        wynik.newLine();
                        wynik.write(Integer.toString(C.Zwroc_Wynik()));
                        wynik.newLine();
                        if (compare(F, D)){
                            wynik.write(F.Zwroc_Nazwe());
                            wynik.newLine();
                            wynik.write(Integer.toString(F.Zwroc_Wynik()));
                            wynik.newLine();
                            wynik.write(D.Zwroc_Nazwe());
                            wynik.newLine();
                            wynik.write(Integer.toString(D.Zwroc_Wynik()));
                         }
                         else{
                            wynik.write(D.Zwroc_Nazwe());
                            wynik.newLine();
                            wynik.write(Integer.toString(D.Zwroc_Wynik()));
                            wynik.newLine();
                            wynik.write(F.Zwroc_Nazwe());
                            wynik.newLine();
                            wynik.write(Integer.toString(F.Zwroc_Wynik()));
                        }
                    }
                }
            }
        wynik.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }
        }
/**Wiadomosc na zakonczenie gry*/
    void Dialogkoncowy() {
        Frame f= new Frame();
        Dialog d = new Dialog(f , "Koniec Gry!", true);
        d.setLayout( new FlowLayout() );
        Button b = new Button ("OK");
        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                d.setVisible(false);
            }
        });
        if(compare (F,E)) {
            d.add(new Label("Trafiles na liste najlepszych wynikow! Twoj wynik to: " + HUD.TIME));
        }
        else {
            d.add(new Label("Nie trafiles na liste najlepszych wynikow.. Twoj wynik to: " + HUD.TIME));
        }
        d.add(b);
        d.setLocationRelativeTo(null);
        d.setSize(400,80);
        d.setVisible(true);
    }

}


