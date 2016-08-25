package ${package}.core;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import ${package}.utils.PropertiesUtil;

import redis.clients.jedis.JedisShardInfo;


@Configuration
public class JedisConfig {
  private static final 	Logger LOGGER = LoggerFactory.getLogger(JedisConfig.class);
 
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	JedisShardInfo info = null;
		try {
			String password = PropertiesUtil.getPropertyValue("spring.redis.password");
			String host= PropertiesUtil.getPropertyValue("spring.redis.host");
			int port = Integer.parseInt(PropertiesUtil.getPropertyValue("spring.redis.port"));
			int db =Integer.parseInt(PropertiesUtil.getPropertyValue("spring.redis.database"));
			
			info = new JedisShardInfo(new URI("redis://:"+password+"@"+host+":"+port+"/"+db));
		} catch (URISyntaxException e) {
			LOGGER.error(e.getMessage(),e);
		}
        return new JedisConnectionFactory(info);
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate< String, Object > template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        return template;
    }
}