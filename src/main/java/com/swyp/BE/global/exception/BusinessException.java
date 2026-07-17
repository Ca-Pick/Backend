package com.swyp.BE.global.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * 케이크를 찾을 수 없는 경우 사용하는 정적 팩토리 메서드
     *
     * @return 케이크를 찾을 수 없음 예외
     */
    public static BusinessException referenceNotFound() {
        return new BusinessException(ErrorCode.REFERENCE_NOT_FOUND);
    }
}
