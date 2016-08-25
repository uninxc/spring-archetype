package ${package}.core;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import lombok.val;

@Service
public class RedisClientWrapper {

	
	private static final  Logger  LOGGER = LoggerFactory.getLogger(RedisClientWrapper.class);
	
	@Autowired
	private RedisTemplate<String, Object> template;

	@SuppressWarnings("unchecked")
	public static RedisClientWrapper createInstance(){
		RedisClientWrapper clientWrapper = new RedisClientWrapper();
		clientWrapper.setTemplate((RedisTemplate<String, Object>) ContextUtils.getContext().getBean("redisTemplate"));
		return clientWrapper;
	}
	
	public void set(String key, String value) {
		getTemplate().opsForValue().set(key, value);
	}

	public void set(String key, Object value, long seconds) {
		getTemplate().opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
	}

	public Object get(String string) {
		return getTemplate().opsForValue().get(string);

	}
	
	public String getString(String string) {
		Object object = getTemplate().opsForValue().get(string);
	
		return object == null?null:object.toString();

	}

	public void leftPush(String key,String item) {
		getTemplate().opsForList().leftPush(key, item);
	}
	
	public void delete(String string) {
		getTemplate().opsForValue().getOperations().delete(string);
		
	}
	
	public void lock(String key){
		while (true) {
			boolean result = getTemplate().opsForValue().setIfAbsent(key, String.valueOf(System.currentTimeMillis()+1000));
			if(result || (System.currentTimeMillis() > (Long.valueOf(getTemplate().opsForValue().get(key).toString())) && System.currentTimeMillis() > Long.valueOf(getTemplate().opsForValue().getAndSet(key,String.valueOf(System.currentTimeMillis()+1000)).toString()))){
				break;
			}else{
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					LOGGER.error(e.getMessage(),e);			
				}
			}	
		}
	}
	
	public void releaseLock(String key){
		getTemplate().delete(key);
	}

	public Object getAndDelete(String key) {
		Object v = this.get(key);
		this.delete(key);
		return v;
	}

	public RedisTemplate<String, Object> getTemplate() {
		return template;
	}

	public void setTemplate(RedisTemplate<String, Object> stringRedisTemplate) {
		this.template = stringRedisTemplate;
	}
	
	

}
