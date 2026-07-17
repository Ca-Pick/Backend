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

public class SaveApiDocumentation {

    /**
     * 저장함 좋아요 생성 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "저장함 좋아요 생성 API",
            description = "좋아요 버튼을 누른다."
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "좋아요 생성 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "좋아요 생성 성공",
                                    value = """
                    {
                      "success": true,
                      "timestamp": "2026-01-19T21:30:00"
                    }
                    """
                            )
                    )
            ),

            // UNAUTHENTICATED
            @ApiResponse(
                    responseCode = "401",
                    description = "로그인이 필요함",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "UNAUTHENTICATED",
                  "message": "로그인이 필요합니다.",
                  "timestamp": "2026-01-29T02:26:25"
                }
                """)
                    )
            ),

            // REFERENCE_NOT_FOUND
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 케이크",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "REFERENCE_NOT_FOUND",
                  "message": "케이크 또는 가게를 찾을 수 없습니다.",
                  "timestamp": "2026-01-29T02:26:25"
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
    public @interface SavePostDoc {}

    /**
     * 저장함 좋아요 취소 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "저장함 좋아요 취소 API",
            description = "좋아요(저장)를 취소한다. 저장돼있지 않아도 에러 없이 성공 처리된다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "좋아요 취소 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "좋아요 취소 성공",
                                    value = """
                    {
                      "success": true,
                      "timestamp": "2026-01-19T21:30:00"
                    }
                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "로그인이 필요함",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "UNAUTHENTICATED",
                  "message": "로그인이 필요합니다.",
                  "timestamp": "2026-01-29T02:26:25"
                }
                """)
                    )
            ),
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
    public @interface SaveDeleteDoc {}

    /**
     * 저장함 목록 조회 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "저장함 목록 조회 API",
            description = "로그인한 사용자가 저장한 케이크 목록을 조회한다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "저장함 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "저장함 조회 성공",
                                    value = """
                    {
                      "success": true,
                      "data": {
                        "cakes": [
                          {
                            "cakeId": 1,
                            "instagramEmbed": "<blockquote class=\\"instagram-media\\">...</blockquote>",
                            "cakedetailtags": ["레터링", "플라워"]
                          }
                        ]
                      },
                      "timestamp": "2026-01-19T21:30:00"
                    }
                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "로그인이 필요함",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject("""
                {
                  "success": false,
                  "code": "UNAUTHENTICATED",
                  "message": "로그인이 필요합니다.",
                  "timestamp": "2026-01-29T02:26:25"
                }
                """)
                    )
            ),
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
    public @interface SaveListDoc {}
}
