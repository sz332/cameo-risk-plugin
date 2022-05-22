package com.acme.riskanalyzer.ui;

import com.acme.riskanalyzer.domain.Risk;

import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author Zsolt
 */
public class RiskDialog extends JDialog {
    
    public RiskDialog(Frame parent, List<Risk> risks){
       super(parent, "Risk dialog", false);

        this.setContentPane(new RiskPanel(risks));
        this.pack();
    }

}
