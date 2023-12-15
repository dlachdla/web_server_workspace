package com.sh.mvc.board;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardServiceTest {

    private BoardService boardService;

    @BeforeEach
    public void beforeEach() {this.boardService = new BoardService();}

    @DisplayName("게시글 전체조회")
    @Test
    public void test1() {
        List<Board> members = boardService.findAll();
        assertThat(members).isNotNull().isNotEmpty();

            members.forEach((board) -> {
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
        int id = 36;
        Board member = boardService.findById(id);
        System.out.println(member);

        assertThat(member).isNotNull();

        assertThat(member.getId()).isGreaterThan(1);
        assertThat(member.getTitle()).isNotNull();
        assertThat(member.getMemberId()).isNotNull();
        assertThat(member.getContent()).isNotNull();
        assertThat(member.getReadCount()).isGreaterThanOrEqualTo(0);
        assertThat(member.getRegDate()).isNotNull();

    }

    @Disabled
    @DisplayName("게시글 등록")
    @Test
    public void test3(){
        String title = "안녕하세요, 게시판입니다";
        String memmberId = "sinsa";
        String content = "게시글등록했습니다";


        Board board = new Board(0, title, memmberId, content, 0, null);
        int result = boardService.insertBoard(board);
        assertThat(result).isGreaterThan(0);

        Board board1 = boardService.findById(61);
        assertThat(board1.getId()).isGreaterThan(0);
        assertThat(board1.getTitle()).isNotNull();
        assertThat(board1.getMemberId()).isNotNull();
        assertThat(board1.getContent()).isNotNull();
        assertThat(board1.getReadCount()).isGreaterThanOrEqualTo(0);
        assertThat(board1.getRegDate()).isNotNull();
    }

    @Disabled
    @DisplayName("게시글 수정")
    @Test
    public void test4(){
        int id = 61;
        Board board = boardService.findById(id);
        String newContent = "게시글을수정했습니다";
        board.setContent(newContent);

        int result = boardService.UpdateBoard(board);
        assertThat(result).isGreaterThan(0);

        Board board1 = boardService.findById(id);
        assertThat(board1.getContent()).isEqualTo(newContent);

    }

    @Disabled
    @DisplayName("게시글 삭제")
    @Test
    public void test5(){
        int id = 61;
        Board board = boardService.findById(id);
        assertThat(board).isNotNull();

        int result = boardService.deleteBoard(id);
        assertThat(result).isGreaterThan(0);

        Board board1 = boardService.findById(id);
        assertThat(board1).isNull();
    }

}
