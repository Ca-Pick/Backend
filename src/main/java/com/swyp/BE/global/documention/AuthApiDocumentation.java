package com.swyp.BE.global.documention;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AuthApiDocumentation {

    /**
     * 액세스 토큰 재발급 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "액세스 토큰 재발급 API",
            description = "리프레시 토큰 쿠키로 액세스 토큰을 재발급한다."
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "재발급 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "재발급 성공",
                                    value = """
                    {
                      "success": true,
                      "timestamp": "2026-07-19T21:30:00"
                    }
                    """
                            )
                    )
            ),

            // INVALID_REFRESH_TOKEN / EXPIRED_REFRESH_TOKEN
            @ApiResponse(
                    responseCode = "401",
                    description = "리프레시 토큰이 없거나 유효하지 않음 / 만료되었거나 저장된 토큰이 없음",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "INVALID_REFRESH_TOKEN",
                                            description = "리프레시 토큰이 없거나 유효하지 않음",
                                            value = """
                        {
                          "success": false,
                          "code": "INVALID_REFRESH_TOKEN",
                          "message": "유효하지 않은 리프레시 토큰입니다.",
                          "timestamp": "2026-07-19T21:30:00"
                        }
                        """
                                    ),
                                    @ExampleObject(
                                            name = "EXPIRED_REFRESH_TOKEN",
                                            description = "만료되었거나 저장된 토큰이 없음",
                                            value = """
                        {
                          "success": false,
                          "code": "EXPIRED_REFRESH_TOKEN",
                          "message": "리프레시 토큰이 만료되었거나 존재하지 않습니다.",
                          "timestamp": "2026-07-19T21:30:00"
                        }
                        """
                                    )
                            }
                    )
            ),

            // NOT_FOUND
            @ApiResponse(
                    responseCode = "404",
                    description = "토큰은 유효하지만 사용자를 찾을 수 없음",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "NOT_FOUND",
                  "message": "요청한 리소스를 찾을 수 없습니다.",
                  "timestamp": "2026-07-19T21:30:00"
                }
                """)
                    )
            ),

            // INTERNAL_SERVER_ERROR
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 오류",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "INTERNAL_SERVER_ERROR",
                  "message": "서버 내부 오류가 발생했습니다.",
                  "timestamp": "2026-07-19T21:30:00"
                }
                """)
                    )
            )
    })
    public @interface ReissueDoc {}

    /**
     * 로그아웃 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "로그아웃 API",
            description = "리프레시 토큰을 삭제하고, Access/Refresh 토큰 쿠키를 삭제한다. 리프레시 토큰이 없거나 유효하지 않아도 에러 없이 성공 처리된다."
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "로그아웃 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "로그아웃 성공",
                                    value = """
                    {
                      "success": true,
                      "timestamp": "2026-07-19T21:30:00"
                    }
                    """
                            )
                    )
            ),

            // INTERNAL_SERVER_ERROR
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 오류",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "INTERNAL_SERVER_ERROR",
                  "message": "서버 내부 오류가 발생했습니다.",
                  "timestamp": "2026-07-19T21:30:00"
                }
                """)
                    )
            )
    })
    public @interface LogoutDoc {}
}
