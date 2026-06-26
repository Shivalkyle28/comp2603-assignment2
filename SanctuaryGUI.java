import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Swing GUI for searching and viewing animals in a sanctuary.
 *
 * Layout:
 *   NORTH:  Search field, type combo box, injured checkbox, search button
 *   CENTER: Scrollable text area showing results
 *   SOUTH:  Status label showing match count
 */
public class SanctuaryGUI extends JFrame {
    // TODO M10: Declare private Sanctuary field
    private Sanctuary sanctuary;

    // TODO M9: Declare GUI components:
    //   JTextField nameField
    //   JComboBox<String> typeCombo
    //   JCheckBox injuredCheck
    //   JButton searchButton
    //   JTextArea resultArea
    //   JLabel statusLabel
    private JTextField nameField;
    private JComboBox<String> typeCombo;
    private JCheckBox injuredCheck;
    private JButton searchButton;
    private JTextArea resultArea;
    private JLabel statusLabel;

    public SanctuaryGUI() {
        super("Caribbean Wildlife Conservation Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        // TODO M9: Set layout to BorderLayout
        setLayout(new BorderLayout());

        // TODO M9: Build NORTH panel (FlowLayout)
        //   Add JLabel "Search:", JTextField (14 columns), JLabel "Type:",
        //   JComboBox with {"All","Bird","Reptile","Marine"},
        //   JCheckBox "Injured/Critical only", JButton "Search"
        //   Add panel to NORTH
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("Search:"));

        nameField = new JTextField(14);
        northPanel.add(nameField);

        northPanel.add(new JLabel("Type:"));

        typeCombo = new JComboBox<String>(new String[] {"All", "Bird", "Reptile", "Marine"});
        northPanel.add(typeCombo);

        injuredCheck = new JCheckBox("Injured/Critical only");
        northPanel.add(injuredCheck);

        searchButton = new JButton("Search");
        northPanel.add(searchButton);

        add(northPanel, BorderLayout.NORTH);

        // TODO M9: Build CENTER
        //   Create JTextArea, set monospaced font, make non-editable
        //   Wrap in JScrollPane, add to CENTER
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        // TODO M9: Build SOUTH
        //   Create JLabel "Ready", add to SOUTH
        statusLabel = new JLabel("Ready");
        add(statusLabel, BorderLayout.SOUTH);

        // TODO M11: Add ActionListener to searchButton that calls runSearch()
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runSearch();
            }
        });

        // TODO M11: Add KeyListener to nameField that calls runSearch() on keyReleased
        nameField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                runSearch();
            }
        });

        setLocationRelativeTo(null);
    }

    /**
     * Stores the Sanctuary to search over.
     * TODO M10: Implement setModel
     */
    public void setModel(Sanctuary s) {
        // TODO M10: Store the sanctuary reference
        sanctuary = s;

        // TODO M10: Optionally update the window title
        if (sanctuary != null) {
            setTitle("Caribbean Wildlife Conservation Tracker - " + sanctuary.getName());
            runSearch();
        }
    }

    /**
     * Filters the sanctuary's animals based on the GUI controls and
     * displays matching results.
     *
     * TODO M11: Implement runSearch
     *
     * Steps:
     * 1. Get text from nameField (trim, convert to lowercase)
     * 2. Get selected type from typeCombo
     * 3. Get checkbox state from injuredCheck
     * 4. Loop through sanctuary's animals:
     *    - If text is non-empty, keep only animals whose species or nickname
     *      contains the text (case-insensitive)
     *    - If type is not "All", keep only matching type
     *    - If checkbox is selected, keep only "Injured" or "Critical" animals
     * 5. Build result string and set in resultArea
     * 6. Set statusLabel: "No matches", "1 result", or "N results"
     */
    private void runSearch() {
        // TODO M11: Implement filtering and display
        if (sanctuary == null) {
            resultArea.setText("");
            statusLabel.setText("No sanctuary loaded");
            return;
        }

        String searchText = nameField.getText().trim().toLowerCase();
        String selectedType = (String) typeCombo.getSelectedItem();
        boolean injuredOnly = injuredCheck.isSelected();

        StringBuilder results = new StringBuilder();
        int count = 0;

        ArrayList<Animal> animals = sanctuary.getAnimals();

        for (Animal a : animals) {
            boolean matches = true;

            if (!searchText.isEmpty()) {
                String species = a.getSpecies().toLowerCase();
                String nickname = a.getNickname().toLowerCase();

                if (!species.contains(searchText) && !nickname.contains(searchText)) {
                    matches = false;
                }
            }

            if (!selectedType.equals("All")) {
                if (!a.getType().equals(selectedType)) {
                    matches = false;
                }
            }

            if (injuredOnly) {
                if (!a.getHealthStatus().equals("Injured") &&
                    !a.getHealthStatus().equals("Critical")) {
                    matches = false;
                }
            }

            if (matches) {
                results.append(a.toString()).append("\n");
                count++;
            }
        }

        resultArea.setText(results.toString());

        if (count == 0) {
            statusLabel.setText("No matches");
        } else if (count == 1) {
            statusLabel.setText("1 result");
        } else {
            statusLabel.setText(count + " results");
        }
    }

    /**
     * Creates a demo sanctuary, populates it, and launches the GUI.
     *
     * TODO M12: Implement main method
     */
    public static void main(String[] args) {
        // TODO M12: Create Sanctuary, add animals, create GUI, wire model, show
        Sanctuary caroni = new Sanctuary("Caroni Bird Sanctuary", "Trinidad", 20);

        caroni.addAnimal(new Bird("Scarlet Ibis", "Ruby", "Trinidad", 0.35, "Healthy", 60.0, true));
        caroni.addAnimal(new Bird("Scarlet Ibis", "Blaze", "Trinidad", 0.40, "Healthy", 58.0, true));
        caroni.addAnimal(new Bird("Cocrico", "Dusty", "Trinidad", 0.25, "Injured", 30.0, true));
        caroni.addAnimal(new Reptile("Spectacled Caiman", "Brutus", "Trinidad", 45.0, "Healthy", false, 180.0));
        caroni.addAnimal(new Reptile("Green Anaconda", "Medusa", "Trinidad", 30.0, "Critical", false, 350.0));
        caroni.addAnimal(new Marine("Leatherback Turtle", "Atlas", "Trinidad", 500.0, "Healthy", 1200.0, 8000));

        SanctuaryGUI gui = new SanctuaryGUI();
        gui.setModel(caroni);
        gui.setVisible(true);
    }
}