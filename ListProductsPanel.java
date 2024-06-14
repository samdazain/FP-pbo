import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ListProductsPanel extends JPanel {
    private ClothingStore store;
    private JTextPane textPane;
    private JTextField searchField;

    public ListProductsPanel(ClothingStore store) {
        this.store = store;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Initialize JTextPane for HTML content
        textPane = new JTextPane();
        textPane.setContentType("text/html"); // Set JTextPane to render HTML
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Set preferred size
        add(scrollPane, BorderLayout.CENTER);

        // Search and Control Panel Setup
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        JButton btnListAll = new JButton("Daftar Item");
        JButton btnEditProduct = new JButton("Edit Item");
        JButton btnDeleteProduct = new JButton("Hapus Item");

        controlPanel.add(searchLabel);
        controlPanel.add(searchField);
        controlPanel.add(btnSearch);
        controlPanel.add(btnListAll);
        controlPanel.add(btnEditProduct);
        controlPanel.add(btnDeleteProduct);
        add(controlPanel, BorderLayout.NORTH);

        // Add Action Listeners
        btnSearch.addActionListener(e -> searchItems());
        btnListAll.addActionListener(e -> listAllItems());
        btnEditProduct.addActionListener(e -> editProduct());
        btnDeleteProduct.addActionListener(e -> deleteProduct());

        // Apply initial listing
        listAllItems();

        // Style the components
        applyComponentStyles(controlPanel, btnSearch, btnListAll, btnEditProduct, btnDeleteProduct);
    }

    // Method to list all items with enhanced styling
    public void listAllItems() {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body style='font-family: Arial, sans-serif; margin: 20px;'>");
        htmlContent.append("<h2 style='color: #4CAF50; text-align: center;'>Daftar Semua Item</h2>");
        htmlContent.append("<table style='width: 100%; border-collapse: collapse;'>");
        htmlContent.append("<tr style='background-color: #f2f2f2;'>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Nama</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Harga</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Size</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Warna</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Material</th>");
        htmlContent.append("</tr>");

        for (Product item : store.getProducts()) {
            htmlContent.append("<tr style='text-align: center;'>");
            htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getName()).append("</td>");
            htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(String.format("$%.2f", item.getPrice())).append("</td>");
            htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getSize()).append("</td>");
            htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getColor()).append("</td>");
            htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getMaterial()).append("</td>");
            htmlContent.append("</tr>");
        }

        htmlContent.append("</table>");
        htmlContent.append("</body></html>");

        textPane.setText(htmlContent.toString());
        textPane.setCaretPosition(0); // Scroll to the top
    }

    // Method to search items with enhanced styling
    private void searchItems() {
        String keyword = searchField.getText().toLowerCase();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body style='font-family: Arial, sans-serif; margin: 20px;'>");
        htmlContent.append("<h2 style='color: #4CAF50; text-align: center;'>Hasil Pencarian</h2>");
        htmlContent.append("<table style='width: 100%; border-collapse: collapse;'>");
        htmlContent.append("<tr style='background-color: #f2f2f2;'>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Nama</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Harga</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Size</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Warna</th>");
        htmlContent.append("<th style='padding: 10px; border: 1px solid #ddd;'>Material</th>");
        htmlContent.append("</tr>");

        for (Product item : store.getProducts()) {
            if (item.toString().toLowerCase().contains(keyword)) {
                htmlContent.append("<tr style='text-align: center;'>");
                htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getName()).append("</td>");
                htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(String.format("$%.2f", item.getPrice())).append("</td>");
                htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getSize()).append("</td>");
                htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getColor()).append("</td>");
                htmlContent.append("<td style='padding: 10px; border: 1px solid #ddd;'>").append(item.getMaterial()).append("</td>");
                htmlContent.append("</tr>");
            }
        }

        htmlContent.append("</table>");
        htmlContent.append("</body></html>");

        textPane.setText(htmlContent.toString());
        textPane.setCaretPosition(0); // Scroll to the top
    }

    // Method to edit a product
    private void editProduct() {
        List<Product> products = store.getProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak Ada Item untuk Diedit.");
            return;
        }
        String[] options = products.stream().map(Product::toString).toArray(String[]::new);
        String selectedItem = (String) JOptionPane.showInputDialog(null, "Pilih Item yang ingin Diubah:", "Edit Product", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (selectedItem != null) {
            for (Product item : products) {
                if (item.toString().equals(selectedItem)) {
                    String name = JOptionPane.showInputDialog("Masukan Nama:", item.getName());
                    double price = Double.parseDouble(JOptionPane.showInputDialog("Masukan Harga:", item.getPrice()));
                    String size = (String) JOptionPane.showInputDialog(null, "Masukan Ukuran:", "Edit Size", JOptionPane.QUESTION_MESSAGE, null, new String[]{"S", "M", "L", "XL", "XXL"}, item.getSize());
                    String color = JOptionPane.showInputDialog("Masukan Warna:", item.getColor());
                    String material = JOptionPane.showInputDialog("Masukan material:", item.getMaterial());
                    store.getProducts().remove(item);
                    store.addProduct(new Product(name, price, size, color, material));
                    listAllItems();
                    break;
                }
            }
        }
    }

    // Method to delete a product
    private void deleteProduct() {
        List<Product> products = store.getProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada item yang bisa dihapus.");
            return;
        }
        String[] options = products.stream().map(Product::toString).toArray(String[]::new);
        String selectedItem = (String) JOptionPane.showInputDialog(null, "Pilih item yang akan dihapus:", "Delete Product", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (selectedItem != null) {
            for (Product item : products) {
                if (item.toString().equals(selectedItem)) {
                    store.getProducts().remove(item);
                    listAllItems();
                    break;
                }
            }
        }
    }

    // Method to apply styles to components
    private void applyComponentStyles(JPanel controlPanel, JButton... buttons) {
        // Style Control Panel
        controlPanel.setBackground(Color.LIGHT_GRAY);

        // Style Buttons with Blue Color
        for (JButton button : buttons) {
            styleButton(button, new Color(0, 0, 139)); // DodgerBlue
        }

        // Style Text Field
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    // Helper method to style a button
    private void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }
}
