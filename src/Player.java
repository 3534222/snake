
import java.awt.Color;

/**
 *
 * @author Santos
 */
public class Player {

    private final String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;

    /**
     *
     * @param no
     */
    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    /**
     *
     * @return
     */
    public int getPosition() {
        return playerPosn;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param posn
     */
    public void setPosition(int posn) {
        playerPosn = posn;
    }

    /**
     *
     * @param posn
     */
    public void incPosition(int posn) {
        playerPosn += posn;
    }

    /**
     *
     * @param c
     */
    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    /**
     *
     * @return
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     *
     * @param a
     */
    public void incPlayerScore(int a) {
        playerScore += a;
    }

    /**
     *
     * @return
     */
    public int getPlayerScore() {
        return playerScore;
    }

}
