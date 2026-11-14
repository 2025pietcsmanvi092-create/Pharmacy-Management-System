package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NavigationPanel extends JPanel {

    private static final Color NAVY =
            new Color(30, 60, 114);

    private static final Color GREEN =
            new Color(52, 199, 154);

    private static final Color WHITE =
            Color.WHITE;

    private final String currentPage;

    public NavigationPanel(String currentPage) {

        this.currentPage = currentPage;

        setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        8,
                        8
                )
        );

        setBackground(NAVY);

        setBorder(
                new EmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        createNavigationButtons();
    }


    // =========================================================
    // CREATE NAVIGATION BUTTONS
    // =========================================================

    private void createNavigationButtons() {

        JButton dashboardButton =
                createButton("Dashboard");

        JButton medicinesButton =
                createButton("Medicines");



        JButton aboutButton =
                createButton("About");

        JButton logoutButton =
                createButton("Logout");


        // =====================================================
        // CURRENT PAGE
        // =====================================================

        if (currentPage.equalsIgnoreCase("Dashboard")) {

            highlightButton(dashboardButton);

        } else if (currentPage.equalsIgnoreCase("Medicines")) {

            highlightButton(medicinesButton);



        } else if (currentPage.equalsIgnoreCase("About")) {

            highlightButton(aboutButton);
        }


        // =====================================================
        // DASHBOARD
        // =====================================================

        dashboardButton.addActionListener(e -> {

            new Dashboard();

            closeCurrentWindow();
        });


        // =====================================================
        // MEDICINES
        // =====================================================

        medicinesButton.addActionListener(e -> {

            new MedicinePage();

            closeCurrentWindow();
        });


        // =====================================================



        // =====================================================
        // ABOUT
        // =====================================================

        aboutButton.addActionListener(e -> {

            new AboutPage();

            closeCurrentWindow();
        });


        // =====================================================
        // LOGOUT
        // =====================================================

        logoutButton.addActionListener(e -> {

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (result == JOptionPane.YES_OPTION) {

                new LoginPage();

                closeCurrentWindow();
            }
        });


        // =====================================================
        // ADD BUTTONS
        // =====================================================

        add(dashboardButton);
        add(medicinesButton);
        add(aboutButton);
        add(logoutButton);
    }


    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(WHITE);

        button.setBackground(NAVY);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(true);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        12,
                        20,
                        12,
                        20
                )
        );

        return button;
    }


    // =========================================================
    // HIGHLIGHT CURRENT PAGE
    // =========================================================

    private void highlightButton(JButton button) {

        button.setBackground(GREEN);

        button.setForeground(WHITE);
    }


    // =========================================================
    // CLOSE CURRENT WINDOW
    // =========================================================

    private void closeCurrentWindow() {

        Window window =
                SwingUtilities.getWindowAncestor(this);

        if (window != null) {

            window.dispose();
        }
    }
}