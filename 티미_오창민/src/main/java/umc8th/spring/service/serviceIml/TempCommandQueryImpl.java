package umc8th.spring.service.serviceIml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring.apiPayload.code.status.ErrorStatus;
import umc8th.spring.apiPayload.exception.handler.TempHandler;
import umc8th.spring.service.TempQueryService;

@Service
@RequiredArgsConstructor
public class TempCommandQueryImpl implements TempQueryService {


    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1) {
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
