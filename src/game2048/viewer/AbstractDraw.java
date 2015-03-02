package game2048.viewer;

import game2048.controler.Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by DexSeven
 */
public class AbstractDraw extends JFrame implements KeyListener{

    protected Game Game;
    protected boolean stop;

    public void keyTyped(KeyEvent e)
    {
    }


    public void keyPressed(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT :
                if (!stop )
                Game.Move(0);
                break;
            case KeyEvent.VK_DOWN :
                if (!stop )
                Game.Move(1);
                break;
            case KeyEvent.VK_LEFT :
                if (!stop )
                Game.Move(2);
                break;
            case KeyEvent.VK_UP :
                if (!stop )
                Game.Move(3);
                break;
        }
    }
}
