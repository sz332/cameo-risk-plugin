package com.acme.riskanalyzer.ui;

import com.acme.riskanalyzer.domain.Risk;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Zsolt
 */
public class RiskDialog extends JDialog {

    private final RiskPanel panel;

    public RiskDialog(Frame parent) {
        super(parent, "Risk dialog", true);

        this.panel = new RiskPanel();

        // This is a very ugly hack. Don't do that in production, please.
        this.panel.getCloseButton().addActionListener(e -> this.setVisible(false));

        this.setContentPane(panel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void setRisks(List<Risk> risks) {
        this.panel.setRisks(risks);
    }

}
