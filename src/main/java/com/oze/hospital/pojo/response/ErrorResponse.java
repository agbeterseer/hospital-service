package com.oze.hospital.pojo.response;

import com.oze.hospital.util.Constant;
import org.apache.logging.log4j.util.Strings;

public class ErrorResponse extends ResponseHelper {

    public ErrorResponse(String message){
        super(false, message, Strings.EMPTY);
    }

    public ErrorResponse(){
        super(false, Constant.ERROR_MESSAGE, Strings.EMPTY);
    }

}
