/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

import model.Constants;


/**
 *
 * @author Dima
 */
public class MyErrors {

    public class RequestError {

        /**
         *
         */
        public static final String IMEI_NOT_FOUNT = Constants.ServletConstant.IMEI + " not found";
    }

    public class ParametrError {
        
        public class AccountError{
            public static final String PARAMETR_USER_NAME_IS_NULL = "parameter " + Constants.ParameterRequestConstant.USER_NAME + " cannot be null";
        }
        
    }
}
