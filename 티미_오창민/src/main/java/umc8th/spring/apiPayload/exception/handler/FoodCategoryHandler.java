package umc8th.spring.apiPayload.exception.handler;

import umc8th.spring.apiPayload.code.BaseErrorCode;
import umc8th.spring.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode code) {
        super(code);
    }
}

