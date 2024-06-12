mport javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ListProductsPanel extends JPanel {
    private ClothingStore store;
    private JTextArea textArea;

    public ListProductsPanel(ClothingStore store) {
        this.store = store;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Background color for the panel 

        // Text area to display products 
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Add a border for visual separation 
        setBorder(BorderFactory.createTitledBorder("Daftar Produk"));
    }

    // Method to list all items in the store 
    public void listAllItems() {
        textArea.setText(""); // Clear the text area 
        List<Product> products = store.getProducts();
        for (Product product : products) {
            textArea.append(product.toString() + "\n");
        }
    }
} 