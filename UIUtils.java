import javax.swing.*;
import java.awt.*;

public class UIUtils {
    // Method to create a labeled field
    public static JPanel createLabeledField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(80, 30)); // Fixed width for alignment
        label.setFont(new Font("Arial", Font.PLAIN, 14)); // Adjust font size
        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height)); // Stretch to fill the width
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
        return panel;
    }
}
