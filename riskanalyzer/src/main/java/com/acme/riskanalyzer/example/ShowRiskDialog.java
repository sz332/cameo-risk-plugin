/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acme.riskanalyzer.example;

import com.acme.riskanalyzer.domain.Risk;
import com.acme.riskanalyzer.ui.RiskDialog;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Zsolt
 */
public class ShowRiskDialog {

    /**
     * @param args
     */
    @SuppressWarnings("UseSpecificCatch")
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // do nothing
            }

            var risks = new ArrayList<Risk>();
            risks.add(new Risk("", "Id-l-5-c-1", "Risk1", 5, 1));
            risks.add(new Risk("", "Id-l-5-c-5", "Risk2", 5, 5));
            risks.add(new Risk("", "Id-l-1-c-1", "Risk3", 1, 1));
            risks.add(new Risk("", "Id-l-1-c-5", "Risk4", 1, 5));
            risks.add(new Risk("", "Id-l-3-c-2", "Risk5", 3, 2));
            risks.add(new Risk("", "Id-l-4-c-1", "Risk6", 4, 1));

            RiskDialog dialog = new RiskDialog(null);
            dialog.setRisks(risks);
            dialog.setVisible(true);
        });

    }

}