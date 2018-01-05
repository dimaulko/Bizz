/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.account;

import com.google.gson.Gson;
import ejb.EJBAccount;
import ejb.ValidateRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Constants;

/**
 *
 * @author Dima
 */
public class enter extends HttpServlet {
    
    @EJB
    private EJBAccount account;
    
    @EJB
    private ValidateRequest validateRequest;
    
    private static final Logger LOG = Logger.getLogger(enter.class.getName());

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
        if (prepare(request, response)) {
            try {
                System.out.println("trabl");
                PrintWriter out = response.getWriter();
                System.out.println("trab2");
                account.enter(request, response);
                out.println(new Gson().toJson(account.getGeneralAccount(request.getHeader(Constants.ServletConstant.IMEI))));
            } catch (IOException se) {
                System.out.println("trab3");
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
//        response.setCharacterEncoding(Constants.ServletConstant.CHARSET_ENCODING);
        try {
//            ProfileDB d = new ProfileDB("5555", "www.asdfasdf.asdfasdf", "DIMA", "ULKO");
//            Gson gson = new Gson();
//            System.out.println(gson.toJson(d));
            validateRequest.validateHaveIMEI(request, response);
//            if (validateRequest.validateRequestBody(request, response, ProfileDB.class)) {
//                System.out.println("true:");
            return true;
//            } else {
//                System.out.println("false");
//                return false;
//            }

        } catch (IOException se) {
            System.out.println("WARNING2:" + se.getMessage());
            return false;
        }
    }
}
