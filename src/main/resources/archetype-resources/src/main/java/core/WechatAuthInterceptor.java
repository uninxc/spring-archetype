package ${package}.core;


import java.util.UUID;
import ${package}.utils.CookieUtils;
import ${package}.model.SessionModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

public class WechatAuthInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisClientWrapper redisService;


	private static final Logger LOGGER = LoggerFactory.getLogger(WechatAuthInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURI();
		if (url.startsWith("/wx")) {
			String cookieValue = CookieUtils.getCookieValue(request, ApplicationConstants.OAUHT_COOKIE_NAME);
			System.out.println("url:"+url+"--->cookieValue:---" + cookieValue);
			if (StringUtils.isBlank(cookieValue) || StringUtils
					.isBlank(redisService.getString(ApplicationConstants.OAUHT_COOKIE_NAME + cookieValue))) {
				WxMpService wxService = new WxMpServiceImpl();

				SessionModel model = new SessionModel();
				model.setReturnUrl(url);

				String random = UUID.randomUUID().toString();
				redisService.set(ApplicationConstants.OAUHT_COOKIE_NAME + random, model,
						ApplicationConstants.XONE_COOKIE_AGE + 60 * 60);
				CookieUtils.setCookie(response, ApplicationConstants.OAUHT_COOKIE_NAME, random, null,
						ApplicationConstants.XONE_COOKIE_AGE);

				wxService.setWxMpConfigStorage(WxConfigBulider.instance().build());
				String urlWx = wxService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_USER_INFO, null);
				LOGGER.info(urlWx);
				response.sendRedirect(urlWx);
				return false;
			}
			return true;
		}

		return true;

	}

}
