/**Klasa przechowujaca wynik danego gracza*/
public class Player {
    /**Zmienna przechowujaca wynik gracza*/
    private int Wynik;
    /**Zmienna przechowujaca nazwe gracza*/
    private String Nazwa;
    /**Konstruktor klasy Player*/
    public Player(int wyn, String naz) {
        this.Wynik = wyn;
        this.Nazwa = naz;
    }

    /**Metoda zwracajaca wynik gracza*/
    public int Zwroc_Wynik() {return this.Wynik; }
    /**Metoda zwracajaca nazwe gracza*/
    public String Zwroc_Nazwe() {return this.Nazwa;}
}
