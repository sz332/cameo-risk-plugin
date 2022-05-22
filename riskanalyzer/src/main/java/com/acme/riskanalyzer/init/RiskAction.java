package com.acme.riskanalyzer.init;

import com.acme.riskanalyzer.domain.Risk;
import com.acme.riskanalyzer.ui.RiskDialog;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RiskAction extends DefaultBrowserAction {

    private static final String ACTION_ID = "RISK_ANALYZER";
    private static final String ACTION_NAME = "Risk Analyzer";

    private final RiskDialog dialog;

    public RiskAction() {
        super(ACTION_ID, ACTION_NAME, null, null);
        this.dialog = new RiskDialog(MDDialogParentProvider.getProvider().getDialogParent(true));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var tree = getTree();
        var risks = findRisks(tree);

        dialog.setRisks(risks);
        dialog.setVisible(true);
    }

    private List<Risk> findRisks(Tree tree) {

        var risks = new ArrayList<Risk>();

        risks.add(new Risk("", "Id-l-5-c-1", 5, 1));
        risks.add(new Risk("", "Id-l-5-c-5", 5, 5));
        risks.add(new Risk("", "Id-l-1-c-1", 1, 1));
        risks.add(new Risk("", "Id-l-1-c-5", 1, 5));
        risks.add(new Risk("", "Id-l-3-c-2", 3, 2));
        risks.add(new Risk("", "Id-l-4-c-1", 4, 1));

        return risks;
    }

}
