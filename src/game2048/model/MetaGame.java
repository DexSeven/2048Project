package game2048.model;


import java.util.*;

/**
 * Created by DexSeven
 */
public class MetaGame extends AbstractMetaGame {

    public boolean Process = false;
    Grid Grid;
    public int size = 4;
    Score Score;
    Save Save;
    List<Thread> Lines;
    int StatusSens = 0; // 0 Right , 1 Bottom , 2 Left and 3 Top
    boolean firstLaunch;
    int oldAddTile;

    public MetaGame()
    {
        firstLaunch = true;
        Score = new Score();
        Save = new Save(Score);
        oldAddTile = -1;
        Init(false);
    }

    public void Init(boolean restart)
    {
        Process = true;
        Grid = new Grid(RealSize());

        Lines = new LinkedList<Thread>();
        notifyGrid();
        Process = false;
        Grid.SetNoMove(false);

        if (restart) Start();
    }

    public void Start()
    {
        addTile();
        addTile();
        if ( Save.FilesExist() && firstLaunch)
        {
            Score = Save.GetSave();
            Score.setScoreMaxChanged(true);
            firstLaunch = false;
        }

        Score.Reset();
        notifyScore();
    }

    public void MoveTiles(int StatusSens)
    {
        this.StatusSens = StatusSens;
        Grid.ResetCountNoMove();
        Run();
    }

    public void Run()
    {
        for ( int i = 0; i < size; i++)
        {
            Lines.add(new Thread(new Line(Score, Grid, StatusSens, i, false)));
        }
        RunLines();
        notifyGrid();
        if (Score.getScoreChanged()) notifyScore();
        if ( Grid.getNoMove())
        {
            for ( int i2 = 0; i2 < size; i2++)
            {
                for ( int i = 0; i < size; i++)
                {
                    if ( i2 != StatusSens)
                    {
                        Lines.add(new Thread(new Line(Score, Grid, i2, i, true)));
                    }
                }
            }
            RunLines();
            if ( Grid.IsLoose())
            {
                notifyGameStatus(false);
            }
            else
            {
                addTile();
                Save.SetSave();
            }
        }
        else
        {
            addTile();
            Save.SetSave();
        }

        if ( Grid.GetWin2048())
        {
            notifyGameStatus(true);
            Grid.ResetWin2048();
        }
        Process = false;
    }

    public void RunLines()
    {
        for ( Thread t : Lines)
        {
            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
        }

        while ( LinesIsAlive())
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        Lines.clear();
    }

    public boolean LinesIsAlive()
    {
        for ( Thread t : Lines )
        {
            if (t.isAlive())
            {
                return true;
            }
        }
        return false;
    }

    public int RealSize()
    {
        return size*size;
    }

    public void addTile()
    {
        List<Integer> ListI = new LinkedList<Integer>();
        int rdm = 0;

        for ( Tile t : Grid.GetTiles())
        {
            if (t.GetPoints() == 0)
            {
                ListI.add(t.GetPosition());
            }
        }
        if ( !ListI.isEmpty())
        {
            if ( ListI.size() > 1 )
            {
                do
                {
                    Random r = new Random();
                    rdm = r.nextInt(ListI.size()-1);

                }while(oldAddTile == Grid.GetTiles().get(ListI.get(rdm)).GetPosition());
                oldAddTile = Grid.GetTiles().get(ListI.get(rdm)).GetPosition();
            }
            oldAddTile = -1;
            Grid.GetTiles().get(ListI.get(rdm)).SetPoints(2);
            notifyGridAdd(ListI.get(rdm));

        }
    }

   public void notifyGrid()
    {
        int MsgGridPos;
        String MsgGridPoint;

        for ( Tile t : Grid.GetTiles())
        {
            MsgGridPos = t.GetPosition();
            MsgGridPoint = String.valueOf(t.GetPoints());
            notifyObserver(MsgGridPoint,MsgGridPos);
        }
    }

    public void notifyScore()
    {
        int MsgScore;
        Score.setScoreChanged(false);
        MsgScore = Score.getScoreMain();
        notifyObserver(MsgScore,true);

        if ( Score.getScoreMaxChanged())
        {
            Score.setScoreMaxChanged(false);
            MsgScore = Score.getScoreMax();
            notifyObserver(MsgScore,false);
        }
    }

    public void notifyGameStatus(boolean win)
    {
        notifyObserver(win);
    }

    public void notifyGridAdd(int pos)
    {
        notifyObserver("2",pos);
    }
}
