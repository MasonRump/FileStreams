import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {

    JTextField searchField = new JTextField(20);
    JTextArea resultsArea = new JTextArea(10, 30);

    public RandProductSearch() {
        setTitle("Product Search");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Search Name:"));
        add(searchField);

        JButton searchBtn = new JButton("Search");
        add(searchBtn);

        add(new JScrollPane(resultsArea));

        searchBtn.addActionListener(e -> search());

        setVisible(true);
    }

    private void search() {
        resultsArea.setText("");

        try {
            RandomAccessFile file = new RandomAccessFile("products.dat", "r");

            String term = searchField.getText().toLowerCase();

            for (long i = 0; i < file.length(); i += Product.RECORD_SIZE) {
                file.seek(i);

                Product p = Product.readFromFile(file);

                if (p.getName().toLowerCase().contains(term)) {
                    resultsArea.append(p.toString() + "\n");
                }
            }

            file.close();

        } catch (Exception e) {
            resultsArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RandProductSearch();
    }
}