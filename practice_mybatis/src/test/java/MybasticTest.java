import com.practice.mybatis.User;
import com.practice.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybasticTest {

    @Test
    public void insertData() throws IOException {
        SqlSession sqlSession = getSqlsession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = new ArrayList<User>();
        list.add(new User("ceshi1", "1112333", "1837@qq.ocm"));
        list.add(new User("ceshi2", "111fsihe44", "18er34@qq.ocm"));
        list.add(new User("ceshi3", "11vsd444", "183t4@qq.ocm"));
        list.add(new User("ceshi4", "1114svd4", "183re4@qq.ocm"));
        mapper.createMany(list);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testLimitPage() throws IOException {
        SqlSession sqlSession = getSqlsession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startIndex", 0);
        map.put("pageSize", 2);
        List<User> list = mapper.selectLimit(map);
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    private SqlSession getSqlsession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSessionFactory对象，用它执行sql
        return sqlSessionFactory.openSession();
    }
}
