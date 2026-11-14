package ui;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {

    public FooterPanel() {

        setPreferredSize(
                new Dimension(0, 45)
        );

        setBackground(
                new Color(239, 250, 247)
        );

        setLayout(
                new GridBagLayout()
        );


        JLabel footerLabel = new JLabel(
                "Developed by Manvi  |  2026"
        );

        footerLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        footerLabel.setForeground(
                new Color(45, 65, 80)
        );

        add(footerLabel);
    }
}