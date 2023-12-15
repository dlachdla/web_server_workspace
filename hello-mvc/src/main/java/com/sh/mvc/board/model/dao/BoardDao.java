package com.sh.mvc.board.model.dao;

import com.sh.mvc.board.model.entity.Board;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BoardDao {
    public List<Board> findAll(SqlSession session) {
        return session.selectList("board.findAll");
    }

    public Board findById(SqlSession session, int id) {
        return session.selectOne("board.findById", id);
    }

    public int insertBoard(SqlSession session, Board board) {
        return session.insert("board.insertBoard", board);
    }

    public int updateBoard(SqlSession session, Board board) {
        return session.update("board.updateBoard", board);
    }

    public int deleteBoard(SqlSession session, int id) {
        return session.delete("board.deleteBoard", id);
    }
}
