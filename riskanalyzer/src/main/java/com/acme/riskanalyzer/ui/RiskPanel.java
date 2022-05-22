/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acme.riskanalyzer.ui;

import com.acme.riskanalyzer.domain.Risk;

import java.awt.BorderLayout;
import java.util.List;

/**
 *
 * @author Zsolt
 */
public class RiskPanel extends AbstractRiskPanel {
    
    public RiskPanel(List<Risk> risks){
        super();
        initComponents(risks);
    }
    
    private void initComponents(List<Risk> risks){
        var canvas = new RiskCanvas();
        canvas.setRisks(risks);
        middlePanel.add(canvas, BorderLayout.CENTER);
    }
    
}
