package com.acme.riskanalyzer.init;

import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.uml.BaseElement;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class RiskAction extends DefaultBrowserAction {

    private static final String ACTION_ID = "RISK_ANALYZER";
    private static final String ACTION_NAME = "Risk Analyzer";

    public RiskAction() {
        super(ACTION_ID, ACTION_NAME, null, null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Tree tree = getTree();

        StringBuilder sb = new StringBuilder();

        sb.append("Selected elements: \n");

        if (tree != null) {
            for (var node : tree.getSelectedNodes()) {
                Object userObject = node.getUserObject();
                if (userObject instanceof BaseElement) {
                    BaseElement element = (BaseElement) userObject;
                    sb.append("\n");
                    sb.append(element.getHumanName());
                }
            }
        }

        JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(true), sb.toString());
    }

}
