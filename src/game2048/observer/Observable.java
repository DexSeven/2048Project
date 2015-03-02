package game2048.observer;


/**
 * Created by DexSeven
 */

public interface Observable {
    public void addObserver(Observer obs);
    public void notifyObserver(String str,int pos);
    public void notifyObserver(int score,boolean bol);
    public void notifyObserver(boolean bol);
}