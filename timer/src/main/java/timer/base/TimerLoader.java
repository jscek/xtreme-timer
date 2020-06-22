package timer.base;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimerLoader {
    public List<TimerRecord> loadFromFile(String filename)  {
        List<TimerRecord> list = new ArrayList<>();

        if (filename != null) {
            FileInputStream fis = null;
            try {
                if (!filename.endsWith(".txt")) {
                    filename += ".txt";
                }
                fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (ArrayList<TimerRecord>) ois.readObject();

                ois.close();
                fis.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        }
        return list;
    }
}
