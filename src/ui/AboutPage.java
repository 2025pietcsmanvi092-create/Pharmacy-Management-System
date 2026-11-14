package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutPage extends JFrame {

    private static final Color BACKGROUND =
            new Color(246, 250, 250);

    private static final Color NAVY =
            new Color(30, 60, 114);

    private static final Color GREEN =
            new Color(52, 199, 154);

    private static final Color SUBTEXT =
            new Color(70, 100, 130);

    private static final Color BORDER =
            new Color(220, 228, 228);

    private static final Color WHITE =
            Color.WHITE;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AboutPage() {

        setTitle(
                "Pharmacy Management System - About"
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );


        // =====================================================
        // HEADER
        // =====================================================

        HeaderPanel headerPanel =
                new HeaderPanel();

        add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // NAVIGATION
        // =====================================================

        NavigationPanel navigationPanel =
                new NavigationPanel("About");


        // =====================================================
        // CONTENT
        // =====================================================

        JPanel contentWrapper =
                new JPanel(
                        new BorderLayout()
                );

        contentWrapper.setBackground(
                BACKGROUND
        );


        JPanel contentPanel =
                new JPanel();

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        contentPanel.setBackground(
                BACKGROUND
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        30,
                        55,
                        35,
                        55
                )
        );


        // =====================================================
        // ABOUT CARD
        // =====================================================

        JPanel aboutCard =
                new JPanel();

        aboutCard.setLayout(
                new BoxLayout(
                        aboutCard,
                        BoxLayout.Y_AXIS
                )
        );

        aboutCard.setBackground(
                WHITE
        );

        aboutCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                45,
                                55,
                                45,
                                55
                        )
                )
        );


        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "Pharmacy Management System"
                );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                NAVY
        );


        aboutCard.add(title);


        // =====================================================
        // GREEN LINE
        // =====================================================

        JPanel line =
                new JPanel();

        line.setBackground(
                GREEN
        );

        line.setMaximumSize(
                new Dimension(
                        135,
                        4
                )
        );

        line.setPreferredSize(
                new Dimension(
                        135,
                        4
                )
        );


        aboutCard.add(
                Box.createVerticalStrut(14)
        );

        aboutCard.add(line);


        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel description =
                new JLabel(
                        "<html>"
                                + "<div style='width:800px; text-align:center;'>"
                                + "This Pharmacy Management System is a basic "
                                + "Java Swing application developed as a college "
                                + "project. It provides a simple and user-friendly "
                                + "interface for managing medicine records, including "
                                + "adding, viewing, updating and deleting medicines."
                                + "</div>"
                                + "</html>"
                );

        description.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        description.setForeground(
                SUBTEXT
        );

        description.setBorder(
                new EmptyBorder(
                        30,
                        0,
                        10,
                        0
                )
        );


        aboutCard.add(description);


        // =====================================================
        // MAIN FEATURES TITLE
        // =====================================================

        JLabel featuresTitle =
                new JLabel(
                        "Main Features"
                );

        featuresTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        featuresTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        featuresTitle.setForeground(
                NAVY
        );


        aboutCard.add(
                Box.createVerticalStrut(15)
        );

        aboutCard.add(featuresTitle);


        // =====================================================
        // FEATURES
        // =====================================================

        JPanel featuresPanel =
                new JPanel();

        featuresPanel.setLayout(
                new BoxLayout(
                        featuresPanel,
                        BoxLayout.Y_AXIS
                )
        );

        featuresPanel.setBackground(
                WHITE
        );

        featuresPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        featuresPanel.setMaximumSize(
                new Dimension(
                        600,
                        220
                )
        );

        featuresPanel.setBorder(
                new EmptyBorder(
                        15,
                        150,
                        10,
                        150
                )
        );


        addFeature(
                featuresPanel,
                "Medicine Management"
        );



        addFeature(
                featuresPanel,
                "Add, Edit, Delete and View Records"
        );

        addFeature(
                featuresPanel,
                "Simple and User-Friendly Interface"
        );


        aboutCard.add(featuresPanel);


        // =====================================================
        // TECHNOLOGY
        // =====================================================

        JLabel technologyTitle =
                new JLabel(
                        "Developed Using"
                );

        technologyTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        technologyTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        technologyTitle.setForeground(
                NAVY
        );


        aboutCard.add(
                Box.createVerticalStrut(20)
        );

        aboutCard.add(technologyTitle);


        JLabel technology =
                new JLabel(
                        "Java • Java Swing • JDBC • MySQL • IntelliJ IDEA"
                );

        technology.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        technology.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        technology.setForeground(
                SUBTEXT
        );


        aboutCard.add(
                Box.createVerticalStrut(10)
        );

        aboutCard.add(technology);


        // =====================================================
        // PROJECT PURPOSE
        // =====================================================

        JLabel purposeTitle =
                new JLabel(
                        "Project Purpose"
                );

        purposeTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        purposeTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        purposeTitle.setForeground(
                NAVY
        );


        aboutCard.add(
                Box.createVerticalStrut(25)
        );

        aboutCard.add(purposeTitle);


        JLabel purpose =
                new JLabel(
                        "<html>"
                                + "<div style='width:800px; text-align:center;'>"
                                + "The project demonstrates Java Swing GUI design, "
                                + "page navigation, table management, CRUD operations "
                                + "and basic form validation."
                                + "</div>"
                                + "</html>"
                );

        purpose.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        purpose.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        purpose.setForeground(
                SUBTEXT
        );


        aboutCard.add(
                Box.createVerticalStrut(10)
        );

        aboutCard.add(purpose);


        // =====================================================
        // DEVELOPER
        // =====================================================

        JLabel developer =
                new JLabel(
                        "Developed by Manvi | 2026"
                );

        developer.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        developer.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        developer.setForeground(
                NAVY
        );


        aboutCard.add(
                Box.createVerticalStrut(30)
        );

        aboutCard.add(developer);


        // =====================================================
        // ADD CARD
        // =====================================================

        contentPanel.add(aboutCard);


        contentWrapper.add(
                contentPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        contentWrapper
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
                scrollPane,
                BorderLayout.CENTER
        );


        add(
                centerArea,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        FooterPanel footerPanel =
                new FooterPanel();

        add(
                footerPanel,
                BorderLayout.SOUTH
        );


        setVisible(true);
    }


    // =========================================================
    // FEATURE ROW
    // =========================================================

    private void addFeature(
            JPanel panel,
            String text
    ) {

        JLabel label =
                new JLabel(
                        "•  " + text
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        label.setForeground(
                SUBTEXT
        );

        label.setBorder(
                new EmptyBorder(
                        7,
                        0,
                        7,
                        0
                )
        );


        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(label);
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                AboutPage::new
        );
    }
}