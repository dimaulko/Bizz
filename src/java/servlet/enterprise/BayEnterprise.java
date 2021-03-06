/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.enterprise;

import com.google.gson.Gson;
import dbmodels.Enterprise;
import ejb.EJBEnterprise;
import ejb.ValidateRequest;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.util.Pair;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccountGeneral;
import model.Constants;
import model.response.ErrorResponse;
import util.HTTPStatuses;

/**
 *
 * @author Dima
 */
public class BayEnterprise extends HttpServlet {

    @EJB
    private EJBEnterprise eJBEnterprise;

    @EJB
    private ValidateRequest validateRequest;

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
        try (PrintWriter out = response.getWriter()) {
            if (prepare(request, response)) {
                Enterprise income = new Gson().fromJson(validateRequest.readBody(request), Enterprise.class);
                Pair<AccountGeneral, ErrorResponse> res = eJBEnterprise.bayEnterprise(income, request.getHeader(Constants.ServletConstant.IMEI));
                if(res.getValue()==null){
                    out.println(new Gson().toJson(res.getKey()));
                }else{
                    out.println(new Gson().toJson(res.getValue()));
                    response.setStatus(HTTPStatuses.ERROR_RESPONSE_CODE);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    }// </editor-fold>

    private boolean prepare(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(Constants.ServletConstant.CONTENT_TYPE);
        response.setCharacterEncoding(Constants.ServletConstant.CHARSET_ENCODING);
        try {
            validateRequest.validateHaveIMEI(request, response);
            return true;
        } catch (IOException se) {
            System.out.println("WARNING2:" + se.getMessage());
            return false;
        }
    }
}
