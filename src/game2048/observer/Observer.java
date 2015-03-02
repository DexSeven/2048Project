package game2048.observer;

/**
 * Created by DexSeven
 */


public interface Observer {
    public void update(String str,int pos);
    public void update(int score,boolean bol);
    public void update(boolean bol);
}
