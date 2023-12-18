package com.sh.mvc.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
/**
 * SqlSessionFactoryBuilder
 * SqlSessionFactory
 * SqlSession
 */
public class SqlSessionTemplate {

    private static SqlSessionFactory factory;

    static {
        String resource = "mybatis-config.xml"; // src/main/resources/mybatis-config.xml

        try (InputStream is = Resources.getResourceAsStream(resource)){
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // mybatis 에서는 DB에 연동하는 Connection객체를 SqlSession이라 부른다.
    // getSqlSession의 역할 - XML 파일을 연결하여 XML 파일 정보를 바탕으로 DB와 연결하고,
    // 해당 Connection(SqlSession)을 리턴하는 역할
    public static SqlSession getSqlSession() {
        // openSession 이라는메소드는 세션을 가져오는 메소드
        return factory.openSession(false); // false : AutoCommit 비활성화
    }
}