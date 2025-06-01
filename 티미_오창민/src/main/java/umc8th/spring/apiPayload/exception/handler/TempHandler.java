package umc8th.spring.apiPayload.exception.handler;

import umc8th.spring.apiPayload.code.BaseErrorCode;
import umc8th.spring.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
