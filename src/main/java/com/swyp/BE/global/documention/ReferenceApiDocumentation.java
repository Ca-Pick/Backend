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
                                              "detailTags": ["리본", "블랙리본"]
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
                    description = "주문서 레퍼런스 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "주문서 레퍼런스 조회 성공",
                                    value = """
                                       {
                                                     "success": true,
                                                     "data": {
                                                         "cakes": [
                                                             {
                                                                 "cakeId": 1,
                                                                 "instagramEmbed": "https://www.instagram.com/p/DFxF4K8yG6D/J2",
                                                                 "saved": false,
                                                                 "cakeDetailTags": [
                                                                     "리본",
                                                                     "블랙리본"
                                                                 ],
                                                                 "cakeDetailCount": 2
                                                             },
                                                             {
                                                                 "cakeId": 3,
                                                                 "instagramEmbed": "https://www.instagram.com/p/DaR64RlMKE1/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==",
                                                                 "saved": false,
                                                                 "cakeDetailTags": [
                                                                     "리본"
                                                                 ],
                                                                 "cakeDetailCount": 1
                                                             }
                                                         ],
                                                         "tags": [
                                                             "강남",
                                                             "친구",
                                                             "기본형",
                                                             "파스텔",
                                                             "귀여운",
                                                             "리본",
                                                             "블랙리본"
                                                         ]
                                                     },
                                                     "timestamp": "2026-07-20T01:42:02"
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

    /**
     * 케이크 상세 페이지 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "케이크 상세 페이지 API",
            description = "케이크 상세 페이지를 조회합니다."
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "케이크 상세 페이지 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "케이크 상세 페이지 조회 성공",
                                    value = """
                                         {
                                                         "success": true,
                                                         "data": {
                                                             "address": "서울특별시 강남구 강남대로122길 30-10 1층 달꼬미 논현점",
                                                             "cakeId": 3,
                                                             "cakelists": [
                                                                 {
                                                                     "cakeId": 3,
                                                                     "instagramEmbed": "https://www.instagram.com/p/DaR64RlMKE1/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==",
                                                                     "saved": true
                                                                 },
                                                                 {
                                                                     "cakeId": 4,
                                                                     "instagramEmbed": "https://www.instagram.com/p/DY1O9Vlsp1G/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==",
                                                                     "saved": false
                                                                 }
                                                             ],
                                                             "instagramEmbed": "https://www.instagram.com/p/DaR64RlMKE1/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==",
                                                             "instagramUrl": "https://www.instagram.com/dalccomi/",
                                                             "latitude": 37.51,
                                                             "longitude": 127.02,
                                                             "name": "달꼬미",
                                                             "saved": true,
                                                             "tags": [
                                                                 "강남",
                                                                 "친구",
                                                                 "연인",
                                                                 "기본형",
                                                                 "파스텔",
                                                                 "심플",
                                                                 "귀여운",
                                                                 "리본",
                                                                 "핑크리본",
                                                                 "꽃",
                                                                 "플라워",
                                                                 "생화",
                                                                 "장미",
                                                                 "핑크",
                                                                 "화이트",
                                                                 "크림",
                                                                 "물방울크림",
                                                                 "버블크림",
                                                                 "레터링",
                                                                 "생일",
                                                                 "러블리",
                                                                 "단아한",
                                                                 "미니멀"
                                                             ]
                                                         },
                                                         "timestamp": "2026-07-20T02:19:10"
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
    public @interface DetailCakeDoc {}


    /**
     * 추천 레퍼런스 API 문서
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "추천 레저런스 조회 API",
            description = "추천 레퍼런스를 조회합니다."
    )
    @ApiResponses({
            // SUCCESS
            @ApiResponse(
                    responseCode = "200",
                    description = "추천 레퍼런스 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.swyp.BE.global.response.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "SUCCESS",
                                    description = "추천 레퍼런스 조회 성공",
                                    value = """
                                            {
                                                 "success": true,
                                                 "data": {
                                                     "academic": [],
                                                     "birthday": [
                                                         {
                                                             "cakeId": 1,
                                                             "instagramEmbed": "https://www.instagram.com/p/DFxF4K8yG6D/J2",
                                                             "saved": false
                                                         },
                                                         {
                                                             "cakeId": 3,
                                                             "instagramEmbed": "https://www.instagram.com/p/DaR64RlMKE1/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==",
                                                             "saved": false
                                                         }
                                                     ],
                                                     "celebration": [
                                                         {
                                                             "cakeId": 2,
                                                             "instagramEmbed": "https://www.instagram.com/p/DaDC3ckTr7v/?utm_source=ig_web_copy_link",
                                                             "saved": false
                                                         },
                                                         {
                                                             "cakeId": 4,
                                                             "instagramEmbed": "https://www.instagram.com/p/DY1O9Vlsp1G/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==",
                                                             "saved": false
                                                         }
                                                     ]
                                                 },
                                                 "timestamp": "2026-07-20T02:22:19"
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
    public @interface RecommendCakeDoc {}

}
