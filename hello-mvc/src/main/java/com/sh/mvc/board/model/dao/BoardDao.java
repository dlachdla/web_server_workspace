package com.sh.mvc.board.model.dao;

import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.vo.BoardVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class BoardDao {
    public List<Board> findAll(SqlSession session) {
        return session.selectList("board.findAll",session);
    }

    public BoardVo findById(SqlSession session, long id) {
        return session.selectOne("board.findById", id);
    }

    public int insertBoard(SqlSession session, Board board) {
        return session.insert("board.insertBoard", board);
    }

    public int updateBoard(SqlSession session, Board board) {
        return session.update("board.updateBoard", board);
    }

    public int deleteBoard(SqlSession session, long id) {
        return session.delete("board.deleteBoard", id);
    }

    public int totalCount(SqlSession session) {
        return session.selectOne("board.totalCount", session);
    }

    public List<BoardVo> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");

        // 건너뛸 회원수
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("board.findAllPage", param, rowBounds);

    }

    public int insertAttachment(SqlSession session, Attachment attach) {
        return session.insert("board.insertAttachment", attach);
    }

    public int updateBoardReadCount(SqlSession session, long id) {
        return session.update("board.updateBoardReadCount", id);
    }
}
