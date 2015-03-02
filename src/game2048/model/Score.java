package game2048.model;

import java.io.Serializable;

/**
 * Created by DexSeven
 */
public class Score implements Serializable{

    static final long serialVersionUID = 2574L;

    int ScoreMain;
    int ScoreMax;
    boolean ScoreMaxChanged;
    boolean ScoreChanged;

    public Score()
    {
        ScoreMaxChanged = false;
        ScoreChanged = false;
        ScoreMain = 0;
        ScoreMax = 0;
    }

    public void Reset()
    {
        ScoreMain = 0;
    }

    public int getScoreMain()
    {
        return ScoreMain;
    }

    public int getScoreMax()
    {
        return ScoreMax;
    }

    public boolean getScoreChanged()
    {
        return ScoreChanged;
    }

    public boolean getScoreMaxChanged()
    {
        return ScoreMaxChanged;
    }

    public void setScoreMaxChanged(boolean ScoreMaxChanged)
    {
        this.ScoreMaxChanged = ScoreMaxChanged;
    }

    public void setScoreChanged(boolean ScoreChanged)
    {
        this.ScoreChanged = ScoreChanged;
    }

    public void addScore(int score)
    {
        if ( score != 0)
        {
            this.ScoreMain = this.ScoreMain + score;
            if ( ScoreMain > ScoreMax)
            {
                ScoreMax = ScoreMain;
                ScoreMaxChanged = true;
            }
            ScoreChanged = true;
        }
    }
}
