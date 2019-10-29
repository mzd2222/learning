package learning.Config;


import learning.Bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.rmi.UnexpectedException;


//自定义一个序列化器，将复杂数据序列化后存入redis
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> EmpredisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnexpectedException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

//    @Bean
//    public RedisCacheManager EmpredisCacheManager(RedisTemplate<Object, Employee> EmpredisTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(EmpredisTemplate);
//        cacheManager.setTransactionAware(true);
//        return  cacheManager;
//    }

//    public RedisCacheConfiguration empRedisCacheConfiguration(RedisTemplate<Object, Employee> EmpredisTemplate){
//
//    }
}
