package com.awesheet.models.charts;

import com.awesheet.models.Cell;
import com.awesheet.enums.ChartType;
import com.awesheet.models.Chart;

public class BarChart extends Chart {

    public BarChart(Cell inputs[]) {
        super(ChartType.BAR_CHART_TYPE, inputs);
    }

    @Override
    public boolean generateImageData() {
        // TODO
        return false;
    }
}
