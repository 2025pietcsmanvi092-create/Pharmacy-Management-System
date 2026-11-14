package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import dao.UserDAO;
public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private final Color MINT =
            new Color(60, 203, 154);

    private final Color BACKGROUND =
            new Color(246, 251, 250);

    private final Color DARK_TEXT =
            new Color(35, 50, 70);


    public LoginPage() {

        setTitle(
                "Pharmacy Management System"
        );

        // ==========================================
        // WINDOW SETTINGS
        // ==========================================

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );


        // ==========================================
        // HEADER
        // ==========================================

        add(
                new HeaderPanel(),
                BorderLayout.NORTH
        );


        // ==========================================
        // MAIN AREA
        // ==========================================

        JPanel mainPanel =
                new JPanel(
                        new GridBagLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );


        // ==========================================
        // LOGIN CARD
        // ==========================================

        RoundedPanel loginCard =
                new RoundedPanel(18);

        loginCard.setBackground(
                Color.WHITE
        );

        loginCard.setPreferredSize(
                new Dimension(
                        450,
                        390
                )
        );

        loginCard.setLayout(
                new GridBagLayout()
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        5,
                        35,
                        5,
                        35
                );


        // ==========================================
        // LOGIN HEADING
        // ==========================================

        gbc.gridy = 0;

        JLabel loginText =
                new JLabel(
                        "Login to continue",
                        SwingConstants.CENTER
                );

        loginText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        loginText.setForeground(
                DARK_TEXT
        );

        gbc.insets =
                new Insets(
                        10,
                        35,
                        18,
                        35
                );

        loginCard.add(
                loginText,
                gbc
        );


        // ==========================================
        // USERNAME LABEL
        // ==========================================

        gbc.gridy++;

        JLabel usernameLabel =
                new JLabel(
                        "Username"
                );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        usernameLabel.setForeground(
                DARK_TEXT
        );

        gbc.insets =
                new Insets(
                        3,
                        35,
                        4,
                        35
                );

        loginCard.add(
                usernameLabel,
                gbc
        );


        // ==========================================
        // USERNAME FIELD
        // ==========================================

        gbc.gridy++;

        usernameField =
                new JTextField();

        usernameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        usernameField.setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );

        usernameField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        228,
                                        232
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        loginCard.add(
                usernameField,
                gbc
        );


        // ==========================================
        // PASSWORD LABEL
        // ==========================================

        gbc.gridy++;

        JLabel passwordLabel =
                new JLabel(
                        "Password"
                );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        passwordLabel.setForeground(
                DARK_TEXT
        );

        gbc.insets =
                new Insets(
                        8,
                        35,
                        4,
                        35
                );

        loginCard.add(
                passwordLabel,
                gbc
        );


        // ==========================================
        // PASSWORD PANEL
        // ==========================================

        gbc.gridy++;

        JPanel passwordPanel =
                new JPanel(
                        new BorderLayout()
                );

        passwordPanel.setBackground(
                Color.WHITE
        );


        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        passwordField.setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        228,
                                        232
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                5
                        )
                )
        );


        // ==========================================
        // SHOW / HIDE BUTTON
        // ==========================================

        JButton showButton =
                new JButton(
                        "Show"
                );

        showButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        showButton.setForeground(
                MINT
        );

        showButton.setBackground(
                Color.WHITE
        );

        showButton.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        8,
                        0,
                        8
                )
        );

        showButton.setFocusPainted(
                false
        );

        showButton.setContentAreaFilled(
                false
        );


        showButton.addActionListener(e -> {

            if (
                    passwordField.getEchoChar()
                            == (char) 0
            ) {

                passwordField.setEchoChar(
                        '•'
                );

                showButton.setText(
                        "Show"
                );

            } else {

                passwordField.setEchoChar(
                        (char) 0
                );

                showButton.setText(
                        "Hide"
                );
            }
        });


        passwordPanel.add(
                passwordField,
                BorderLayout.CENTER
        );

        passwordPanel.add(
                showButton,
                BorderLayout.EAST
        );


        loginCard.add(
                passwordPanel,
                gbc
        );


        // ==========================================
        // LOGIN BUTTON
        // ==========================================

        gbc.gridy++;

        JButton loginButton =
                new JButton(
                        "Login"
                );

        loginButton.setPreferredSize(
                new Dimension(
                        0,
                        42
                )
        );

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setBackground(
                MINT
        );

        loginButton.setFocusPainted(
                false
        );

        loginButton.setBorderPainted(
                false
        );

        gbc.insets =
                new Insets(
                        15,
                        35,
                        8,
                        35
                );

        loginCard.add(
                loginButton,
                gbc
        );


        // ==========================================
        // FORGOT PASSWORD
        // ==========================================

        gbc.gridy++;

        JButton forgotButton =
                new JButton(
                        "Forgot Password?"
                );

        forgotButton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        forgotButton.setForeground(
                MINT
        );

        forgotButton.setBorderPainted(
                false
        );

        forgotButton.setContentAreaFilled(
                false
        );

        forgotButton.setFocusPainted(
                false
        );

        loginCard.add(
                forgotButton,
                gbc
        );


        // ==========================================
        // ADD LOGIN CARD
        // ==========================================

        GridBagConstraints cardGbc =
                new GridBagConstraints();

        cardGbc.gridx = 0;
        cardGbc.gridy = 0;

        cardGbc.weightx = 1.0;
        cardGbc.weighty = 1.0;

        cardGbc.anchor =
                GridBagConstraints.CENTER;

        mainPanel.add(
                loginCard,
                cardGbc
        );


        add(
                mainPanel,
                BorderLayout.CENTER
        );


        // ==========================================
        // FOOTER
        // ==========================================

        add(
                new FooterPanel(),
                BorderLayout.SOUTH
        );


        // ==========================================
        // LOGIN ACTION
        // ==========================================

        loginButton.addActionListener(e -> {

            String username = usernameField.getText().trim();

            String password = new String(
                    passwordField.getPassword()
            );

            UserDAO userDAO = new UserDAO();

            if (userDAO.validateLogin(username, password)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful"
                );

                dispose();

                new Dashboard();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password"
                );
            }
        });


        // ==========================================
        // FORGOT PASSWORD
        // ==========================================

        forgotButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Please check your registered email to reset your password."
            );
        });


        // ==========================================
        // WINDOW POSITION
        // ==========================================

        setLocationRelativeTo(null);

        setVisible(true);
    }


    // =================================================
    // ROUNDED LOGIN CARD
    // =================================================

    static class RoundedPanel
            extends JPanel {

        private final int radius;


        public RoundedPanel(
                int radius
        ) {

            this.radius = radius;

            setOpaque(false);
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            // Shadow

            g2.setColor(
                    new Color(
                            210,
                            225,
                            220,
                            100
                    )
            );

            g2.fillRoundRect(
                    3,
                    5,
                    getWidth() - 6,
                    getHeight() - 5,
                    radius,
                    radius
            );


            // Card

            g2.setColor(
                    Color.WHITE
            );

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth() - 6,
                    getHeight() - 8,
                    radius,
                    radius
            );


            // Border

            g2.setColor(
                    new Color(
                            225,
                            232,
                            230
                    )
            );

            g2.drawRoundRect(
                    0,
                    0,
                    getWidth() - 7,
                    getHeight() - 9,
                    radius,
                    radius
            );


            g2.dispose();

            super.paintComponent(g);
        }
    }


    // =================================================
    // MAIN
    // =================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                LoginPage::new
        );
    }
}