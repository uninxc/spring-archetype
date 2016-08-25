package ${package}.service;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.me.core.ApplicationConstants;
import com.me.core.RedisClientWrapper;
import com.me.model.SessionModel;
import com.me.utils.CookieUtils;

import ${package}.core.ApplicationConstants;
import ${package}.core.RedisClientWrapper;
import ${package}.model.SessionModel;
import ${package}.utils.CookieUtils;

/**
 * Session 管理器，通过该类实现获取用户相关信息的功能
 */
public class SessionContextManager {

	private static volatile SessionContextManager manager = new SessionContextManager();
	
	private SessionContextManager(){}
	
	
	public static  SessionContextManager instance(){
		return SessionContextManager.manager;
	}
	
	public String getCurrentOpenId(){
		String cookieValue = CookieUtils.getCookieValue(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(), ApplicationConstants.OAUHT_COOKIE_NAME);
	
		Object  val = RedisClientWrapper.createInstance().get(ApplicationConstants.OAUHT_COOKIE_NAME + cookieValue);
		if(val != null){
			return ((SessionModel)val).getOpenId();
		}

		return null;
	}
	
	public void updateSessionModel(SessionModel sessionModel){
		String cookieValue = CookieUtils.getCookieValue(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(), ApplicationConstants.OAUHT_COOKIE_NAME);

		RedisClientWrapper.createInstance().set(ApplicationConstants.OAUHT_COOKIE_NAME + cookieValue, sessionModel,
				ApplicationConstants.XONE_COOKIE_AGE);
	}
	
	public SessionModel getSessionModel(){
		String cookieValue = CookieUtils.getCookieValue(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(), ApplicationConstants.OAUHT_COOKIE_NAME);
	
		Object  val = RedisClientWrapper.createInstance().get(ApplicationConstants.OAUHT_COOKIE_NAME + cookieValue);
		
		if(val != null){
			return ((SessionModel)val);
		}

		return new SessionModel();
	}
	
	public boolean isAuthenticated(){
		return true;
	}


	public String getCookieValue() {
		return  CookieUtils.getCookieValue(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(), ApplicationConstants.OAUHT_COOKIE_NAME);
	}
	
	
	
	
}
