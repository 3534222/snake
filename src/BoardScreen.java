
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Santos
 */
public class BoardScreen extends JPanel {

    BoardDrawing bd;
    JPanel stats;
    JLabel dieResults;
    JLabel whichPlayer;
    JLabel extraInfo;
    int maxPlayers = 1;
    int currPlayer = 0;
    ArrayList<Portal> portals;
    ArrayList<Player> players;
    JLabel success;
    JButton roll;
    MainWindow mw;

    JButton go;
    JButton quit;

    private Random random = new Random(); // Cambio 2: Crear un objeto Random como campo de clase.

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
        mw.showCard("Two");
        mw.resetAll();
    }

    /**
     *
     * @param m
     */
    public void setMaxPlayers(int m) {
        maxPlayers = m;
    }

    /**
     *
     * @return
     */
    public int returnMaxPlayers() {
        return maxPlayers;
    }

    /**
     *
     */
    public void setUpPlayers() {
        players = new ArrayList<>();
        for (int i = 0; i < returnMaxPlayers(); i++) {
            players.add(new Player(i));
        }

        if (0 < returnMaxPlayers()) {
            players.get(0).setPlayerColor(Color.green);
        }
        if (1 < returnMaxPlayers()) {
            players.get(1).setPlayerColor(Color.blue);
        }
        if (2 < returnMaxPlayers()) {
            players.get(2).setPlayerColor(Color.red);
        }
    }

    /**
     *
     * @param mw
     */
    public BoardScreen(MainWindow mw) {
        this.mw = mw;

        currPlayer = 0;

        go = new JButton("New Game");
        quit = new JButton("Quit");

        go.addActionListener(e -> goButtonActionListener()); // Cambio 4: Lambda para ActionListener.
        quit.addActionListener(e -> quitButtonActionListener()); // Cambio 4: Lambda para ActionListener.

        players = new ArrayList<>();
        players.add(new Player(currPlayer));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        int boardX = 8; // Cambio 1: Renombrar la variable x.
        int boardY = 8; // Cambio 1: Renombrar la variable y.

        bd = new BoardDrawing(boardX, boardY, this);
        bd.setVisible(true);

        int sw = getSize().width;
        int sh = getSize().height;
        int a = (int) (0.75 * ((sw > sh) ? sh : sw));

        bd.setSize(a, a);

        add(bd);

        stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.X_AXIS));
        add(stats);

        stats.add(go);
        stats.add(quit);

        whichPlayer = new JLabel();
        whichPlayer.setText(players.get(currPlayer).getName());
        stats.add(whichPlayer);

        extraInfo = new JLabel();

        success = new JLabel("");

        roll = new JButton("Roll the die!");
        roll.addActionListener((ActionEvent e) -> {
            int rollResult = random.nextInt(6) + 1; // Cambio 2: Uso del objeto Random reutilizado.
            dieResults.setText("You rolled a " + rollResult);
            bd.setPlayer(rollResult, currPlayer);
            extraInfo.setText(bd.ensurePlayerPosition(currPlayer));
            bd.repaint();

            players.get(currPlayer).incPlayerScore(1);

            for (Player p : players) {
                if (p.getPosition() >= boardX * boardY - 1) {
                    success.setText("And the winner is: " + p.getName() + "\nYour score: " + p.getPlayerScore());
                    roll.setVisible(false);
                }
            }

            if (currPlayer == maxPlayers - 1) {
                currPlayer = 0;
            } else {
                currPlayer += 1;
            }

            whichPlayer.setText(players.get(currPlayer).getName());
        });
        roll.setVisible(true);

        stats.add(roll);

        dieResults = new JLabel();
        stats.add(dieResults);

        stats.add(extraInfo);
        stats.add(success);
    }
}
