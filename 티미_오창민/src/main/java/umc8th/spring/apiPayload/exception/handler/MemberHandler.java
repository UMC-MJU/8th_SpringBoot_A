package umc8th.spring.apiPayload.exception.handler;

import umc8th.spring.apiPayload.code.BaseErrorCode;
import umc8th.spring.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
