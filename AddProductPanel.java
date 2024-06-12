import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddProductPanel extends JPanel {
private JTextField nameField, priceField, colorField, materialField;
private JComboBox<String> sizeComboBox;
private ClothingStore store;
private ListProductsPanel listProductsPanel;
public AddProductPanel(ClothingStore store, ListProductsPanel 
listProductsPanel) {
this.store = store;
this.listProductsPanel = listProductsPanel;
// Setting the panel layout and border
setLayout(new GridBagLayout());
setBorder(new EmptyBorder(10, 10, 10, 10));
setBackground(Color.WHITE); // Background color for the panel
// Initialize input fields and combo box
nameField = new JTextField(20);
priceField = new JTextField(20);
sizeComboBox = new JComboBox<>(new String[]{"S", "M", "L", "XL", "XXL"});
colorField = new JTextField(20);
materialField = new JTextField(20);
// Add labeled fields
add(UIUtils.createLabeledField("Nama:", nameField), createConstraints(0, 
0));
add(UIUtils.createLabeledField("Harga:", priceField),
createConstraints(0, 1));
add(UIUtils.createLabeledField("Size:", sizeComboBox), 
createConstraints(0, 2));
add(UIUtils.createLabeledField("Warna:", colorField), 
createConstraints(0, 3));
add(UIUtils.createLabeledField("Material:", materialField), 
createConstraints(0, 4));
// Add product button
JButton btnAddProduct = new JButton("Tambah Item");
styleButton(btnAddProduct, new Color(0, 0, 139)); // Applying blue color 
to the button
btnAddProduct.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
addProduct();
}
});
// Center the button in a panel with full width alignment
JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
buttonPanel.add(btnAddProduct);
buttonPanel.setBackground(Color.WHITE); // Ensure panel matches the 
background
// Add the button panel with GridBagConstraints to span all columns
GridBagConstraints gbcButtonPanel = createConstraints(0, 5);
gbcButtonPanel.gridwidth = GridBagConstraints.REMAINDER; // Span all
columns
gbcButtonPanel.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
add(buttonPanel, gbcButtonPanel);
// Add a border for visual separation
TitledBorder titledBorder = BorderFactory.createTitledBorder("Tambah Item 
Baru");
titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 20)); // Increase 
font size
titledBorder.setTitleJustification(TitledBorder.CENTER); // Center the 
title
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
// Method to add a product to the store
private void addProduct() {
try {
String name = nameField.getText();
double price = Double.parseDouble(priceField.getText());
String size = (String) sizeComboBox.getSelectedItem();
String color = colorField.getText();
String material = materialField.getText();
store.addProduct(new Product(name, price, size, color, material));
listProductsPanel.listAllItems(); // Update the product list panel
// Clear the fields after adding
clearFields();
// Show a success message
JOptionPane.showMessageDialog(this, "Item Berhasil Ditambahkan!", 
"Success", JOptionPane.INFORMATION_MESSAGE);
} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(this, "Masukan Data Yang Valid.", 
"Input Error", JOptionPane.ERROR_MESSAGE);
}
}
// Method to clear input fields
private void clearFields() {
nameField.setText("");
priceField.setText("");
sizeComboBox.setSelectedIndex(0);
colorField.setText("");
materialField.setText("");
}
// Helper method to style a button
private void styleButton(JButton button, Color backgroundColor) {
button.setBackground(backgroundColor);
button.setForeground(Color.WHITE);
button.setFont(new Font("Arial", Font.BOLD, 12));
button.setFocusPainted(false);
button.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 
255))); // DodgerBlue border
}
}
