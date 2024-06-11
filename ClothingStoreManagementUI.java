import javax.swing.*;
import java.awt.*;

public class ClothingStoreManagementUI {
    private ClothingStore store;
    private JTabbedPane tabbedPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ClothingStoreManagementUI window = new ClothingStoreManagementUI();
                window.initUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ClothingStoreManagementUI() {
        store = new ClothingStore();
    }

    private void initUI() {
        JFrame frame = new JFrame("Clothing Store Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 16)); // Set tab font
        frame.getContentPane().add(tabbedPane);

        // Create the panels for different functionalities
        ListProductsPanel listProductsPanel = new ListProductsPanel(store);
        AddProductPanel addProductPanel = new AddProductPanel(store, listProductsPanel);
        OrderPanel orderPanel = new OrderPanel(store);
        ReportPanel reportPanel = new ReportPanel(store);

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Daftar Item", listProductsPanel);
        tabbedPane.addTab("Tambah Item", addProductPanel);
        tabbedPane.addTab("Order", orderPanel);
        tabbedPane.addTab("Laporan Keuangan", reportPanel);

        // Enhance frame appearance
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setVisible(true);
    }
}
