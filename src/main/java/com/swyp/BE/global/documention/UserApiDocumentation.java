package com.swyp.BE.global.documention;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class UserApiDocumentation {

    /**
     * 마이페이지 조회 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "마이페이지 조회 API",
            description = "로그인한 사용자의 정보를 조회한다."
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "마이페이지 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "마이페이지 조회 성공",
                                    value = """
                    {
                      "success": true,
                      "data": {
                          "id": 1,
                          "nickname": "박소정",
                          "provider": "kakao",
                          "role": "USER"
                      },
                      "timestamp": "2026-07-16T09:30:00"
                    }
                    """
                            )
                    )
            ),

            // UNAUTHENTICATED
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "UNAUTHENTICATED",
                  "message": "인증이 필요합니다.",
                  "timestamp": "2026-07-16T09:30:00"
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
                  "timestamp": "2026-01-29T02:26:25"
                }
                """)
                    )
            )
    })
    public @interface UserInfoDoc {}

    /**
     * 닉네임 수정 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "닉네임 수정 API",
            description = "로그인한 사용자의 닉네임을 수정한다.",
            requestBody = @RequestBody(
                    description = "수정할 닉네임 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "닉네임 수정 예시",
                                    description = "수정할 닉네임 값 전달",
                                    value = """
                                        {
                                              "nickname": "새로운닉네임"
                                        }
                                    """
                            )
                    )
            )
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "닉네임 수정 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "닉네임 수정 성공",
                                    value = """
                    {
                      "success": true,
                      "timestamp": "2026-07-16T09:30:00"
                    }
                    """
                            )
                    )
            ),

            // VALIDATION_ERROR
            @ApiResponse(
                    responseCode = "400",
                    description = "닉네임 값 검증 실패",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "VALIDATION_ERROR",
                  "message": "닉네임은 필수입니다.",
                  "timestamp": "2026-07-16T09:30:00"
                }
                """)
                    )
            ),

            // UNAUTHENTICATED
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "UNAUTHENTICATED",
                  "message": "인증이 필요합니다.",
                  "timestamp": "2026-07-16T09:30:00"
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
                  "timestamp": "2026-01-29T02:26:25"
                }
                """)
                    )
            )
    })
    public @interface NicknameUpdateDoc {}

    /**
     * 회원 탈퇴 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "회원 탈퇴 API",
            description = "로그인한 사용자를 탈퇴 처리하고, Access/Refresh 토큰 쿠키를 삭제한다."
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "회원 탈퇴 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "회원 탈퇴 성공",
                                    value = """
                    {
                      "success": true,
                      "timestamp": "2026-07-16T09:30:00"
                    }
                    """
                            )
                    )
            ),

            // UNAUTHENTICATED
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "UNAUTHENTICATED",
                  "message": "인증이 필요합니다.",
                  "timestamp": "2026-07-16T09:30:00"
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
                  "timestamp": "2026-01-29T02:26:25"
                }
                """)
                    )
            )
    })
    public @interface WithdrawDoc {}
}
