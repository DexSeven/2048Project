package game2048.model;

import com.sun.org.apache.bcel.internal.classfile.Constant;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Steph on 14/02/2015.
 */
public class Save {

    Score Score;
    final String fileName = "save.uniq";

    public Save(Score Score)
    {
        this.Score = Score;
    }

    public boolean FilesExist()
    {
        File file = new File(fileName);
        if(file.exists()) {
            try {
                String canonicalName = file.getCanonicalFile().getName();
                return canonicalName.equals(fileName);
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
            final FileOutputStream file = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(file);
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
            final FileInputStream file = new FileInputStream(fileName);
            ois = new ObjectInputStream(file);
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
