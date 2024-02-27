
import java.util.Random;

/**
 *
 * @author Santos
 */
public class Portal {

    private final int nature; //-1 for snake , +1 for ladder
    private final int start;
    private final int end;

    /**
     *
     * @param maxCells
     */
    public Portal(int maxCells) {   //creates random portals
        Random luck = new Random();
        start = luck.nextInt(maxCells);
        end = luck.nextInt(maxCells);
        if (start < end) {
            nature = 1;
        } else {
            nature = -1;
        }
    }

    /**
     *
     * @return
     */
    public int returnNature() {
        return nature;
    }

    /**
     *
     * @return
     */
    public int returnStart() {
        return start;
    }

    /**
     *
     * @return
     */
    public int returnEnd() {
        return end;
    }

}
