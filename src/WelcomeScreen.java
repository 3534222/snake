
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Santos
 */
public class WelcomeScreen extends JPanel {

    private static final String CARD_TWO = "Two";  // Constante para el nombre del card "Two"
    private static final String NEW_GAME_LABEL = "New Game";
    private static final String QUIT_LABEL = "Quit";

    private final JLabel title;
    private final JButton go;
    private final JButton quit;

    private final MainWindow mw;

    /**
     *
     * @param t
     */
    public void setTitle(String t) {
        title.setText(t);
    }

    /**
     *
     */
    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     *
     */
    public void goButtonActionListener() {
        mw.showCard(CARD_TWO);
    }

    /**
     *
     * @param mw
     */
    public WelcomeScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel();
        add(title);

        //add formatting here
        go = new JButton(NEW_GAME_LABEL);
        quit = new JButton(QUIT_LABEL);

        go.addActionListener((ActionEvent event) -> {
            goButtonActionListener();
        });

        quit.addActionListener((ActionEvent event) -> {
            quitButtonActionListener();
        });

        add(go);
        add(quit);
    }

}
