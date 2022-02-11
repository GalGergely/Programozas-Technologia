package snake;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class HighScoreWindow extends JDialog{
    private final JTable table;
    
    public HighScoreWindow(ArrayList<HighScore> highscores, JFrame parent){
        super(parent, true);
        table = new JTable(new HighScoreTableModel(highscores));
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table));
        setSize(400,400);
        setTitle("Eredmények");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
