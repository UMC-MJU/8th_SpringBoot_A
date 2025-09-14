package umc8th.spring.apiPayload.exception.handler;

import umc8th.spring.apiPayload.code.BaseErrorCode;
import umc8th.spring.apiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {
    public MemberMissionHandler(BaseErrorCode code) {
        super(code);
    }
}
