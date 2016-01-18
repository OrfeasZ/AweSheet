package com.awesheet.models.charts;

import com.awesheet.models.Cell;
import com.awesheet.enums.ChartType;
import com.awesheet.models.Chart;

public class LineChart extends Chart {

    public LineChart(Cell inputs[]) {
        super(ChartType.LINE_CHART_TYPE, inputs);
    }

    @Override
    public boolean generateImageData() {
        // TODO
        return false;
    }
}
