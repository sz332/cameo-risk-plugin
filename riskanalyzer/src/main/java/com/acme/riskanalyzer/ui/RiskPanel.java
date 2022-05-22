/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acme.riskanalyzer.ui;

import com.acme.riskanalyzer.domain.Risk;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.List;

/**
 *
 * @author Zsolt
 */
public class RiskPanel extends AbstractRiskPanel {

    private final RiskCanvas canvas;

    public RiskPanel(){
        super();
        this.canvas = new RiskCanvas();
        middlePanel.add(canvas, BorderLayout.CENTER);
    }

    public void setRisks(List<Risk> risks) {
        canvas.setRisks(risks);
    }

    public JButton getCloseButton(){
        return this.closeButton;
    }
}
