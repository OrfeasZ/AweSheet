package com.awesheet.models.charts;

import com.awesheet.models.Cell;
import com.awesheet.enums.ChartType;
import com.awesheet.models.Chart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BarChart extends Chart {
    public BarChart(Cell inputs[]) {
        super(ChartType.BAR_CHART_TYPE, inputs);
    }

    @Override
    public boolean generateImageData() {
        // Create the dataset.
        DefaultCategoryDataset dataset = createDataset();

        if (dataset == null) {
            return false;
        }

        // Create the chart.
        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                nameX, nameY,
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        final CategoryPlot plot = barChart.getCategoryPlot();
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());

        // Generate the image.
        try {
            imageData = ChartUtilities.encodeAsPNG(barChart.createBufferedImage(720, 480));
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
