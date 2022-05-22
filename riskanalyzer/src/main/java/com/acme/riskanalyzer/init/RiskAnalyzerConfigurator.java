package com.acme.riskanalyzer.init;

import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.ActionsID;
import com.nomagic.magicdraw.actions.BrowserContextAMConfigurator;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.utils.PriorityProvider;

public class RiskAnalyzerConfigurator implements BrowserContextAMConfigurator {

    @Override
    public void configure(ActionsManager actionsManager, Tree tree) {
        ActionsCategory category = (ActionsCategory) actionsManager.getActionFor(ActionsID.TOOLS_CATEGORY);

        if (category != null) {
            category.addAction(new RiskAction());
        }
    }

    @Override
    public int getPriority() {
        return PriorityProvider.LOW_PRIORITY;
    }
}
