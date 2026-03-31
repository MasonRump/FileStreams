import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {

    JTextField idField = new JTextField(10);
    JTextField nameField = new JTextField(20);
    JTextField descField = new JTextField(20);
    JTextField costField = new JTextField(10);
    JTextField countField = new JTextField("0", 5);

    int recordCount = 0;

    public RandProductMaker() {
        setTitle("Product Maker");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID:")); add(idField);
        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Description:")); add(descField);
        add(new JLabel("Cost:")); add(costField);
        add(new JLabel("Record Count:")); add(countField);

        JButton addBtn = new JButton("Add Product");
        add(addBtn);

        JButton quitBtn = new JButton("Quit");
        add(quitBtn);

        addBtn.addActionListener(e -> addProduct());
        quitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void addProduct() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String desc = descField.getText();
            double cost = Double.parseDouble(costField.getText());

            if (id.isEmpty() || name.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
                return;
            }

            RandomAccessFile file = new RandomAccessFile("products.dat", "rw");
            file.seek(file.length());

            Product p = new Product(id, name, desc, cost);
            p.writeToFile(file);

            file.close();

            recordCount++;
            countField.setText(String.valueOf(recordCount));

            idField.setText("");
            nameField.setText("");
            descField.setText("");
            costField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RandProductMaker();
    }
}