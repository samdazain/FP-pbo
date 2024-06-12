import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ReportPanel extends JPanel {
    private ClothingStore store;
    private JTextArea reportArea;

    public ReportPanel(ClothingStore store) {
        this.store = store;

        // Panel layout and border
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Background color for the panel

        // Initialize report area
        reportArea = new JTextArea();
        reportArea.setFont(new Font("Arial", Font.PLAIN, 14));
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        add(scrollPane, BorderLayout.CENTER);

        // Add a border for visual separation
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Laporan Keuangan");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 20)); // Increase font size
        titledBorder.setTitleJustification(TitledBorder.CENTER); // Center the title
        setBorder(titledBorder);
    }

    // Method to generate and display financial report
    public void generateReport() {
        double totalRevenue = store.getTotalRevenue();
        reportArea.setText(""); // Clear the report area
        reportArea.append("Total Pendapatan: Rp " + totalRevenue + "\n");
        reportArea.append("\nRincian Order:\n");
        for (Order order : store.getOrders()) {
            reportArea.append(order.toString() + "\n");
        }
    }
}
