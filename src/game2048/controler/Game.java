package game2048.controler;

import game2048.model.MetaGame;

/**
 * Created by DexSeven
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
        MetaGame.Init(true);
    }

    public void Move(int StatusSens)
    {
        MetaGame.MoveTiles(StatusSens);
    }

    public int GetSize()
    {
        return MetaGame.RealSize();
    }


}

