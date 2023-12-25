package com.sh.mvc.board.service;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.common.HelloMvcUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardServiceTest {

    private BoardService boardService;

    @BeforeEach
    public void beforeEach() {this.boardService = new BoardService();}

    @DisplayName("게시글 전체조회")
    @Test
    public void test1() {
        List<Board> boards = boardService.findAll();
        System.out.println(boards);
        assertThat(boards).isNotNull().isNotEmpty();

        boards.forEach((board) -> {
            System.out.println(board);
            assertThat(board.getId()).isGreaterThan(0);
            assertThat(board.getTitle()).isNotNull();
            assertThat(board.getMemberId()).isNotNull();
            assertThat(board.getContent()).isNotNull();
            assertThat(board.getReadCount()).isGreaterThanOrEqualTo(0);
            assertThat(board.getRegDate()).isNotNull();
        });
    }

    @DisplayName("게시글 한건조회")
    @Test
    public void test2(){
        long id = 56;
        BoardVo board = boardService.findById(id);
        System.out.println(board);

        assertThat(board).isNotNull();

        assertThat(board.getId()).isGreaterThan(1);
        assertThat(board.getTitle()).isNotNull();
        assertThat(board.getMemberId()).isNotNull();
        assertThat(board.getContent()).isNotNull();
        assertThat(board.getReadCount()).isGreaterThanOrEqualTo(0);
        assertThat(board.getRegDate()).isNotNull();

    }

//    @Disabled
    @DisplayName("게시글 등록")
    @Test
    public void test3(){
        String title = "안녕하세요, 게시판입니다";
        String memmberId = "sinsa";
        String content = "게시글등록했습니다";

        BoardVo board = new BoardVo();
        board.setTitle("제목");
        board.setContent("내용");

        int result = boardService.insertBoard(board);
        assertThat(result).isGreaterThan(0);

    }

//    @Disabled
    @DisplayName("게시글 수정")
    @Test
    public void test4(){
        int id = 81;
        BoardVo board = boardService.findById(id);
        String newContent = "게시글을수정했습니다";
        String newTitle = "안녕하세요. 제목수정했습니다";
        board.setContent(newContent);
        board.setTitle(newTitle);

        int result = boardService.UpdateBoard(board);
        assertThat(result).isGreaterThan(0);

        Board board1 = boardService.findById(id);
        assertThat(board1.getContent()).isEqualTo(newContent);
        assertThat(board1.getTitle()).isEqualTo(newTitle);

    }

    @Disabled
    @DisplayName("게시글 삭제")
    @Test
    public void test5(){
        long id = 57;
        BoardVo board = boardService.findById(id);
        System.out.println(board);
        assertThat(board).isNotNull();

        int result = boardService.deleteBoard(id);
        assertThat(result).isGreaterThan(0);
        BoardVo board1 = boardService.findById(id);
        assertThat(board1).isNull();
    }

    @DisplayName("전체게시글수 조회")
    @Test
    public void test6(){
        int result = boardService.totalCount();
        assertThat(result).isGreaterThan(0);
        System.out.println(result);

    }

    @DisplayName("게시글 페이징조회")
    @Test
    public void test7() {
        int page = 1;
        int limit = 5;
        Map<String, Object> param = Map.of("page", page, "limit", limit);

        List<BoardVo> boards = boardService.findAll(param);
        assertThat(boards).isNotNull().isNotEmpty();

        boards.forEach((board) -> {
            System.out.println(board);
            assertThat(board.getId()).isGreaterThan(0);
            assertThat(board.getTitle()).isNotNull();
            assertThat(board.getMemberId()).isNotNull();
            assertThat(board.getContent()).isNotNull();
            assertThat(board.getReadCount()).isGreaterThanOrEqualTo(0);
            assertThat(board.getRegDate()).isNotNull();
        });

        int totalCount = boardService.totalCount();
        String url = "/mvc/board/boardList";
        String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
        assertThat(pagebar).isNotNull();

    }
}
