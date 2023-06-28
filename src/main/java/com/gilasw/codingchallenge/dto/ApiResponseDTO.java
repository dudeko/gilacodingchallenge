package com.gilasw.codingchallenge.dto;

import static com.gilasw.codingchallenge.dto.ResponseType.ERROR;
import static com.gilasw.codingchallenge.dto.ResponseType.SUCCESS;

public class ApiResponseDTO {

    private String message;
    private ResponseType responseType;

    private static ApiResponseDTO create(String message, ResponseType responseType) {
        ApiResponseDTO notificationDTO = new ApiResponseDTO();
        notificationDTO.setMessage(message);
        notificationDTO.setResponseType(responseType);
        return notificationDTO;
    }

    public static ApiResponseDTO success(String message) {
        return create(message, SUCCESS);
    }

    public static ApiResponseDTO error(String message) {
        return create(message, ERROR);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}
