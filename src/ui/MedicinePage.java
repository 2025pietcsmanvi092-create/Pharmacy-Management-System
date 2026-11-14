package ui;

import dao.MedicineDAO;
import model.Medicine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MedicinePage extends JFrame {

    // COLORS

    private static final Color BACKGROUND =
            new Color(246, 250, 250);

    private static final Color NAVY =
            new Color(30, 60, 114);

    private static final Color GREEN =
            new Color(52, 199, 154);

    private static final Color SUBTEXT =
            new Color(80, 105, 125);

    private static final Color BORDER =
            new Color(220, 228, 228);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color EDIT_BLUE =
            new Color(61, 135, 225);

    private static final Color DELETE_RED =
            new Color(220, 80, 80);

    private static final Color VIEW_PURPLE =
            new Color(116, 82, 205);


    // DATABASE AND TABLE

    private final MedicineDAO medicineDAO =
            new MedicineDAO();

    private JTable medicineTable;

    private DefaultTableModel tableModel;

    private TableRowSorter<DefaultTableModel> sorter;

    private JTextField searchField;


    // CONSTRUCTOR

    public MedicinePage() {

        setTitle("Pharmacy Management System - Medicines");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());


        // HEADER AND NAVIGATION

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.setBackground(WHITE);

        topPanel.add(new HeaderPanel(), BorderLayout.NORTH);

        topPanel.add(
                new NavigationPanel("Medicines"),
                BorderLayout.SOUTH
        );

        add(topPanel, BorderLayout.NORTH);


        // MAIN CONTENT

        JPanel mainPanel = new JPanel(
                new BorderLayout(0, 10)
        );

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(6, 20, 6, 20)
        );


        mainPanel.add(
                createTitlePanel(),
                BorderLayout.NORTH
        );

        mainPanel.add(
                createTablePanel(),
                BorderLayout.CENTER
        );


        JPanel actionPanel = createActionPanel();

        actionPanel.setPreferredSize(
                new Dimension(0, 130)
        );

        mainPanel.add(
                actionPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel, BorderLayout.CENTER);

        add(new FooterPanel(), BorderLayout.SOUTH);


        // LOAD DATABASE RECORDS

        loadMedicines();


        setVisible(true);
    }


    // TITLE AND SEARCH PANEL

    private JPanel createTitlePanel() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(BACKGROUND);


        // LEFT SIDE: TITLE

        JPanel textPanel = new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(textPanel, BoxLayout.Y_AXIS)
        );


        JLabel title = new JLabel("Medicine Management");

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        title.setForeground(NAVY);


        JLabel subtitle = new JLabel(
                "Manage medicines available in your pharmacy"
        );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        subtitle.setForeground(SUBTEXT);


        textPanel.add(title);

        textPanel.add(Box.createVerticalStrut(5));

        textPanel.add(subtitle);

        panel.add(textPanel, BorderLayout.WEST);


        // RIGHT SIDE: SEARCH

        JPanel searchPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 8, 15)
        );

        searchPanel.setOpaque(false);


        searchField = new JTextField(20);

        searchField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        searchField.setPreferredSize(
                new Dimension(270, 38)
        );

        searchField.setToolTipText(
                "Search by Medicine ID or Medicine Name"
        );


        JButton searchButton = new JButton("Search");

        searchButton.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        searchButton.setBackground(GREEN);

        searchButton.setForeground(WHITE);

        searchButton.setFocusPainted(false);

        searchButton.setBorder(
                new EmptyBorder(8, 15, 8, 15)
        );


        JButton clearButton = new JButton("Clear");

        clearButton.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        clearButton.setBackground(NAVY);

        clearButton.setForeground(WHITE);

        clearButton.setFocusPainted(false);

        clearButton.setBorder(
                new EmptyBorder(8, 15, 8, 15)
        );


        searchPanel.add(searchField);

        searchPanel.add(searchButton);

        searchPanel.add(clearButton);

        panel.add(searchPanel, BorderLayout.EAST);


        // SEARCH ACTIONS

        searchButton.addActionListener(
                e -> searchMedicine()
        );

        searchField.addActionListener(
                e -> searchMedicine()
        );

        clearButton.addActionListener(
                e -> clearSearch()
        );


        return panel;
    }


    // SEARCH MEDICINE

    private void searchMedicine() {

        String searchText = searchField.getText().trim();

        if (searchText.isEmpty()) {

            clearSearch();

            return;
        }

        sorter.setRowFilter(
                RowFilter.regexFilter(
                        "(?i)" + Pattern.quote(searchText),
                        0, 1
                )
        );

        medicineTable.clearSelection();

        if (medicineTable.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No medicine found.",
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // CLEAR SEARCH

    private void clearSearch() {

        searchField.setText("");

        sorter.setRowFilter(null);

        medicineTable.clearSelection();
    }


    // TABLE PANEL

    private JPanel createTablePanel() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(WHITE);

        panel.setBorder(new LineBorder(BORDER, 1));


        String[] columns = {
                "ID",
                "Medicine Name",
                "Category",
                "Quantity",
                "Price (₹)",
                "Expiry Date"
        };


        // INITIALLY EMPTY: RECORDS WILL COME FROM MYSQL

        tableModel = new DefaultTableModel(
                new Object[0][6],
                columns
        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };


        medicineTable = new JTable(tableModel);

        sorter = new TableRowSorter<>(tableModel);

        medicineTable.setRowSorter(sorter);

        medicineTable.setRowHeight(40);

        medicineTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        medicineTable.setForeground(
                new Color(40, 50, 60)
        );

        medicineTable.setBackground(WHITE);

        medicineTable.setSelectionBackground(
                new Color(220, 245, 238)
        );

        medicineTable.setSelectionForeground(NAVY);

        medicineTable.setGridColor(
                new Color(225, 230, 230)
        );

        medicineTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        medicineTable.setShowVerticalLines(true);

        medicineTable.setShowHorizontalLines(true);


        // LET THE TABLE FILL THE AVAILABLE WIDTH

        medicineTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );


        // TABLE HEADER

        medicineTable.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        medicineTable.getTableHeader().setBackground(NAVY);

        medicineTable.getTableHeader().setForeground(WHITE);

        medicineTable.getTableHeader().setPreferredSize(
                new Dimension(0, 45)
        );


        // COLUMN WIDTHS

        medicineTable.getColumnModel()
                .getColumn(0).setPreferredWidth(60);

        medicineTable.getColumnModel()
                .getColumn(1).setPreferredWidth(250);

        medicineTable.getColumnModel()
                .getColumn(2).setPreferredWidth(170);

        medicineTable.getColumnModel()
                .getColumn(3).setPreferredWidth(130);

        medicineTable.getColumnModel()
                .getColumn(4).setPreferredWidth(140);

        medicineTable.getColumnModel()
                .getColumn(5).setPreferredWidth(160);


        // SCROLL PANE

        JScrollPane scrollPane = new JScrollPane(medicineTable);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );


        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }


    // LOAD MEDICINES FROM MYSQL

    private void loadMedicines() {

        tableModel.setRowCount(0);

        ArrayList<Medicine> medicines =
                medicineDAO.getAllMedicines();


        for (Medicine medicine : medicines) {

            tableModel.addRow(
                    new Object[]{

                            medicine.getId(),

                            medicine.getName(),

                            medicine.getCategory(),

                            medicine.getQuantity(),

                            medicine.getPrice(),

                            medicine.getExpiryDate()
                    }
            );
        }
    }


    // ACTION PANEL

    private JPanel createActionPanel() {

        JPanel outerPanel = new JPanel(
                new BorderLayout(0, 6)
        );

        outerPanel.setBackground(WHITE);

        outerPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER, 1),
                        new EmptyBorder(10, 20, 10, 20)
                )
        );


        JLabel actionTitle = new JLabel("Medicine Actions");

        actionTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 19)
        );

        actionTitle.setForeground(NAVY);


        JLabel actionSubtitle = new JLabel(
                "Add, edit, delete or view medicine details"
        );

        actionSubtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        actionSubtitle.setForeground(SUBTEXT);


        JPanel heading = new JPanel();

        heading.setOpaque(false);

        heading.setLayout(
                new BoxLayout(heading, BoxLayout.Y_AXIS)
        );

        heading.add(actionTitle);

        heading.add(Box.createVerticalStrut(3));

        heading.add(actionSubtitle);

        outerPanel.add(heading, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(
                new GridLayout(1, 4, 16, 0)
        );

        buttonPanel.setOpaque(false);


        JButton addButton = createActionButton(
                "Add Medicine",
                "Add a new medicine",
                GREEN
        );

        JButton editButton = createActionButton(
                "Edit",
                "Modify selected medicine",
                EDIT_BLUE
        );

        JButton deleteButton = createActionButton(
                "Delete",
                "Remove selected medicine",
                DELETE_RED
        );

        JButton viewButton = createActionButton(
                "View",
                "View medicine details",
                VIEW_PURPLE
        );


        buttonPanel.add(addButton);

        buttonPanel.add(editButton);

        buttonPanel.add(deleteButton);

        buttonPanel.add(viewButton);


        outerPanel.add(buttonPanel, BorderLayout.CENTER);


        // ADD

        addButton.addActionListener(e -> {

            new AddMedicinePage(this);

        });


        // EDIT

        editButton.addActionListener(e -> {

            int row = medicineTable.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a medicine first.",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            new AddMedicinePage(this, row);

        });


        // DELETE

        deleteButton.addActionListener(
                e -> deleteMedicine()
        );


        // VIEW

        viewButton.addActionListener(
                e -> viewMedicine()
        );


        return outerPanel;
    }


    // ACTION BUTTON

    private JButton createActionButton(
            String title,
            String description,
            Color backgroundColor
    ) {

        JButton button = new JButton();

        button.setLayout(
                new BorderLayout(12, 0)
        );

        button.setBackground(backgroundColor);

        button.setForeground(WHITE);

        button.setBorder(
                new EmptyBorder(4, 15, 4, 15)
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );


        JLabel iconLabel = new JLabel(
                getActionSymbol(title)
        );

        iconLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        iconLabel.setForeground(WHITE);

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setPreferredSize(
                new Dimension(38, 38)
        );


        JPanel textPanel = new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(textPanel, BoxLayout.Y_AXIS)
        );


        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        titleLabel.setForeground(WHITE);


        JLabel descriptionLabel = new JLabel(description);

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        descriptionLabel.setForeground(WHITE);


        textPanel.add(titleLabel);

        textPanel.add(Box.createVerticalStrut(4));

        textPanel.add(descriptionLabel);


        JLabel arrow = new JLabel(">");

        arrow.setFont(
                new Font("Segoe UI", Font.BOLD, 25)
        );

        arrow.setForeground(WHITE);


        button.add(iconLabel, BorderLayout.WEST);

        button.add(textPanel, BorderLayout.CENTER);

        button.add(arrow, BorderLayout.EAST);


        // HOVER EFFECT

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {
                        button.setBackground(
                                backgroundColor.darker()
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {
                        button.setBackground(backgroundColor);
                    }
                }
        );


        return button;
    }


    private String getActionSymbol(String title) {

        if (title.equals("Add Medicine")) {
            return "+";
        }

        if (title.equals("Edit")) {
            return "E";
        }

        if (title.equals("Delete")) {
            return "X";
        }

        return "V";
    }


    // DELETE MEDICINE FROM MYSQL

    private void deleteMedicine() {

        int row = medicineTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a medicine first.",
                    "Select Medicine",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        // CONVERT VIEW ROW TO MODEL ROW

        row = medicineTable.convertRowIndexToModel(row);


        int id = Integer.parseInt(
                tableModel.getValueAt(row, 0).toString()
        );

        String name = tableModel.getValueAt(row, 1).toString();


        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete\n" + name + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );


        if (result == JOptionPane.YES_OPTION) {

            boolean deleted = medicineDAO.deleteMedicine(id);

            if (deleted) {

                clearSearch();

                loadMedicines();

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine deleted successfully.",
                        "Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Could not delete medicine from the database.\n"
                                + "Check the console for the error.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    // VIEW MEDICINE

    private void viewMedicine() {

        int row = medicineTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a medicine first.",
                    "Select Medicine",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        // CONVERT VIEW ROW TO MODEL ROW

        row = medicineTable.convertRowIndexToModel(row);


        String details =
                "<html>"
                        + "<b>Medicine ID:</b> "
                        + tableModel.getValueAt(row, 0)
                        + "<br><br>"

                        + "<b>Medicine Name:</b> "
                        + tableModel.getValueAt(row, 1)
                        + "<br><br>"

                        + "<b>Category:</b> "
                        + tableModel.getValueAt(row, 2)
                        + "<br><br>"

                        + "<b>Quantity:</b> "
                        + tableModel.getValueAt(row, 3)
                        + "<br><br>"

                        + "<b>Price:</b> ₹"
                        + tableModel.getValueAt(row, 4)
                        + "<br><br>"

                        + "<b>Expiry Date:</b> "
                        + tableModel.getValueAt(row, 5)
                        + "</html>";


        JOptionPane.showMessageDialog(
                this,
                details,
                "Medicine Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // GET MEDICINE VALUE

    public String getMedicineValue(
            int row,
            int column
    ) {

        int modelRow =
                medicineTable.convertRowIndexToModel(row);

        return tableModel.getValueAt(
                modelRow,
                column
        ).toString();
    }


    // ADD MEDICINE TO MYSQL

    public boolean addMedicine(
            String name,
            String category,
            String quantity,
            String price,
            String expiry
    ) {

        try {

            Medicine medicine = new Medicine();

            medicine.setName(name);

            medicine.setCategory(category);

            medicine.setQuantity(
                    Integer.parseInt(quantity)
            );

            medicine.setPrice(
                    Double.parseDouble(price)
            );

            medicine.setExpiryDate(expiry);


            boolean added = medicineDAO.addMedicine(medicine);

            if (added) {

                clearSearch();

                loadMedicines();

                // SELECT THE NEWLY ADDED MEDICINE

                medicineTable.setRowSelectionInterval(
                        medicineTable.getRowCount() - 1,
                        medicineTable.getRowCount() - 1
                );

            }

            return added;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be a whole number and price must be numeric.",
                    "Invalid Data",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }
    }


    // UPDATE MEDICINE IN MYSQL

    public boolean updateMedicine(
            int row,
            String name,
            String category,
            String quantity,
            String price,
            String expiry
    ) {

        try {

            // CONVERT VIEW ROW TO MODEL ROW

            row = medicineTable.convertRowIndexToModel(row);


            int id = Integer.parseInt(
                    tableModel.getValueAt(row, 0).toString()
            );


            Medicine medicine = new Medicine();

            medicine.setId(id);

            medicine.setName(name);

            medicine.setCategory(category);

            medicine.setQuantity(
                    Integer.parseInt(quantity)
            );

            medicine.setPrice(
                    Double.parseDouble(price)
            );

            medicine.setExpiryDate(expiry);


            boolean updated = medicineDAO.updateMedicine(medicine);

            if (updated) {

                clearSearch();

                loadMedicines();

                // RESELECT THE UPDATED MEDICINE BY ID

                for (int i = 0; i < tableModel.getRowCount(); i++) {

                    if (Integer.parseInt(
                            tableModel.getValueAt(i, 0).toString()
                    ) == id) {

                        int viewRow =
                                medicineTable.convertRowIndexToView(i);

                        medicineTable.setRowSelectionInterval(
                                viewRow,
                                viewRow
                        );

                        break;
                    }
                }
            }

            return updated;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be a whole number and price must be numeric.",
                    "Invalid Data",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }
    }


    // MAIN

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                MedicinePage::new
        );
    }
}