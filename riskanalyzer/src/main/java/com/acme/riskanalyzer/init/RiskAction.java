package com.acme.riskanalyzer.init;

import com.acme.riskanalyzer.domain.Risk;
import com.acme.riskanalyzer.ui.RiskDialog;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;

import com.nomagic.magicdraw.core.*;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.jmi.helpers.TagsHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

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

        var project = Application.getInstance().getProject();
        var profile = StereotypesHelper.getProfile(project, "Safety");
        var stereotype = StereotypesHelper.getStereotype(project, "Risk", profile);

        var risks = new ArrayList<Risk>();

        if (project != null && profile != null && stereotype != null) {
            var myPackage = project.getElementByID("_2021x_2_62c021d_1653239356108_45350_2833");

            if (myPackage instanceof Package) {
                Package pkg = (Package) myPackage;

                System.err.println("Found package");

                for (var element : pkg.getOwnedElement()) {
                    if (StereotypesHelper.hasStereotype(element, stereotype)) {
                        var name = TagsHelper.getStereotypePropertyFirst(element, stereotype, "riskName").toString();
                        var id = TagsHelper.getStereotypePropertyFirst(element, stereotype, "riskID").toString();
                        var likelihood = asNumber(TagsHelper.getStereotypePropertyFirst(element, stereotype, "likelihood"));
                        var maxConsequence = asNumber(element.refGetValue("maxConsequence"));

                        var risk = new Risk(element.getID(), id, name, likelihood, maxConsequence);
                        risks.add(risk);
                    }
                }
            }

        } else {
            System.err.println("No project, profile, or stereotype found");
            System.err.println("project = " + project);
            System.err.println("profile = " + profile);
            System.err.println("stereotype = " + stereotype);
        }

        return risks;
    }

    private int asNumber(Object o) {
        if (o != null) {
            try {
                return Integer.parseInt(o.toString());
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        return -1;
    }

}
