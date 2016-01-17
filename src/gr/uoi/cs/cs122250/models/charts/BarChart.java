package gr.uoi.cs.cs122250.models.charts;

import gr.uoi.cs.cs122250.enums.ChartType;
import gr.uoi.cs.cs122250.models.Cell;
import gr.uoi.cs.cs122250.models.Chart;

public class BarChart extends Chart {

    public BarChart(Cell[] inputs) {
        super(ChartType.BAR_CHART_TYPE, inputs);
    }

    @Override
    public boolean generateImageData() {
        return false;
    }
}
