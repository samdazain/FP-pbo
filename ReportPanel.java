import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportPanel extends JPanel {
    private JTextPane reportTextPane;
    private JButton btnTransactionComplete;

    public ReportPanel(ClothingStore store) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Add header
        JLabel headerLabel = new JLabel("Laporan Keuangan");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 128, 0)); // Dark Green
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // JTextPane for report details with HTML content
        reportTextPane = new JTextPane();
        reportTextPane.setContentType("text/html");
        reportTextPane.setEditable(false);
        reportTextPane.setText("<html><body style='font-family: Arial, sans-serif; margin: 20px;'><h2 style='text-align: center;'>Riwayat Transaksi</h2></body></html>");
        JScrollPane reportScrollPane = new JScrollPane(reportTextPane);
        reportScrollPane.setPreferredSize(new Dimension(800, 400));
        add(reportScrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnTransactionComplete = new JButton("Hasil Pemasukan");
        styleButton(btnTransactionComplete, new Color(0, 128, 0)); // Dark Green
        btnTransactionComplete.addActionListener(e -> showReport(store));
        buttonPanel.add(btnTransactionComplete);

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

    // Method to show the report
    private void showReport(ClothingStore store) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("<html><body style='font-family: Arial, sans-serif; margin: 20px;'>");
        reportBuilder.append("<h2 style='text-align: center;'>Riwayat Transaksi</h2>");
        reportBuilder.append("<ul>");

        for (Order order : store.getOrders()) {
            reportBuilder.append("<li>").append(order.toString().replace("\n", "<br>")).append("</li><br>");
        }

        reportBuilder.append("</ul>");
        reportBuilder.append("<h3>Total pemasukan: $").append(String.format("%.2f", store.getTotalRevenue())).append("</h3>");
        reportBuilder.append("</body></html>");

        reportTextPane.setText(reportBuilder.toString());
        reportTextPane.setCaretPosition(0); // Scroll to the top
    }
}
