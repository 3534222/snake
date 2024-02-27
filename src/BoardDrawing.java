
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * La clase  BoardDrawing representa el panel que dibuja el tablero del juego.
 * Se encarga de la representación visual de las celdas, jugadores y portales en el tablero.
 */

public class BoardDrawing extends JPanel {

    int row = 8;
    int col = 8;
    ArrayList<Rectangle> cells;
    int[] cellnos;

    BoardScreen bs;

    /**
     *
     * @param row
     * @param col
     * @param bs
     */
    public BoardDrawing(int row, int col, BoardScreen bs) {
        this.bs = bs;

        this.row = row;
        this.col = col;

        cells = new ArrayList<>();

        cellnos = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i % 2 == 0) {
                    cellnos[i * col + j] = i * col + j;
                } else {
                    cellnos[i * col + j] = i * col + (row - 1 - j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cellnos[i * col + j] = row * col - 1 - cellnos[i * col + j];
            }
        }

        int noPorts = 8;
        bs.portals = new ArrayList<>(noPorts);
        for (int i = 0; i < noPorts; i++) {
            Portal temp = new Portal(row * col);
            bs.portals.add(temp);
        }
    }


    /**
     * El método paintComponent se utiliza para dibujar el tablero del juego y sus elementos.
     *
     * @param g El objeto {@code Graphics} utilizado para pintar.
     */
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / col;
        int cellHeight = height / row;

        int xOffset = (width - (col * cellWidth)) / 2;
        int yOffset = (height - (row * cellHeight)) / 2;

        createCells(xOffset, yOffset, cellWidth, cellHeight);

        drawCells(g2d);
        drawCellNumbers(g2d);
        drawPlayers(g2d);
        drawPortals(g2d);
    }

    private void createCells(int xOffset, int yOffset, int cellWidth, int cellHeight) {
        if (cells.isEmpty()) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    Rectangle latest = new Rectangle(xOffset + (j * cellWidth), yOffset + (i * cellHeight),
                            cellWidth, cellHeight);
                    cells.add(latest);
                }
            }
        }
    }

    private void drawCells(Graphics2D g2d) {
        g2d.setColor(Color.white);
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }

        g2d.setColor(Color.BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }
    }

    private void drawCellNumbers(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        int i = 0;
        for (Rectangle cell : cells) {
            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            i++;
        }
    }

    private void drawPlayers(Graphics2D g2d) {
        for (int pl = 0; pl < bs.maxPlayers; pl++) {
            int playerPosition = bs.players.get(pl).getPosition();
            int cellIndex = findCellIndex(playerPosition);

            if (cellIndex != -1) {
                g2d.setColor(bs.players.get(pl).getPlayerColor());
                Rectangle cell = cells.get(cellIndex);
                g2d.fillRect((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
                g2d.setColor(Color.blue);
            }
        }
    }

    private int findCellIndex(int playerPosition) {
        for (int i = 0; i < cellnos.length; i++) {
            if (cellnos[i] == playerPosition) {
                return i;
            }
        }
        return -1;
    }

    private void drawPortals(Graphics2D g2d) {
        for (Portal port : bs.portals) {
            if (port.returnNature() == -1) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.green);
            }

            int ind;
            int s = port.returnStart();
            for (ind = 0; ind < row * col; ind++) {
                if (cellnos[ind] == s) {
                    break;
                }
            }

            int j;
            int e = port.returnEnd();
            for (j = 0; j < row * col; j++) {
                if (cellnos[j] == e) {
                    break;
                }
            }

            g2d.drawLine((int) cells.get(ind).getCenterX(), (int) cells.get(ind).getCenterY(),
                    (int) cells.get(j).getCenterX(), (int) cells.get(j).getCenterY());
        }
    }

    /**
     * Asegura la posición del jugador en el tablero, actualizándola según los portales.
     *
     * @param pnos El número del jugador.
     * @return Un mensaje que indica el resultado del movimiento del jugador a través de portales.
     */
    
    public String ensurePlayerPosition(int pnos) {
        String message = "";
        for (Portal port : bs.portals) {
            if (bs.players.get(pnos).getPosition() == port.returnStart()) {
                bs.players.get(pnos).setPosition(port.returnEnd());
                if (port.returnNature() == 1) {
                    message += "You are up through the ladder at position " + port.returnStart();
                } else if (port.returnNature() == -1) {
                    message += "Snake at " + port.returnStart() + " got you.";
                }
            }
        }
        return message;
    }

     /**
     * Establece la posición de un jugador en el tablero.
     *
     * @param a    La cantidad para incrementar la posición del jugador.
     * @param pnos El número del jugador.
     */
    
    public void setPlayer(int a, int pnos) {
        bs.players.get(pnos).incPosition(a);
    }
}
