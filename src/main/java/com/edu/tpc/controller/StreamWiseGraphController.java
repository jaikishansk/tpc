/*
 * Date: 6/3/12
 * @author Jaikishan
 */

package com.edu.tpc.controller;


import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;  

@Controller
public class StreamWiseGraphController
{
    //@RequestMapping(value="streamwisereport.html", method=RequestMethod.GET)
    public void showGraph(HttpServletRequest request,
                          HttpServletResponse response)
    {
        try
        {
            response.setContentType("image/png");
            final double[][] data = new double[][] { { 210, 300, 320, 265 },
                                                     { 200, 304, 201, 201 } };
            String[] seriesNames = new String[] { "Actual", "Budget"};
            String[] categoryNames = new String[] { "Loan", "Trade","Cash","Treasury" };
            CategoryDataset dataset = DatasetUtilities.createCategoryDataset(seriesNames, categoryNames, data);

            JFreeChart chart = null;
            BarRenderer renderer = null;
            CategoryPlot plot = null;

            final CategoryAxis categoryAxis = new CategoryAxis("Match");
            final ValueAxis valueAxis = new NumberAxis("Run");
            renderer = new BarRenderer();

            plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
            plot.setOrientation(PlotOrientation.VERTICAL);
            chart = new JFreeChart(" ", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

            chart.setBackgroundPaint(new Color(249, 231, 236));
            Paint p1 = new GradientPaint(0.0f, 0.0f,
                                         new Color(16, 89, 172), 0.0f, 0.0f,
                                         new Color(201, 201, 244));
            renderer.setSeriesPaint(1, p1);
            Paint p2 = new GradientPaint(0.0f, 0.0f, 
                                         new Color(255, 35, 35), 0.0f, 0.0f,
                                         new Color(255, 180, 180));
            renderer.setSeriesPaint(2, p2);
            plot.setRenderer(renderer);
            JFreeChart jfreechart1 = ChartFactory.createBarChart("Bar Chart Demo", "", " ", dataset, 
                                        PlotOrientation.VERTICAL, true, true, true);

            ChartUtilities.writeChartAsPNG(response.getOutputStream(), jfreechart1,
                                           400, 400); 
            response.getOutputStream().close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
