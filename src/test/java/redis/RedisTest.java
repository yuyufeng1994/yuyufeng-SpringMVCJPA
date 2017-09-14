package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationRedis.xml"})
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name="redisTemplate")
    private ValueOperations<String, List<String>> valueOs;

    @Test
    public void testoGet() {

        List<String> lists = valueOs.get("qq");
        for (String list : lists) {
            System.out.println(list);
        }
    }


    @Test
    public void testoSet() {
        List<String> lists = new ArrayList<>();
        lists.add("aa");
        lists.add("bb");
        valueOs.set("qq",lists);
    }

    @Test
    public void testGet() {

        String result = template.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] value = redisConnection.get("aa".getBytes());
                String result = template.getStringSerializer().deserialize(value);
                return result;
            }
        });

        System.out.println(result);
    }

    @Test
    public void testSet() {

        String result = template.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(template.getStringSerializer().serialize("date"), template.getStringSerializer().serialize("cc"));
                return null;
            }
        });

        System.out.println(result);
    }


}

/*
*
* ValueOperations：简单K-V操作
SetOperations：set类型数据操作
ZSetOperations：zset类型数据操作
HashOperations：针对map类型的数据操作
ListOperations：针对list类型的数据操作
* */