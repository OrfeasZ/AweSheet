/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

public class LineChart extends Chart {
    public LineChart(Cell inputs[]) {
        super(ChartType.LINE_CHART_TYPE, inputs);
    }

    @Override
    public boolean generateImageData() {
        // Create the dataset.
        DefaultCategoryDataset dataset = createDataset();

        if (dataset == null) {
            return false;
        }

        // Create the chart.
        JFreeChart lineChart = ChartFactory.createLineChart(
                title,
                nameX, nameY,
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        final CategoryPlot plot = lineChart.getCategoryPlot();
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());

        // Generate the image.
        try {
            imageData = ChartUtilities.encodeAsPNG(lineChart.createBufferedImage(720, 480));
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
