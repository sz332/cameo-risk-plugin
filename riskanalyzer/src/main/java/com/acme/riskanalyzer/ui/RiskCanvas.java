package com.acme.riskanalyzer.ui;

import com.acme.riskanalyzer.domain.Risk;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Zsolt
 */
public class RiskCanvas extends JPanel {

    private final static int COLUMNS = 5;
    private final static int ROWS = 5;

    private enum Severity {
        OK, WARNING, ERROR
    }

    private Severity[][] severities = new Severity[][]{
            {Severity.OK, Severity.WARNING, Severity.ERROR, Severity.ERROR, Severity.ERROR},
            {Severity.OK, Severity.WARNING, Severity.WARNING, Severity.ERROR, Severity.ERROR},
            {Severity.OK, Severity.OK, Severity.WARNING, Severity.WARNING, Severity.ERROR},
            {Severity.OK, Severity.OK, Severity.OK, Severity.WARNING, Severity.WARNING},
            {Severity.OK, Severity.OK, Severity.OK, Severity.OK, Severity.WARNING}
    };

    private final List<Risk> risks;

    public RiskCanvas() {
        super();
        this.risks = new ArrayList<>();
    }

    public void setRisks(List<Risk> risks) {
        this.risks.clear();
        this.risks.addAll(risks);
        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    public void paintComponent(Graphics g) {

        var width = getWidth();
        var height = getHeight();

        g.setColor(Color.red);
        g.drawRect(0, 0, width - 1, height - 1);

        int cellSize = Math.min(getWidth() / 5, getHeight() / 5);

        var left = (width - cellSize * COLUMNS) / 2;
        var top = (height - cellSize * ROWS) / 2;

        g.translate(left, top);

        paintRiskCells(g, cellSize);

        g.translate(-left, -top);
    }

    private void paintRiskCells(Graphics g, int cellSize) {

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                paintRiskCell(g, row, column, cellSize, severities[row][column]);
            }
        }

    }

    private void paintRiskCell(Graphics g, int row, int column, int cellSize, Severity severity) {
        Color color = calculateSeverityColor(severity);
        Point point = calculateCoordinate(row, column, cellSize);

        g.setColor(color);
        g.fillRect(point.x, point.y, cellSize, cellSize);

        g.setColor(Color.black);
        g.drawRect(point.x, point.y, cellSize, cellSize);

        g.translate(point.x + 5, point.y + 5);
        paintRisksInCell(g, row, column, cellSize);
        g.translate(-(point.x + 5), -(point.y + 5));
    }

    private void paintRisksInCell(Graphics g, int row, int column, int cellSize) {

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.translate(0, metrics.getAscent());

        var textHeight = metrics.getHeight();
        var y = 0;

        for (var risk : risks){
            if (riskAppliesToCell(risk, row, column)){
                g.drawString("Risk ID:", 0, y);
                y += textHeight;

                g.drawString(risk.getId(), 0, y);
                y+= textHeight;
            }
        }

        g.translate(0,-metrics.getAscent());
    }

    boolean riskAppliesToCell(Risk risk, int row, int column){

        if ((risk.getLikelihood() == 5 - row) && (risk.getMaxConsequence() == column + 1)){
            return true;
        }

        return false;
    }

    private Point calculateCoordinate(int row, int column, int cellSize) {
        int x = cellSize * column;
        int y = cellSize * row;

        return new Point(x, y);
    }

    private Color calculateSeverityColor(Severity severity) {
        switch (severity) {
            case WARNING:
                return Color.yellow;
            case ERROR:
                return Color.red;
            default:
                return Color.green;
        }
    }


}
