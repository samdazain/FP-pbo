import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderPanel extends JPanel {
    private ClothingStore store;
    private JComboBox<Product> productComboBox;
    private JTextField quantityField;

    public OrderPanel(ClothingStore store) {
        this.store = store;

        // Panel layout and border
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE); // Background color for the panel

        // Initialize product combo box
        productComboBox = new JComboBox<>();
        for (Product product : store.getProducts()) {
            productComboBox.addItem(product);
        }

        // Initialize quantity field
        quantityField = new JTextField(10);

        // Add labeled fields
        add(UIUtils.createLabeledField("Produk:", productComboBox), createConstraints(0, 0));
        add(UIUtils.createLabeledField("Kuantitas:", quantityField), createConstraints(0, 1));

        // Add order button
        JButton btnOrder = new JButton("Pesan");
        styleButton(btnOrder, new Color(0, 0, 139)); // Applying blue color to the button
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

        // Center the button in a panel with full width alignment
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnOrder);
        buttonPanel.setBackground(Color.WHITE); // Ensure panel matches the background

        // Add the button panel with GridBagConstraints to span all columns
        GridBagConstraints gbcButtonPanel = createConstraints(0, 2);
        gbcButtonPanel.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        gbcButtonPanel.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        add(buttonPanel, gbcButtonPanel);

        // Add a border for visual separation
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Pesan Produk");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 20)); // Increase font size
        titledBorder.setTitleJustification(TitledBorder.CENTER); // Center the title
        setBorder(titledBorder);
    }

    // Helper method to create GridBagConstraints
    private GridBagConstraints createConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    // Method to place an order
    private void placeOrder() {
        try {
            Product selectedProduct = (Product) productComboBox.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());
            if (selectedProduct != null && quantity > 0) {
                Order order = new Order(selectedProduct, quantity);
                store.placeOrder(order);
                // Show a success message
                JOptionPane.showMessageDialog(this, "Order Berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Clear the quantity field after placing order
                quantityField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Masukan Data Yang Valid.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukan Data Yang Valid.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to style a button
    private void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255))); // DodgerBlue border
    }
}
