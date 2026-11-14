
        package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class AddMedicinePage extends JFrame {

    private JTextField nameField;
    private JTextField categoryField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField expiryField;

    private MedicinePage medicinePage;
    private int editRow = -1;

    private final Color NAVY = new Color(30, 60, 114);
    private final Color GREEN = new Color(52, 199, 154);
    private final Color BACKGROUND = new Color(246, 250, 250);
    private final Color SUBTEXT = new Color(70, 100, 130);
    private final Color BORDER = new Color(220, 228, 228);

    // ADD MODE

    public AddMedicinePage(MedicinePage medicinePage) {

        this.medicinePage = medicinePage;
        this.editRow = -1;

        createPage();
    }

    // EDIT MODE

    public AddMedicinePage(MedicinePage medicinePage, int row) {

        this.medicinePage = medicinePage;
        this.editRow = row;

        createPage();
        loadMedicineDetails(row);
    }

    // PAGE CREATION

    private void createPage() {

        setTitle(
                editRow == -1
                        ? "Pharmacy Management System - Add Medicine"
                        : "Pharmacy Management System - Edit Medicine"
        );

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(850, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                if (medicinePage != null) {
                    medicinePage.setVisible(true);
                }
            }
        });

        setLayout(new BorderLayout());

        // HEADER

        HeaderPanel headerPanel = new HeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // MAIN PANEL

        JPanel mainPanel = new JPanel(new GridBagLayout());

        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(25, 50, 25, 50));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // TITLE

        JLabel title = new JLabel(
                editRow == -1 ? "Add Medicine" : "Edit Medicine"
        );

        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(NAVY);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 5, 0);

        mainPanel.add(title, gbc);

        // SUBTITLE

        JLabel subtitle = new JLabel(
                editRow == -1
                        ? "Enter the details of the new medicine"
                        : "Update the medicine details below"
        );

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(SUBTEXT);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);

        mainPanel.add(subtitle, gbc);

        // FORM PANEL

        JPanel formPanel = new JPanel(new GridBagLayout());

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        new EmptyBorder(20, 30, 20, 30)
                )
        );

        GridBagConstraints formGbc = new GridBagConstraints();

        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.weightx = 1.0;
        formGbc.insets = new Insets(8, 10, 8, 10);

        // INPUT FIELDS

        nameField = new JTextField();
        categoryField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();
        expiryField = new JTextField();

        addFormRow(formPanel, formGbc, 0,
                "Medicine Name", nameField);

        addFormRow(formPanel, formGbc, 1,
                "Category", categoryField);

        addFormRow(formPanel, formGbc, 2,
                "Quantity", quantityField);

        addFormRow(formPanel, formGbc, 3,
                "Price (₹)", priceField);

        addFormRow(formPanel, formGbc, 4,
                "Expiry Date (MM/YYYY)", expiryField);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 15, 0);

        mainPanel.add(formPanel, gbc);

        // BUTTON PANEL

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 5)
        );

        buttonPanel.setOpaque(false);

        JButton saveButton = new JButton(
                editRow == -1 ? "Save Medicine" : "Update Medicine"
        );

        JButton clearButton = new JButton("Clear");
        JButton cancelButton = new JButton("Cancel");

        styleButton(saveButton, GREEN);
        styleButton(clearButton, new Color(70, 130, 190));
        styleButton(cancelButton, new Color(220, 80, 80));

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(cancelButton);

        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 0, 0, 0);

        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // FOOTER

        FooterPanel footerPanel = new FooterPanel();
        add(footerPanel, BorderLayout.SOUTH);

        // SAVE BUTTON

        saveButton.addActionListener(e -> saveMedicine());

        // CLEAR BUTTON

        clearButton.addActionListener(e -> {

            boolean hasData =
                    !nameField.getText().trim().isEmpty()
                            || !categoryField.getText().trim().isEmpty()
                            || !quantityField.getText().trim().isEmpty()
                            || !priceField.getText().trim().isEmpty()
                            || !expiryField.getText().trim().isEmpty();

            if (hasData) {

                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to clear all fields?",
                        "Clear Fields",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            clearFields();
        });

        // CANCEL BUTTON

        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // LOAD EXISTING MEDICINE

    private void loadMedicineDetails(int row) {

        if (medicinePage == null) {
            return;
        }

        nameField.setText(medicinePage.getMedicineValue(row, 1));
        categoryField.setText(medicinePage.getMedicineValue(row, 2));
        quantityField.setText(medicinePage.getMedicineValue(row, 3));
        priceField.setText(medicinePage.getMedicineValue(row, 4));
        expiryField.setText(medicinePage.getMedicineValue(row, 5));
    }

    // SAVE OR UPDATE MEDICINE

    private void saveMedicine() {

        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        String quantity = quantityField.getText().trim();
        String price = priceField.getText().trim();
        String expiry = expiryField.getText().trim();

        // EMPTY FIELD VALIDATION

        if (name.isEmpty()) {
            showError("Medicine name cannot be empty.");
            nameField.requestFocusInWindow();
            return;
        }

        if (category.isEmpty()) {
            showError("Category cannot be empty.");
            categoryField.requestFocusInWindow();
            return;
        }

        if (quantity.isEmpty()) {
            showError("Quantity cannot be empty.");
            quantityField.requestFocusInWindow();
            return;
        }

        if (price.isEmpty()) {
            showError("Price cannot be empty.");
            priceField.requestFocusInWindow();
            return;
        }

        if (expiry.isEmpty()) {
            showError("Expiry date cannot be empty.");
            expiryField.requestFocusInWindow();
            return;
        }

        // MEDICINE NAME VALIDATION

        if (!name.matches("[A-Za-z0-9 ()-]{2,50}")) {

            showError(
                    "Enter a valid medicine name.\n"
                            + "Use 2-50 letters, numbers, spaces, brackets or hyphens."
            );

            nameField.requestFocusInWindow();
            return;
        }

        // CATEGORY VALIDATION

        if (!category.matches("[A-Za-z ]{2,30}")) {

            showError(
                    "Category should contain only letters and spaces "
                            + "and be 2-30 characters long."
            );

            categoryField.requestFocusInWindow();
            return;
        }

        // QUANTITY VALIDATION

        int quantityValue;

        try {

            quantityValue = Integer.parseInt(quantity);

            if (quantityValue <= 0) {

                showError("Quantity must be greater than 0.");
                quantityField.requestFocusInWindow();
                return;
            }

        } catch (NumberFormatException e) {

            showError(
                    "Quantity must be a positive whole number.\n"
                            + "Example: 25"
            );

            quantityField.requestFocusInWindow();
            return;
        }

        // PRICE VALIDATION

        double priceValue;

        try {

            if (!price.matches("\\d+(\\.\\d{1,2})?")) {

                showError(
                        "Enter a valid price with up to 2 decimal places.\n"
                                + "Examples: 25 or 25.50"
                );

                priceField.requestFocusInWindow();
                return;
            }

            priceValue = Double.parseDouble(price);

            if (!Double.isFinite(priceValue) || priceValue <= 0) {

                showError("Price must be greater than 0.");
                priceField.requestFocusInWindow();
                return;
            }

        } catch (NumberFormatException e) {

            showError("Enter a valid price, such as 25 or 25.50.");
            priceField.requestFocusInWindow();
            return;
        }

        // EXPIRY DATE VALIDATION

        if (!expiry.matches("(0[1-9]|1[0-2])/\\d{4}")) {

            showError(
                    "Enter the expiry date in MM/YYYY format.\n"
                            + "Example: 12/2027"
            );

            expiryField.requestFocusInWindow();
            return;
        }

        // CHECK MONTH AND YEAR

        YearMonth expiryMonth;

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM/uuuu", Locale.ROOT)
                            .withResolverStyle(ResolverStyle.STRICT);

            expiryMonth = YearMonth.parse(expiry, formatter);

        } catch (DateTimeParseException e) {

            showError("Enter a valid expiry date in MM/YYYY format.");
            expiryField.requestFocusInWindow();
            return;
        }

        YearMonth currentMonth = YearMonth.now();

        if (expiryMonth.isBefore(currentMonth)) {

            showError("Expiry date cannot be in the past.");
            expiryField.requestFocusInWindow();
            return;
        }

        // FORMAT PRICE

        String formattedPrice = String.format(
                Locale.US,
                "%.2f",
                priceValue
        );

        // SAVE OR UPDATE

        if (medicinePage == null) {

            showError("Medicine page is unavailable. Please try again.");
            return;
        }

        try {

            if (editRow != -1) {

                medicinePage.updateMedicine(
                        editRow,
                        name,
                        category,
                        String.valueOf(quantityValue),
                        formattedPrice,
                        expiry
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine update request completed.\n"
                                + "Please check the Medicine page.",
                        "Update",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                medicinePage.addMedicine(
                        name,
                        category,
                        String.valueOf(quantityValue),
                        formattedPrice,
                        expiry
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine add request completed.\n"
                                + "Please check the Medicine page.",
                        "Save",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            dispose();

        } catch (RuntimeException e) {

            showError(
                    "Could not save the medicine.\n"
                            + "Please check the database connection and try again."
            );

            e.printStackTrace();
        }
    }

    // SHOW ERROR MESSAGE

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // CLEAR FIELDS

    private void clearFields() {

        nameField.setText("");
        categoryField.setText("");
        quantityField.setText("");
        priceField.setText("");
        expiryField.setText("");

        nameField.requestFocusInWindow();
    }

    // FORM ROW

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JTextField field
    ) {

        JLabel label = new JLabel(labelText);

        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(NAVY);

        field.setPreferredSize(new Dimension(350, 38));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        new EmptyBorder(0, 10, 0, 10)
                )
        );

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;

        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;

        panel.add(field, gbc);
    }

    // BUTTON STYLE

    private void styleButton(JButton button, Color color) {

        button.setPreferredSize(new Dimension(220, 45));

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        // Slightly shift text upwards

        button.setMargin(new Insets(0, 10, 8, 10));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}