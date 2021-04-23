package com.demo.linechartexample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.demo.linechartexample.R;
import com.demo.linechartexample.baseView.BaseActivity;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    SeekBar seekBar;
    TextView seekBarText;
    Button largestButton;

    @Override
    protected void initActivity() {
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        seekBarText=(TextView) findViewById(R.id.seekBarText);
        largestButton=(Button) findViewById(R.id.largestButton);
        largestButton.setOnClickListener(this);

        seekBarText.setText("Target:  "+ 75);
        seekBar.setProgress(75);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(this);

        APIlib.getInstance().setActiveAnyChartView(anyChartView);


        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("JAN", 70, 50));
        seriesData.add(new CustomDataEntry("FEB", 85, 60));
        seriesData.add(new CustomDataEntry("MAR", 90, 75));
        seriesData.add(new CustomDataEntry("APR", 50, 80));
        seriesData.add(new CustomDataEntry("MAY", 70, 65));
        seriesData.add(new CustomDataEntry("JUN", 30, 55));
        seriesData.add(new CustomDataEntry("JUL", 40, 20));
        seriesData.add(new CustomDataEntry("AUG", 60, 40));
        seriesData.add(new CustomDataEntry("SEP", 50, 30));
        seriesData.add(new CustomDataEntry("OCT", 90, 75));
        seriesData.add(new CustomDataEntry("NOV", 80, 65));
        seriesData.add(new CustomDataEntry("DEC", 75, 60));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping agentOneSeriesMapping = set.mapAs("{ x: 'x', value: 'value1' }");
        Mapping agentTwoSeriesMapping = set.mapAs("{ x: 'x', value: 'value2' }");

        Line series1 = cartesian.line(agentOneSeriesMapping);
        series1.name("Agent One");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(agentTwoSeriesMapping);
        series3.name("Agent Two");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.largestButton:
                Intent i=new Intent(MainActivity.this,LargestNumberActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        seekBarText.setText("Target:  "+ (seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);

        }
    }
}