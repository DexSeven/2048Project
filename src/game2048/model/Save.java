package game2048.model;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Steph on 14/02/2015.
 */
public class Save {

    Score Score;

    public Save(Score Score)
    {
        this.Score = Score;
    }

    public boolean FilesExist()
    {
        File file = new File("save.uniq");
        if(file.exists()) {
            try {
                String canonicalName = file.getCanonicalFile().getName();
                return canonicalName.equals("save.uniq");
            } catch (final java.io.IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void SetSave()
    {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream("save.uniq");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(Score);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Score GetSave()
    {
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichier = new FileInputStream("save.uniq");
            ois = new ObjectInputStream(fichier);
            return this.Score = (Score) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
