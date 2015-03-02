package game2048;


import game2048.controler.Game;
import game2048.model.MetaGame;
import game2048.viewer.Draw;

/**
 * Created by DexSeven
 * 2048 WIP
 */

public class Main {

    public static void main(String[] args) {

        MetaGame MetaGame = new MetaGame();
        Game Game = new Game(MetaGame);
        Draw Draw = new Draw(Game);
        MetaGame.addObserver(Draw);

        Game.Start();
    }


}
