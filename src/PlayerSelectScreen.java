
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//needs massive aesthetic rewrites
public class PlayerSelectScreen extends JPanel {

    JButton go;
    JButton quit;
    MainWindow mw;

    JRadioButton opt1;
    JRadioButton opt2;
    JRadioButton opt3;

    public void quitButtonActionListener() {
        mw.showCard("One");
    }

    public void goButtonActionListener() {
        playerOptions();
        mw.s4.setUpPlayers();
        mw.showCard("Three");
    }

    // Modificación de la variable playerOptions para mejorar la legibilidad 
    // del código, y hacerlo mas mantenible
    public void playerOptions() {
        int selectedPlayers = getSelectedPlayers(); //Cambio de nombre para mejorar la legibilidad
        mw.s4.setMaxPlayers(selectedPlayers); //Cambio de nombre para mejorar la legibilidad
    }

    //Creición de  un método getSelectedPlayers para manejar la lógica de obtener la cantidad de 
    //jugadores seleccionados. Esto mejora la legibilidad del código y centraliza la lógica 
    //de selección en un solo lugar.    
    private int getSelectedPlayers() { //Cambio de nombre para mejorar la legibilidad
        int defaultPlayers = 1; //Cambio de nombre para mejorar la ligibilidad

        if (opt1.isSelected()) {
            return 1;
        } else if (opt2.isSelected()) {
            return 2;
        } else if (opt3.isSelected()) {
            return 3;
        }

        return defaultPlayers;
    }

    public PlayerSelectScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Etiqueta para la selección de jugadores
        JLabel selectPlayersLabel = new JLabel("Select Players: "); //Cambio de nombre para mejorar la ligibilidad
        add(selectPlayersLabel);

        // Etiqueta que indica el valor predeterminado
        JLabel defaultLabel = new JLabel("Default: 1 Player"); //Cambio de nombre para mejorar la ligibilidad
        add(defaultLabel);

        //set up radio buttons
        setupRadioButtons();

        go = new JButton("Customize Board");
        quit = new JButton("Back");

        // Configuración de los listeners de los botones
        setupButtonListeners();

        add(go);
        add(quit);
    }

    private void setupRadioButtons() {
        opt1 = new JRadioButton("1 Player (Default)");
        opt1.setSelected(true);

        opt2 = new JRadioButton("2 Players");
        opt3 = new JRadioButton("3 Players");

        ButtonGroup grp = new ButtonGroup();
        grp.add(opt1);
        grp.add(opt2);
        grp.add(opt3);

        add(opt1);
        add(opt2);
        add(opt3);

        // Configuración de los listeners para los radio buttons
        setupRadioButtonListeners();
    }

    // Creación de un método setupRadioButtonListeners para encapsular la 
    // configuración de los listeners de los radio buttons, mejorando 
    // la organización del código.
    private void setupRadioButtonListeners() {
        ActionListener playerOptionsListener = (ActionEvent event) -> {
            playerOptions();
        };

        opt1.addActionListener(playerOptionsListener);
        opt2.addActionListener(playerOptionsListener);
        opt3.addActionListener(playerOptionsListener);
    }

    private void setupButtonListeners() {
        go.addActionListener((ActionEvent event) -> {
            goButtonActionListener();
        });

        quit.addActionListener((ActionEvent event) -> {
            quitButtonActionListener();
        });
    }
}
