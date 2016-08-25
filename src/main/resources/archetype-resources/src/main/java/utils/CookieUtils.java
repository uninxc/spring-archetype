package ${package}.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {


	public static void setCookie(HttpServletResponse response, String name, String value, String domain, int maxAge) {
		if (value == null) {
			value = "";
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		if (domain != null && !"".equals(domain)) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		if (cookieName == null || request == null) {
			return null;
		}
		Cookie[] cks = request.getCookies();
		if (cks == null) {
			return null;
		}
		for (Cookie cookie : cks) {
			if (cookieName.equals(cookie.getName()))
				return cookie.getValue();
		}
		return null;
	}


	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		if (cookieName == null || request == null) {
			return null;
		}
		Cookie[] cks = request.getCookies();
		if (cks == null) {
			return null;
		}
		for (Cookie cookie : cks) {
			if (cookieName.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}
}