package com.dglee.mini_prj.etc;

import com.dglee.mini_prj.question.Question;
import com.dglee.mini_prj.question.QuestionDTO;
import com.dglee.mini_prj.question.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * PackageName : com.dglee.mini_prj.etc
 * FileName : SwaggerAPIController
 * Author : dglee
 * Create : 2022/11/29 12:34 AM
 * Description : Swagger API 응답을 위한 컨트롤러
 **/

@Tag(name = "board",description = "게시판 api")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SwaggerAPIController {

    private final QuestionService questionService;

    @Operation(summary = "get question list", description = "게시판 질문 리스트 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 넘버", example = "1"),
//            @Parameter(name = "city", description = "도", example = "고양시"),
//            @Parameter(name = "hashtag", description = "검색한 해시태그", example = "['#자장면', '#중국집']")
    })
    @ResponseBody
    @GetMapping( "/question/list")
    public Page<Question> getQuestions(
            @RequestParam(value = "page",defaultValue = "0") int page
//            @RequestParam(value = "city") String city,
//            @RequestParam(value = "hashtag", required = false) @Nullable String hashtag
    ) {
        Page<Question> paging = questionService.getList(page);
        return paging;
    }
}
