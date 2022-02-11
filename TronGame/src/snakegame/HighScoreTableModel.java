package snakegame;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class HighScoreTableModel extends AbstractTableModel{
    private final ArrayList<HighScore> highscores;
    private final String[] colName = new String[]{ "Név", "Eredmény" };
    
    public HighScoreTableModel(ArrayList<HighScore> highscores){
        this.highscores = highscores;
    }

    @Override
    public int getRowCount() { return highscores.size(); }

    @Override
    public int getColumnCount() { return 2; }

    @Override
    public Object getValueAt(int r,int c)
    {
        HighScore h = highscores.get(r);
        if      (c == 0) return h.name;
        else if (c == 1) return h.score;
        return 0;
    }

    @Override
    public String getColumnName(int i) { return colName[i]; }    
    
}

