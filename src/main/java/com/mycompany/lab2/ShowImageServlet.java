/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 *
 * @author student
 */
@WebServlet(name = "ShowImageServlet", urlPatterns = {"/ShowImageServlet"})
public class ShowImageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=obrazek.jpg");

        try(OutputStream out = response.getOutputStream()) {

            PollResultFileWriter pollResultFileWriter = new PollResultFileWriter("results.txt");
            pollResultFileWriter.readFromFile();

            this.showResultsAsImage(pollResultFileWriter.getChosenLanguage(), out);

        }
    }


    private void showResultsAsImage(Map<String, Integer> pollData, OutputStream out) throws IOException {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();


        for (String language : pollData.keySet()) {
            dataset.addValue(pollData.get(language), language, language);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Głosowanie na swój ulubiony język programowania", // tytul wykresu
                "Język", // etykieta osi X
                "Ilość głosow", // etykieta osi Y
                dataset, // dane
                PlotOrientation.VERTICAL,
                true,
                false,
                false);

        chart.setBackgroundPaint(Color.PINK);

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);

        BufferedImage img =
                chart.createBufferedImage(400,400,BufferedImage.TYPE_INT_RGB, null);

        javax.imageio.ImageIO.write(img, "jpg", out);

        out.close();
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
