package umc8th.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc8th.spring.apiPayload.ApiResponse;
import umc8th.spring.converter.TempConverter;
import umc8th.spring.service.TempService.TempQueryService;
import umc8th.spring.web.dto.TempResponse;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {
        private final TempQueryService tempQueryService;

        @GetMapping("/test")
        public ApiResponse<TempResponse.TempTestDTO> testAPI(){

            return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
        }


        @GetMapping("/exception")
        public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam(value = "flag") Integer flag){
            tempQueryService.CheckFlag(flag);
            return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
    }
