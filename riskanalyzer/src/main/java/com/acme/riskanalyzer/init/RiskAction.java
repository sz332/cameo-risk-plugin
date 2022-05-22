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

        System.err.println("project = " + project);
        System.err.println("profile = " + profile);
        System.err.println("stereotype = " + stereotype);

        if (project != null && profile != null && stereotype != null){
            var myPackage = project.getElementByID("_2021x_2_62c021d_1653239356108_45350_2833");

            if (myPackage instanceof Package){
                Package pkg = (Package) myPackage;

                System.err.println("Found package");

                for (var element : pkg.getOwnedElement()){
                    if (StereotypesHelper.hasStereotype(element, stereotype)){
                        var name = TagsHelper.getStereotypePropertyFirst(element, stereotype, "riskName");
                        var id = TagsHelper.getStereotypePropertyFirst(element, stereotype, "riskID");
                        var likelihood = TagsHelper.getStereotypePropertyFirst(element, stereotype, "likelihood");
                        var maxConsequence = element.refGetValue("maxConsequence");

                        System.err.println("human name = " + element.getHumanName());
                        System.err.println("name =" + name);
                        System.err.println("id =" + id);
                        System.err.println("likelihood = " + likelihood);
                        System.err.println("maxConsequence = " + maxConsequence);
                    }
                }
            }

        } else {
            risks.add(new Risk("", "Id-l-5-c-1", 5, 1));
            risks.add(new Risk("", "Id-l-5-c-5", 5, 5));
            risks.add(new Risk("", "Id-l-1-c-1", 1, 1));
            risks.add(new Risk("", "Id-l-1-c-5", 1, 5));
            risks.add(new Risk("", "Id-l-3-c-2", 3, 2));
            risks.add(new Risk("", "Id-l-4-c-1", 4, 1));
        }

        return risks;
    }

}
