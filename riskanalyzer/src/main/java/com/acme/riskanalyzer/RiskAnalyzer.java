package com.acme.riskanalyzer;

import com.acme.riskanalyzer.init.RiskAnalyzerConfigurator;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.plugins.Plugin;

public class RiskAnalyzer extends Plugin {

    @Override
    public void init() {
        Application.getInstance().getGUILog().showMessage("RiskAnalyzer initialized.");
        var configurator = new RiskAnalyzerConfigurator();
        ActionsConfiguratorsManager.getInstance().addContainmentBrowserContextConfigurator(configurator);
    }

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
