package umc8th.spring.apiPayload.exception.handler;

import umc8th.spring.apiPayload.code.BaseErrorCode;
import umc8th.spring.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
