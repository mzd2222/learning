package learning;

import learning.Bean.Employee;
import learning.Mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class LearningApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //专门操作string

    @Autowired
    RedisTemplate redisTemplate;  //操作键值对，都是object

    /**
     * String,List,Set集合,Hash散列,ZSet有序集合
     * Redis常见五大数据类型
     * stringRedisTemplate.opsForValue();简化操作字符串
     * stringRedisTemplate.opsForList();
     * stringRedisTemplate.opsForHash();  XXXXXXXX
     * stringRedisTemplate.opsForSet();  XXXXXXXX
     *
     *
     *
     */

    @Test
    void test01(){
        //保存信息
//        stringRedisTemplate.opsForValue().append("msg","hello world");
//        String s = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(s);
        stringRedisTemplate.opsForList().leftPush("myList", "1");
        stringRedisTemplate.opsForList().leftPush("myList", "2");
        stringRedisTemplate.opsForList().leftPush("myList", "3");
    }

    @Test
    void contextLoads() {
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
    }

}
