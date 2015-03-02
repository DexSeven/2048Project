package game2048.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Steph on 20/02/2015.
 */
public class Line implements Runnable{

    Score Score;
    Grid Grid;
    List<Tile> Tiles;
    int statsSens;
    int statsLine;
    boolean changeSens;
    boolean vertical;
    int iScore;
    boolean processStart;
    boolean looseTest;

    public Line(Score Score,Grid Grid,int statsSens, int statsLine, boolean looseTest)
    {
        this.looseTest = looseTest;
        iScore = 0;
        this.statsSens = statsSens;
        this.statsLine = statsLine;
        this.Score = Score;
        this.Grid = Grid;
    }

    public synchronized void updateScore()
    {
        Score.addScore(iScore);
    }

    public synchronized void updateTiles()
    {
        Grid.SetLine(vertical,statsLine,Tiles);
    }

    public synchronized void getTiles()
    {
        processStart = true;
        switch(statsSens)
        {
            case 0:
                // Right
                vertical = true;
                Tiles = Grid.GetLine(statsLine, vertical);
                changeSens = true;
                break;
            case 1:
                // Bottom
                vertical = false;
                Tiles = Grid.GetLine(statsLine, vertical);
                changeSens = true;
                break;
            case 2:
                // Left
                vertical = true;
                Tiles = Grid.GetLine(statsLine, vertical);
                changeSens = false;
                break;
            case 3:
                // Top
                vertical = false;
                Tiles = Grid.GetLine(statsLine, vertical);
                changeSens = false;
                break;
        }

        checkTiles();
    }

    public void checkTiles()
    {

        if (changeSens)
        {
            if (processStart)
            {
                changeSensTiles();
                checkZero();
            }
            else
            {
                checkZero();
                changeSensTiles();
            }
        }
        else
        {
            checkZero();
        }


    }

    public void changeSensTiles()
    {
        List<Tile> changeTile = new LinkedList<Tile>();

        for ( int i = Tiles.size()-1; i > -1 ; i--)
        {
            changeTile.add(Tiles.get(i));
        }

        Tiles.clear();
        Tiles = changeTile;
    }

    public void checkZero()
    {
        List<Tile> zeroTile = new LinkedList<Tile>();
        List<Tile> SupTile = new LinkedList<Tile>();

        for ( int i = 0; i < Tiles.size();i++ )
        {
            if ( Tiles.get(i).GetPoints() == 0)
            {
                zeroTile.add(Tiles.get(i));
            }
            else
            {
                SupTile.add(Tiles.get(i));
            }
        }

        for (Tile zT :zeroTile )
        {
            SupTile.add(zT);
        }

        Tiles.clear();
        Tiles = SupTile;
    }

    public void calcLine()
    {
        int countNoMove = 0;

        for ( int i = 0; i < Tiles.size(); i++)
        {
            if ( i != Tiles.size()-1 && Tiles.get(i).GetPoints() != 0)
            {
                if ( Tiles.get(i).GetPoints() == Tiles.get(i+1).GetPoints())
                {
                    if (!looseTest)
                    {
                        Tiles.get(i).calcPoints();
                        if (Tiles.get(i).GetPoints() == 2048) Win2048();
                        Tiles.get(i + 1).ResetPoints();
                        iScore = iScore + Tiles.get(i).GetPoints();
                        i++;
                    }
                }
                else if (Tiles.get(i+1).GetPoints() != 0 )
                {
                    countNoMove++;
                }
            }
        }
        if ( countNoMove == 3)
        {
            noMove();
        }
        processStart = false;
        checkTiles();
    }

    public synchronized void noMove()
    {
        Grid.noMove(true);
    }

    public synchronized void Win2048()
    {
        Grid.Win2048();
    }

    public void run()
    {

        getTiles();
        calcLine();
        if (!looseTest)
        {
            updateTiles();
            updateScore();
        }

    }

}
