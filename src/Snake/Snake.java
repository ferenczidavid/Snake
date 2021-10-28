package Snake;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Snake extends JFrame {

    public Snake(String title) {
        super(title);

        JMenuBar mb = new JMenuBar();
        JMenu mNewGame = new JMenu("New Game");
        JMenuItem mi500x500 = new JMenuItem("500 x 500");

        JLabel TimeDisplay = new JLabel("    0");
        TimeDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        mNewGame.setFont(new Font("Serif", Font.BOLD, 16));
        setJMenuBar(mb);
        mb.add(mNewGame);
        mNewGame.add(mi500x500);
    }

}