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

public class ReferenceApiDocumentation {

    /**
     * 주문서 레퍼런스 조회 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "주문서 레퍼런스 조회 API",
            description = "주문서를 받아 맞는 레퍼런스를 조회합니다.",
            requestBody = @RequestBody(
                    description = "주문서 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "주문서 정보 예시",
                                    description = "주문서롤 통해 레퍼런스 조회 요청",
                                    value = """
                                        {
                                              "place": "강남",
                                              "target": "친구",
                                              "shape": "기본형",
                                              "color": "파스텔",
                                              "mood": "귀여운",
                                              "detailTags": ["리본"]
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
                                          "data": {
                                              "cakes": [
                                                  {
                                                      "cakeId": 1,
                                                      "instagramEmbed": "https://www.instagram.com/p/DFxF4K8yG6D/J2",
                                                      "cakedetailtags": [
                                                          "리본",
                                                          "시스루",
                                                          "블랙리본",
                                                          "하트",
                                                          "블랙",
                                                          "핑크",
                                                          "블랙핑크",
                                                          "실타래크림",
                                                          "레터링",
                                                          "러블리",
                                                          "시크",
                                                          "키치"
                                                      ]
                                                  }
                                              ],
                                              "tags": [
                                                  "강남",
                                                  "친구",
                                                  "기본형",
                                                  "파스텔",
                                                  "귀여운",
                                                  "리본"
                                              ]
                                          },
                                          "timestamp": "2026-07-14T21:08:52"
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
                  "timestamp": "2026-01-29T02:26:25"
                }
                """)
                    )
            )
    })
    public @interface CakeDoc {}
}

