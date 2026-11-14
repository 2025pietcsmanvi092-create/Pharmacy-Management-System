package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private static final Color BACKGROUND =
            new Color(246, 250, 250);

    private static final Color NAVY =
            new Color(30, 60, 114);

    private static final Color GREEN =
            new Color(52, 199, 154);

    private static final Color PURPLE =
            new Color(116, 78, 190);

    private static final Color SUBTEXT =
            new Color(70, 100, 130);

    private static final Color BORDER =
            new Color(220, 228, 228);

    private static final Color WHITE =
            Color.WHITE;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Dashboard() {

        setTitle(
                "Pharmacy Management System - Dashboard"
        );

        /*
         * NORMAL WINDOW SIZE
         *
         * This is used when the user restores the window.
         */
        setSize(
                1200,
                750
        );

        /*
         * Minimum size of the window.
         */
        setMinimumSize(
                new Dimension(
                        900,
                        650
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );


        // =====================================================
        // HEADER
        // =====================================================

        add(
                new HeaderPanel(),
                BorderLayout.NORTH
        );


        // =====================================================
        // NAVIGATION
        // =====================================================

        NavigationPanel navigationPanel =
                new NavigationPanel("Dashboard");


        // =====================================================
        // MAIN CONTENT AREA
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );


        // =====================================================
        // CONTENT PANEL
        // =====================================================

        JPanel contentPanel =
                new JPanel(
                        new GridBagLayout()
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        30,
                        50,
                        30,
                        50
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // =====================================================
        // DASHBOARD TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "Dashboard",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        38
                )
        );

        title.setForeground(
                NAVY
        );


        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        10,
                        0,
                        5,
                        0
                );

        contentPanel.add(
                title,
                gbc
        );


        // =====================================================
        // GREEN LINE
        // =====================================================

        JPanel greenLine =
                new JPanel();

        greenLine.setBackground(
                GREEN
        );

        greenLine.setPreferredSize(
                new Dimension(
                        145,
                        5
                )
        );


        gbc.gridy = 1;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        10,
                        0
                );

        contentPanel.add(
                greenLine,
                gbc
        );


        // =====================================================
        // SUBTITLE
        // =====================================================

        JLabel subtitle =
                new JLabel(
                        "Welcome to your Pharmacy Management System",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        17
                )
        );

        subtitle.setForeground(
                SUBTEXT
        );


        gbc.gridy = 2;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        25,
                        0
                );

        contentPanel.add(
                subtitle,
                gbc
        );


        // =====================================================
        // QUICK ACCESS TITLE
        // =====================================================

        JLabel quickAccess =
                new JLabel(
                        "Quick Access"
                );

        quickAccess.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        quickAccess.setForeground(
                NAVY
        );


        gbc.gridy = 3;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        12,
                        0
                );

        contentPanel.add(
                quickAccess,
                gbc
        );


        // =====================================================
        // MEDICINE CARD
        // =====================================================

        JPanel medicineCard =
                createCard(
                        "Medicine Management",
                        "Add, edit, delete and view medicines",
                        GREEN
                );


        medicineCard.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        new MedicinePage();

                        dispose();
                    }
                }
        );


        gbc.gridy = 4;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        15,
                        0
                );

        contentPanel.add(
                medicineCard,
                gbc
        );


        // =====================================================
        // ABOUT CARD
        // =====================================================

        JPanel aboutCard =
                createCard(
                        "About the System",
                        "View information about this project",
                        PURPLE
                );


        aboutCard.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        new AboutPage();

                        dispose();
                    }
                }
        );


        gbc.gridy = 5;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        10,
                        0
                );

        contentPanel.add(
                aboutCard,
                gbc
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        contentPanel
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // CENTER AREA
        // =====================================================

        JPanel centerArea =
                new JPanel(
                        new BorderLayout()
                );

        centerArea.setBackground(
                BACKGROUND
        );


        centerArea.add(
                navigationPanel,
                BorderLayout.NORTH
        );

        centerArea.add(
                mainPanel,
                BorderLayout.CENTER
        );


        add(
                centerArea,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        add(
                new FooterPanel(),
                BorderLayout.SOUTH
        );


        // =====================================================
        // OPEN WINDOW
        // =====================================================

        setLocationRelativeTo(null);

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setVisible(true);
    }


    // =========================================================
    // CREATE CARD
    // =========================================================

    private JPanel createCard(
            String heading,
            String description,
            Color accentColor
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );


        // =====================================================
        // LEFT ACCENT LINE
        // =====================================================

        JPanel accent =
                new JPanel();

        accent.setBackground(
                accentColor
        );

        accent.setPreferredSize(
                new Dimension(
                        6,
                        60
                )
        );


        card.add(
                accent,
                BorderLayout.WEST
        );


        // =====================================================
        // TEXT PANEL
        // =====================================================

        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                5
                        )
                );

        textPanel.setBackground(
                WHITE
        );

        textPanel.setBorder(
                new EmptyBorder(
                        0,
                        25,
                        0,
                        20
                )
        );


        // =====================================================
        // HEADING
        // =====================================================

        JLabel headingLabel =
                new JLabel(
                        heading
                );

        headingLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        headingLabel.setForeground(
                NAVY
        );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel descriptionLabel =
                new JLabel(
                        description
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        descriptionLabel.setForeground(
                SUBTEXT
        );


        textPanel.add(
                headingLabel
        );

        textPanel.add(
                descriptionLabel
        );


        card.add(
                textPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // ARROW
        // =====================================================

        JLabel arrow =
                new JLabel(
                        "›"
                );

        arrow.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        arrow.setForeground(
                accentColor
        );


        card.add(
                arrow,
                BorderLayout.EAST
        );


        return card;
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                Dashboard::new
        );
    }
}