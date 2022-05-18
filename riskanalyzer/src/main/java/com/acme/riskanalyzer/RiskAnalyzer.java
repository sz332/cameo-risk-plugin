package com.acme.riskanalyzer;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.plugins.Plugin;

public class RiskAnalyzer extends Plugin {

	@Override
	public void init() {
		Application.getInstance().getGUILog().showMessage("RiskAnalyzer initialized.");
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
