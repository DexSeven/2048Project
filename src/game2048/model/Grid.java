package game2048.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Steph on 23/02/2015.
 */
public class Grid {

    List<Tile> LineTiles;
    List<Tile> Tiles;
    List<Integer> TilesPoints;
    int Taille;
    int statsLine;
    boolean vertical;
    boolean noMove = false;
    int countNoMove;
    boolean win2048;
    boolean isLoose;

    public Grid(int Taille)
    {
        isLoose = false;
        win2048 = false;
        this.Taille = Taille;
        Tiles = new LinkedList<Tile>();
        for ( int i = 0; i < Taille; i++ )
        {
            AddTile(new Tile(i));
        }
    }

    public void TestGrid()
    {
        //int i = 0;
        //for ( Tile t : Tiles)
        for ( int i = 0; i < 16; i++)
        {
            //t.SetPoints(i);
            if ( i < 4) Tiles.get(i).SetPoints(2);
            else if ( i < 8) Tiles.get(i).SetPoints(4);
            else if ( i < 12) Tiles.get(i).SetPoints(8);
            else Tiles.get(i).SetPoints(16);
        }
    }

    public boolean IsLoose()
    {
        return isLoose;
    }
    public void ResetCountNoMove()
    {
        countNoMove = 0;
    }

    public void ResetWin2048()
    {
        win2048 = false;
    }

    public boolean GetWin2048()
    {
        return win2048;
    }
    public boolean getNoMove()
    {
        return noMove;
    }

    public void SetNoMove(boolean noMove)
    {
        this.noMove = noMove;
    }

    public List<Tile> GetTiles()
    {
        return Tiles;
    }

    public List<Tile> GetLine(int statsLine, boolean vertical )
    {
        initLine(statsLine,vertical);
        getMinTiles();
        return LineTiles;
    }

    public void SetLine(boolean vertical , int statsLine, List<Tile> Line)
    {

        initLine(statsLine,vertical,Line);
        setMinTiles();
    }

    public void getMinTiles()
    {
        int iVal;
        int iCalc;
        int iInc;

        if (vertical)
        {
            iVal = statsLine * (int)Math.sqrt(Taille);
            iCalc = (statsLine+1) *(int)Math.sqrt(Taille);
            iInc = 1;
        }
        else
        {
            iVal = statsLine;
            iCalc = Taille - ((int) Math.sqrt(Taille) - statsLine) + 1;
            iInc = (int) Math.sqrt(Taille);
        }

        for ( int i = iVal; i < iCalc; i = i + iInc)
        {
            LineTiles.add(Tiles.get(i));

        }
    }

    public void setMinTiles()
    {
        int iVal;
        int iCalc;
        int iInc;
        int iSet = 0;

        if (vertical)
        {
            iVal = statsLine * (int)Math.sqrt(Taille);
            iCalc = (statsLine+1) *(int)Math.sqrt(Taille);
            iInc = 1;
        }
        else
        {
            iVal = statsLine;
            iCalc = Taille - ((int) Math.sqrt(Taille) - statsLine) + 1;
            iInc = (int) Math.sqrt(Taille);
        }
        for ( Tile t : LineTiles)
        {
            TilesPoints.add(t.GetPoints());
        }

        for ( int i = iVal; i < iCalc; i = i + iInc)
        {
            Tiles.get(i).SetPoints(TilesPoints.get(iSet));

            iSet++;
        }
    }

    public void initLine(int statsLine, boolean vertical )
    {
        this.vertical = vertical;
        this.statsLine = statsLine;
        LineTiles = new LinkedList<Tile>();
    }

    public void initLine(int statsLine, boolean vertical, List<Tile> Line)
    {
        this.vertical = vertical;
        this.statsLine = statsLine;
        LineTiles = Line;
        TilesPoints = new LinkedList<Integer>();
    }

    public void AddTile(Tile t)
    {

        Tiles.add(t);

    }

    public void noMove(boolean noMove)
    {
        if (noMove)
        {
            countNoMove++;
            if ( countNoMove == 4)
            {
                this.noMove = true;
            }
            else if  ( countNoMove == 16 )
            {
                isLoose = true;
            }
        }
    }

    public void Win2048()
    {
        win2048 = true;
    }
}
