package com.sh.mvc.board.model.service;

import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.board.model.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Map;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;

public class BoardService {

    private BoardDao boardDao = new BoardDao();

    public List<Board>findAll() {
        SqlSession session = getSqlSession();
        List<Board> boards = boardDao.findAll(session);
        session.close();
        return boards;
    }
    public BoardVo findById(long id, boolean hasRead) {
        SqlSession session = getSqlSession();
        BoardVo board = null;
        int result = 0;

        try {
            // 조회수 증가처리
            if (!hasRead) // 읽을때만
                result = boardDao.updateBoardReadCount(session, id);

            // 조회
            board = boardDao.findById(session, id);
            List<BoardComment> comments = boardDao.findCommentByBoardId(session, id);
            board.setComments(comments);

            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();

        }
        return board;
    }
    /**
     * 조회수 상관없이 게시글 조회해야하는 경우는 조회수를 안올려야하니까 (수정할때같은것)
     *
     * @param id
     * @return
     */
    public BoardVo findById(long id) {
       return findById(id, true);
    }

    /**
     * 트랜젝션 관리
     *
     * @param board
     * @return
     */
    public int insertBoard(BoardVo board) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            // board 테이블에 등록
            result = boardDao.insertBoard(session, board);
            System.out.println("BoardService#insertBoard : board#id = " + board.getId());


            // attachment 테이블에 등록
            List<Attachment> attachments = board.getAttachments();
            if (!attachments.isEmpty()) {
                for (Attachment attach : attachments) {
                    attach.setBoardId(board.getId()); // fk boardId 필드값 대입
                    result = boardDao.insertAttachment(session, attach);
                }
            }
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

        public int UpdateBoard(BoardVo board) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            // board테이블 수정
            result = boardDao.updateBoard(session, board);

            // atachment 테이블 삭제
            List<Long> delFiles = board.getDeFiles();
            if (!delFiles.isEmpty()) {
                for (Long id : delFiles) {
                    boardDao.deleteAttachment(session, id);
                }
            }

            // attachment 테이블 등록
            List<Attachment> attachments = board.getAttachments();
            if(!attachments.isEmpty()) {
                for (Attachment attach : attachments) {
                    attach.setBoardId(board.getId());
                    result = boardDao.insertAttachment(session, attach);
                }
            }

            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deleteBoard(long id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            result = boardDao.deleteBoard(session, id);
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public int totalCount() {
        SqlSession session = getSqlSession();
        int totalCount = boardDao.totalCount(session);
        session.close();
        return totalCount;

    }

    public List<BoardVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<BoardVo> boards = boardDao.findAll(session, param);
        session.close();
        return boards;
    }


    public int insertBoardComment(BoardComment comment) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.insertBoardComment(session, comment);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }
}
