package com.acme.riskanalyzer.ui;

import com.acme.riskanalyzer.domain.Risk;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zsolt
 */
public class RiskCanvas extends JPanel {

    private static final int COLUMNS = 5;
    private static final int ROWS = 5;

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

    private final List<Risk> risks = new ArrayList<>();

    public RiskCanvas() {
        super();
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

        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);

        var cellSize = Math.min(width / COLUMNS, height / ROWS);

        var left = (width - cellSize * COLUMNS) / 2;
        var top = (height - cellSize * ROWS) / 2;

        g.translate(left, top);

        paintRiskCells(g, cellSize);

        g.translate(-left, -top);
    }

    private void paintRiskCells(Graphics g, int cellSize) {

        for (var row = 0; row < ROWS; row++) {
            for (var column = 0; column < COLUMNS; column++) {
                paintRiskCell(g, row, column, cellSize, severities[row][column]);
            }
        }

    }

    private void paintRiskCell(Graphics g, int row, int column, int cellSize, Severity severity) {
        var color = calculateSeverityColor(severity);
        var point = calculateCoordinate(row, column, cellSize);

        g.setColor(color);
        g.fillRect(point.x, point.y, cellSize, cellSize);

        g.setColor(Color.black);
        g.drawRect(point.x, point.y, cellSize, cellSize);

        g.translate(point.x + 5, point.y + 5);
        paintRisksInCell(g, row, column);
        g.translate(-(point.x + 5), -(point.y + 5));
    }

    private void paintRisksInCell(Graphics g, int row, int column) {

        var metrics = g.getFontMetrics(g.getFont());
        g.translate(0, metrics.getAscent());

        var textHeight = metrics.getHeight();
        var y = 0;

        for (var risk : risks) {
            if (riskAppliesToCell(risk, row, column)) {
                g.drawString("Risk ID:", 0, y);
                y += textHeight;

                g.drawString(risk.getId(), 0, y);
                y += textHeight;
            }
        }

        g.translate(0, -metrics.getAscent());
    }

    boolean riskAppliesToCell(Risk risk, int row, int column) {
        return ((risk.getLikelihood() == ROWS - row) && (risk.getMaxConsequence() == column + 1));
    }

    private Point calculateCoordinate(int row, int column, int cellSize) {
        var x = cellSize * column;
        var y = cellSize * row;

        return new Point(x, y);
    }

    private Color calculateSeverityColor(Severity severity) {
        switch (severity) {
            case WARNING:
                return Color.yellow;
            case ERROR:
                return Color.red;
            case OK:
                return Color.green;
            default:
                return Color.magenta;
        }
    }

}
