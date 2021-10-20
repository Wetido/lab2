/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "PollServlet", urlPatterns = {"/PollServlet"})
public class PollServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {


            Map<String, String[]>parameters = request.getParameterMap();
            ArrayList<String>formLanguages = this.getAlChosenLanguages(parameters);

            this.writeHtmlBegining(out);
            this.writeAnswer(formLanguages, out);

            Helper helper = new Helper("C:\\PLIKI\\Programowanie\\lab2\\src\\main\\java\\com\\mycompany\\lab2\\" + "results.txt");
            helper.readFromFile();
            helper.appendNewValues(formLanguages);
            helper.writeToFile();

            this.writeResults(helper.getChosenLanguage(), out);

            this.writeHtmlEnd(out);
        }
    }

    private ArrayList<String> getAlChosenLanguages(Map<String, String[]> parameters) {

        if (!parameters.isEmpty()){
            return new ArrayList<String>(parameters.keySet());
        }else{
            return new ArrayList<>();
        }
    }

    private void writeHtmlBegining(PrintWriter out){

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet PollServlet</title>");
        out.println("</head>");
        out.println("<body>");
    }

    private void writeHtmlEnd(PrintWriter out){
        out.println("");
        out.println("</body>");
        out.println("</html>");
    }

    private void writeAnswer(ArrayList<String> chosenLanguages, PrintWriter out) {


        if(chosenLanguages.isEmpty()){
            out.println("<h1>Nic nie umiesz! Weź się za naukę gnoju!</h1>");
        }else{
            out.println("<h1>Umiesz jezyki</h1>");
            for (String language: chosenLanguages) {
                out.println(language);
                out.println("<br>");
            }
        }
    }

    private void writeResults(Map<String, Integer> chosenLanguages, PrintWriter out) {

        if(chosenLanguages.isEmpty()){
            out.println("<h1>Wyniki ankiety są puste</h1>");
        }else{
            out.println("<h1>Wyniki ankiety</h1>");
            for (String language : chosenLanguages.keySet()) {
                out.println(language + ":" + chosenLanguages.get(language));
                out.println("<br>");
            }
        }
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
