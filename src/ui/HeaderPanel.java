package ui;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel() {

        setBackground(Color.WHITE);

        // Smaller and more balanced header
        setPreferredSize(new Dimension(0, 145));

        setLayout(new BorderLayout());


        // =========================
        // PIET LOGO
        // =========================

        JLabel logoLabel = new JLabel();

        ImageIcon icon = new ImageIcon(
                "src/images/img.png"
        );

        Image image = icon.getImage().getScaledInstance(
                220,
                75,
                Image.SCALE_SMOOTH
        );

        logoLabel.setIcon(new ImageIcon(image));

        logoLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        logoLabel.setVerticalAlignment(
                SwingConstants.BOTTOM
        );

        add(
                logoLabel,
                BorderLayout.CENTER
        );


        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "PHARMACY MANAGEMENT SYSTEMS"
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        titleLabel.setForeground(
                new Color(60, 203, 154)
        );

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );

        add(
                titleLabel,
                BorderLayout.SOUTH
        );
    }
}