import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class OrderPanel extends JPanel {
    private JTextPane orderTextPane;
    private JButton btnPlaceOrder;
    private JButton btnDone;

    public OrderPanel(ClothingStore store) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Add header
        JLabel headerLabel = new JLabel("Pemesanan");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 128, 0)); // Dark Green
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // JTextPane for order details with HTML content
        orderTextPane = new JTextPane();
        orderTextPane.setContentType("text/html");
        orderTextPane.setEditable(false);
        orderTextPane.setText("<html><body style='font-family: Arial, sans-serif; margin: 20px;'><h2 style='text-align: center;'>Detail Pemesanan</h2></body></html>");
        JScrollPane orderScrollPane = new JScrollPane(orderTextPane);
        orderScrollPane.setPreferredSize(new Dimension(800, 400));
        add(orderScrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnPlaceOrder = new JButton("Pemesanan");
        styleButton(btnPlaceOrder, new Color(0, 128, 0)); // Dark Green
        btnPlaceOrder.addActionListener(e -> placeOrder(store));
        buttonPanel.add(btnPlaceOrder);

        btnDone = new JButton("Selesai");
        styleButton(btnDone, new Color(0, 0, 139)); // Dark Red
        btnDone.addActionListener(e -> completeOrder(store));
        btnDone.setEnabled(false); // Initially disabled
        buttonPanel.add(btnDone);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to style buttons
    private void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    // Method to place an order
    private void placeOrder(ClothingStore store) {
        Order order = new Order();
        List<Product> products = store.getProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada item yang bisa dipesan.");
            return;
        }

        String[] options = products.stream().map(Product::toString).toArray(String[]::new);
        boolean continueOrdering = true;

        // Use HTML for order details
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("<html><body style='font-family: Arial, sans-serif; margin: 20px;'>");
        orderDetails.append("<h2 style='text-align: center;'>Detail Pemesanan</h2>");
        orderDetails.append("<ul>");

        while (continueOrdering) {
            String selectedItem = (String) JOptionPane.showInputDialog(null, "Pilih item yang akan Dipesan:", "Order", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (selectedItem == null) {
                continueOrdering = false;
            } else {
                for (Product item : products) {
                    if (item.toString().equals(selectedItem)) {
                        order.addItem(item);
                        orderDetails.append("<li>").append(item.toString()).append("</li>");
                    }
                }
            }
        }

        orderDetails.append("</ul>");
        orderDetails.append("<h3>Total: $").append(String.format("%.2f", order.getTotalPrice())).append("</h3>");
        orderDetails.append("</body></html>");

        orderTextPane.setText(orderDetails.toString());
        orderTextPane.setCaretPosition(0); // Scroll to the top

        // Enable the "Done" button after placing the order
        btnDone.setEnabled(true);
    }

    // Method to complete the order
    private void completeOrder(ClothingStore store) {
        String orderDetails = orderTextPane.getText();
        if (!orderDetails.contains("<li>")) {
            JOptionPane.showMessageDialog(this, "Tidak ada item yang dapat diedit.");
            return;
        }

        // Parse the HTML and create the order
        Order order = new Order();
        List<Product> products = store.getProducts();

        // Extract product names from the HTML list items
        String[] selectedProducts = orderDetails.split("<li>|</li>");
        for (String selectedItem : selectedProducts) {
            if (selectedItem.trim().isEmpty()) continue;
            for (Product item : products) {
                if (item.toString().equals(selectedItem.trim())) {
                    order.addItem(item);
                }
            }
        }

        store.placeOrder(order);
        JOptionPane.showMessageDialog(this, "Pemesanan Berhasil!");
        orderTextPane.setText("<html><body style='font-family: Arial, sans-serif; margin: 20px;'><h2 style='text-align: center;'>Detail Pemesanan</h2></body></html>");
        btnDone.setEnabled(false); // Disable the "Done" button after completion
    }
}
