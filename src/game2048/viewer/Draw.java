package game2048.viewer;

import game2048.controler.Game;
import game2048.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Steph on 14/02/2015.
 * VIEWER
 *
 */
public class Draw extends AbstractDraw implements Observer{

    static JButton bouton;
    static JLabel status;
    public boolean Process = false;
    static List<JLabel> JTiles;
    static List<JLabel> JScore;
    private JPanel container;
    int oldScoreMain;


    public Draw(Game Game)
    {
        stop = false;
        oldScoreMain = 0;
        this.Game = Game;
        JTiles = new LinkedList<JLabel>();
        JScore = new LinkedList<JLabel>();
        this.setTitle("2048");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        container = new JPanel();


        Font police = new Font("Arial", Font.BOLD, 20);
        Font police2 = new Font("Arial", Font.BOLD, 10);
        JPanel panEcran = new JPanel();
        panEcran.setPreferredSize(new Dimension(350, 350));
        JPanel pan2Ecran = new JPanel();
        pan2Ecran.setPreferredSize(new Dimension(350, 100));


        for ( int i = 0; i < Game.getTaille(); i++)
        {
            JTiles.add(new JLabel(" "));
        }

        for ( JLabel Jl : JTiles)
        {

            Jl.setFont(police);
            Jl.setHorizontalAlignment(JLabel.CENTER);
            Jl.setPreferredSize(new Dimension(80, 80));
            Jl.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            //Jl.setForeground(new Color(255, 255, 255));
            panEcran.add(Jl);
        }

        for ( int i = 0; i <4 ; i++)
        {
            JScore.add(new JLabel("0"));
        }
        int i =0;
        for ( JLabel Jl : JScore)
        {
            if ( i == 0 || i == 2)
            {
                if ( i == 0)
                {
                    Jl.setText("Score : ");
                    Jl.setPreferredSize(new Dimension(40, 30));
                }
                else
                {
                    Jl.setText("High Score : ");
                    Jl.setPreferredSize(new Dimension(70, 30));
                }
                Jl.setFont(police2);
                Jl.setHorizontalAlignment(JLabel.CENTER);
            }
            else
            {
                Jl.setPreferredSize(new Dimension(100, 30));
                Jl.setBorder(BorderFactory.createLineBorder(Color.red));
                pan2Ecran.add(Jl,BorderLayout.EAST);
                Jl.setFont(police);
                Jl.setHorizontalAlignment(JLabel.RIGHT);
            }
            pan2Ecran.add(Jl,BorderLayout.EAST);
            i++;
        }


        panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
        panEcran.setBackground(Color.GRAY);

        bouton = new JButton("Restart");
        bouton.addActionListener(new TestLis());

        status = new JLabel(" ");
        status.setPreferredSize(new Dimension(80, 30));
        status.setFont(police);
        status.setForeground(new Color(100, 69, 28));

        pan2Ecran.add(status);
        pan2Ecran.add(bouton,BorderLayout.EAST);
        container.add(pan2Ecran,BorderLayout.NORTH);
        container.add(panEcran,BorderLayout.SOUTH);
        container.addKeyListener(this);
        container.setFocusable(true);
        container.requestFocus();


        this.setContentPane(container);

        this.setVisible(true);

    }

    public void update(final String str,final int pos) {

        Process = true;
        SwingWorker sw = new SwingWorker(){
            protected Object doInBackground() throws Exception {
                //for(int i = 0; i < Game.MetaGame.RealTaille(); i++){
                    try {
                        setProgress(1);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
               // }
                return null;
            }

            public void done(){

                //permettre de savoir quand les mise a jour grid et score sont fini pour faire les truc en bas
                Process = false;
                container.setFocusable(true);
                container.requestFocus();
            }
        };

        sw.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent event)
            {
                String Msg;
                if("progress".equals(event.getPropertyName()))
                {
                    if ( str.equals("0"))
                    {
                        Msg = " ";
                        JTiles.get(pos).setOpaque(false);
                    }
                    else
                    {
                        Msg = str;
                        JTiles.get(pos).setOpaque(true);
                        JTiles.get(pos).setBackground(Color.pink);
                    }
                    JTiles.get(pos).setText(Msg);
                }
            }
        });
        sw.execute();
    }

    public void update(final int score,final boolean bol) {

        Process = true;
        SwingWorker sw = new SwingWorker(){
            protected Object doInBackground() throws Exception {
                for(int i = 0; i < 10 ; i++){
                try {
                    setProgress(i);
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
                return null;
            }

            public void done()
            {
                if ( bol)
                {
                    JScore.get(1).setText(String.valueOf(score));
                }
                else
                {
                    JScore.get(3).setText(String.valueOf(score));
                }
                oldScoreMain = score;
                Process = false;
                container.setFocusable(true);
                container.requestFocus();
            }
        };

        sw.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent event)
            {
                if("progress".equals(event.getPropertyName()))
                {
                    if ( bol)
                    {
                        JScore.get(1).setText(String.valueOf(event.getNewValue()));
                    }
                    else
                    {
                        JScore.get(3).setText(String.valueOf(event.getNewValue()));
                    }
                }
            }
        });
        sw.execute();
    }

    public void update(final boolean bol) {

        Process = true;
        SwingWorker sw = new SwingWorker(){
            protected Object doInBackground() throws Exception {
                for(int i = 0; i < 10 ; i++){
                    try {
                        setProgress(i);
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            public void done()
            {
                Process = false;
                container.setFocusable(true);
                container.requestFocus();
            }
        };

        sw.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent event)
            {
                if("progress".equals(event.getPropertyName()))
                {
                    if ( bol)
                    {
                        status.setText("WIN");
                    }
                    else
                    {
                        status.setText("LOOSE");
                        stop = true;
                    }
                }
            }
        });
        sw.execute();
    }

    class TestLis implements ActionListener {
        public void actionPerformed(ActionEvent e) {


           if ( !Game.MetaGame.Process && !Process )
           {
               Game.Init();
               oldScoreMain = 0;
               stop = false;
               status.setText(" ");

           }
            container.setFocusable(true);
            container.requestFocus();
        }
    }


}
