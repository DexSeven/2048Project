package game2048.model;

/**
 * Created by Steph on 14/02/2015.
 */
public class Tile {

    int Position;
    int Points;

    public Tile(int Position)
    {
        this.Position = Position;
        Points = 0;
    }

    public int GetPosition()
    {
        return Position;
    }

    public int GetPoints()
    {
       return Points;
    }

    public void SetPoints(int Points)
    {
        this.Points = Points;
    }

    public void calcPoints()
    {
        this.Points = this.Points*2;
    }

    public void ResetPoints()
    {
        this.Points = 0;
    }


}
