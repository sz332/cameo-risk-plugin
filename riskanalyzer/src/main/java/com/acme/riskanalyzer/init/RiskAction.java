package com.acme.riskanalyzer.init;

import com.nomagic.magicdraw.ui.browser.Node;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.uml.BaseElement;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RiskAction extends DefaultBrowserAction {

    private static final String ACTION_ID = "RISK_ANALYZER";
    private static final String ACTION_NAME = "Risk Analyzer";

    public RiskAction() {
        super(ACTION_ID,ACTION_NAME, null, null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Tree tree = getTree();

        StringBuilder sb = new StringBuilder();

        sb.append("Selected elements: \n");

        for (int i = 0; i < tree.getSelectedNodes().length; i++)
        {
            Node node = tree.getSelectedNodes()[i];
            Object userObject = node.getUserObject();
            if (userObject instanceof BaseElement)
            {
                BaseElement element = (BaseElement) userObject;
                sb.append("\n"+element.getHumanName());
            }
        }

        JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(true), sb.toString());
    }

}
