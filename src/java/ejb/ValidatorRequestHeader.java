/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import com.google.gson.Gson;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Constants;
import model.response.ErrorResponse;
import util.HTTPStatuses;

/**
 *
 * @author Dima
 */
@Stateless
public class ValidatorRequestHeader {

    public boolean validateAccountEnter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getHeader(Constants.ServletConstant.IMEI) == null) {
            response.getWriter().print(new Gson().toJson(new ErrorResponse(Errors.MyErrors.RequestError.IMEI_NOT_FOUNT)));
            response.setStatus(HTTPStatuses.ERROR_RESPONSE_CODE);
            throw new IOException();
        }else{
            return true;
        }
    }
}
