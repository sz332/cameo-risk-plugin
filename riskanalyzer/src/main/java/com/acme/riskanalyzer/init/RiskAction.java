package com.acme.riskanalyzer.init;

import com.acme.riskanalyzer.domain.Risk;
import com.acme.riskanalyzer.ui.RiskDialog;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.jmi.helpers.TagsHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        var selectedPackage = findSelectedPackage(getTree());

        selectedPackage.ifPresent(rootPackage -> {
            var risks = findRisks(rootPackage);

            dialog.setRisks(risks);
            dialog.setVisible(true);
        });
    }

    private Optional<Package> findSelectedPackage(Tree tree) {

        var selectedNode = tree.getSelectedNode();

        if (selectedNode != null && selectedNode.getUserObject() instanceof Package) {
            return Optional.of((Package) selectedNode.getUserObject());
        }

        return Optional.empty();
    }

    private List<Risk> findRisks(Package rootPackage) {
        var project = Application.getInstance().getProject();
        var profile = StereotypesHelper.getProfile(project, "Safety");
        var stereotype = StereotypesHelper.getStereotype(project, "Risk", profile);

        return rootPackage.getOwnedElement().stream()
                .filter(element -> StereotypesHelper.hasStereotype(element, stereotype))
                .map(element -> createRisk(element, stereotype))
                .collect(Collectors.toList());
    }

    private Risk createRisk(Element element, Stereotype stereotype) {
        var name = TagsHelper.getStereotypePropertyFirst(element, stereotype, "riskName").toString();
        var id = TagsHelper.getStereotypePropertyFirst(element, stereotype, "riskID").toString();
        var likelihood = asNumber(TagsHelper.getStereotypePropertyFirst(element, stereotype, "likelihood"));
        var maxConsequence = asNumber(element.refGetValue("maxConsequence"));

        return new Risk(element.getID(), id, name, likelihood, maxConsequence);
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
