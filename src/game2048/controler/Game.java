package game2048.controler;

import game2048.model.MetaGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Steph on 14/02/2015.
 * CONTROLER
 */
public class Game {


    public MetaGame MetaGame;


    public Game(MetaGame MetaGame)
    {
        this.MetaGame = MetaGame;
    }


    public void Start()
    {
        MetaGame.Start();
    }

    public void Init()
    {
        MetaGame.Init();
    }

    public void Move(int StatusSens)
    {
        MetaGame.MoveTiles(StatusSens);
    }

    public int getTaille()
    {
        return MetaGame.RealTaille();
    }


}

