package game2048.model;

import java.util.ArrayList;
import game2048.observer.Observable;
import game2048.observer.Observer;
/**
 * Created by DexSeven
 */
public abstract class AbstractMetaGame implements Observable{

    protected ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void notifyObserver(String str, int pos) {

        for(Observer obs : listObserver)
            obs.update(str,pos);
    }

    public void notifyObserver(int score, boolean bol) {

        for(Observer obs : listObserver)
            obs.update(score,bol);
    }

    public void notifyObserver(boolean bol) {

        for(Observer obs : listObserver)
            obs.update(bol);
    }

    public void removeObserver() {
        listObserver = new ArrayList<Observer>();
    }

}
