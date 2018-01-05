/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Errors.MyErrors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Constants;
import model.Constants.ReqestLink;
import model.response.ErrorResponse;
import util.HTTPStatuses;

/**
 *
 * @author Dima
 */
@Stateless
public class ValidateRequest {

    @EJB
    private ValidatorRequestHeader validatorRequestHeader;
    Gson gson = new Gson();

    public void validateHaveIMEI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        validatorRequestHeader.validateAccountEnter(request, response);
    }

    public boolean validateRequestBody(HttpServletRequest request, HttpServletResponse response, Class classType) {

        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }
            new Gson().fromJson(sb.toString(), classType);
            System.out.println(request.getPathInfo());
            System.out.println(sb.toString());
            return true;
        } catch (IOException ex) {
            response.setStatus(HTTPStatuses.ERROR_RESPONSE_CODE);
            return false;
        }
    }

    public boolean validateRequestBody(HttpServletRequest request, HttpServletResponse response, Type type) {

        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }
            System.out.println(request.getPathInfo());
            System.out.println("Body1: " + sb.toString());
            return true;
        } catch (IOException ex) {
            response.setStatus(HTTPStatuses.ERROR_RESPONSE_CODE);
            return false;
        }
    }

    public String readBody(HttpServletRequest request) {
        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }
            System.out.println("Body2: " + sb.toString());
            return sb.toString();
        } catch (IOException ex) {
            return "";
        }
    }

    public Object parser(String string, Class T) {
        return gson.fromJson(string, T);
    }

    public void validateRequestParameters(HttpServletRequest request, HttpServletResponse response, ReqestLink reqestLink) throws IOException {
        ErrorResponse mErrorResponse = null;// = new ErrorResponse();

        switch (reqestLink) {
            case ACCOUNT_EDIT_USERNAME: {
                if (request.getParameter(Constants.ParameterRequestConstant.USER_NAME) == null) {
                    mErrorResponse = new ErrorResponse(MyErrors.ParametrError.AccountError.PARAMETR_USER_NAME_IS_NULL);
                }
            }
        }

        if (mErrorResponse != null) {
            response.getWriter().println(new Gson().toJson(mErrorResponse));
            throw new IOException();
        }

    }

}
