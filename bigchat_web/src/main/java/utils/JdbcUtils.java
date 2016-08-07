package utils;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Created by wang on 16-4-16.
 */
public class JdbcUtils extends UnpooledDataSourceFactory{
    private static SqlSessionFactory sqlSessionFactory = null;
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getConnection()
    {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
