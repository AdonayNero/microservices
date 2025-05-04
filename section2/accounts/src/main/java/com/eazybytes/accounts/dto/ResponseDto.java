package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;

@Schema(
        name = "Response",
        description = "Shema to hold response details"

)
@Data
@AllArgsConstructor
public class ResponseDto {


    private String statusCode;

    private String statusMsg;
}
