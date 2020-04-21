package pers.edwin.contract.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pers.edwin.contract.response.ResultResponse;


/**
 * Created by 廖师兄
 * 2017-05-15 00:22
 *
 * @author liang
 */
public class ResultUtil {


    public static ResponseEntity success(HttpStatus httpStatus, Object object) {
        ResultResponse<Object> resultResponse = new ResultResponse<>();
        resultResponse.setCode(0);
        resultResponse.setMsg("success");
        resultResponse.setData(object);
        return ResponseEntity.status(httpStatus).body(resultResponse);
    }

    public static ResponseEntity success(HttpStatus httpStatus) {
        return success(httpStatus,null);
    }

    public static ResponseEntity error(HttpStatus httpStatus, String msg) {
        ResultResponse<Object> resultResponse = new ResultResponse();
        resultResponse.setCode(1);
        resultResponse.setMsg(msg);
        return ResponseEntity.status(httpStatus).body(resultResponse);
    }
}
