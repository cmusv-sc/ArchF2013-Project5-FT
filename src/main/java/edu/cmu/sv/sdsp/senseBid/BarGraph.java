/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley.
All rights reserved.

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

Please contact http://www.cmu.edu/silicon-valley/ if you have any
questions.
*/
package main.java.edu.cmu.sv.sdsp.senseBid;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
// TODO: Auto-generated Javadoc

/**
 * The Class BarGraph.
 */
public class BarGraph {
   
    /**
     * Gets the intent.
     *
     * @param context the context
     * @return the intent
     */
    public Intent getIntent(Context context){
       
        int y[] = {25,10,15,20};
       
        CategorySeries series = new CategorySeries("Bar1");
        for(int i=0; i < y.length; i++){
            series.add("Bar"+(i+1),y[i]);
        }
       
        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();  // collection of series under one object.,there could any
        dataSet.addSeries(series.toXYSeries());                            // number of series
       
        //customization of the chart
   
        XYSeriesRenderer renderer = new XYSeriesRenderer();     // one renderer for one series
        renderer.setColor(Color.BLUE);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 5.5d);
        renderer.setLineWidth((float) 10.5d);
           
       
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();   // collection multiple values for one renderer or series
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setChartTitle("Bid Amount");
//        mRenderer.setXTitle("xValues");
        mRenderer.setYTitle("Optimum Range");
        mRenderer.setZoomButtonsVisible(true);    mRenderer.setShowLegend(true);
        mRenderer.setShowGridX(true);      // this will show the grid in  graph
        mRenderer.setShowGridY(true);             
//        mRenderer.setAntialiasing(true);
        mRenderer.setBarSpacing(.5);   // adding spacing between the line or stacks
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.BLACK);
        mRenderer.setXAxisMin(0);
//        mRenderer.setYAxisMin(.5);
        mRenderer.setXAxisMax(5);
        mRenderer.setYAxisMax(50);
//   
        mRenderer.setXLabels(0);
        mRenderer.addXTextLabel(1,"Week1");
        mRenderer.addXTextLabel(2,"Week2");
        mRenderer.addXTextLabel(3,"Week3");
        mRenderer.addXTextLabel(4,"Week4");
        mRenderer.setPanEnabled(true, true);    // will fix the chart position
     Intent intent = ChartFactory.getBarChartIntent(context, dataSet, mRenderer,Type.DEFAULT);
       
                return intent;
    }
}