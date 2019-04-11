/**Kamera*/
public class Camera {
    /**Zmienne przechowujace polozenie kamery*/
    private float x,y;
    /**Konstruktor kamery**/
    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

/**Funkcja ktora aktualizuje logike kamery*/
public void tick(GameObject Ball){
        x = (-Ball.getX()*Board.xh) +Board.aw/3 ;
        y = (-Ball.getY()*Board.yh) +Board.ah/3 ;
}
    /**Ustawienie polozenia X kamery*/
public void setX(float x){
        this.x=x;
}
    /**Ustawienie polozenia Y kamery*/
public void setY(float y){
        this.y=y;
}
    /**Funkcja zwracajaca polozenie X kamery*/
public float getX(){
        return x;
}
    /**Funkcja zwracajaca polozenie Y kamery*/
public float getY(){
        return y;
}

}

