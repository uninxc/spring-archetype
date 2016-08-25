package ${package}.core;

import ${package}.utils.PropertiesUtil;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public class WxConfigBulider {
	
	private static  WxConfigBulider wxMpInMemoryConfigStorageBulider = new WxConfigBulider();
	private WxMpInMemoryConfigStorage config ;
	private WxConfigBulider(){
		config = new WxMpInMemoryConfigStorage();
		config.setAppId(PropertiesUtil.getPropertyValue("wx.app.id")); // 设置微信公众号的appid
		config.setSecret(PropertiesUtil.getPropertyValue("wx.secret")); // 设置微信公众号的app corpSecret
		config.setToken(PropertiesUtil.getPropertyValue("wx.token")); // 设置微信公众号的token
		config.setAesKey(PropertiesUtil.getPropertyValue("wx.aes.key")); // 设置微信公众号的EncodingAESKey
		config.setOauth2redirectUri(PropertiesUtil.getPropertyValue("wx.oauth.redirect"));

	}
	
	public static WxConfigBulider instance(){
		return wxMpInMemoryConfigStorageBulider;
	}
	
	public WxMpConfigStorage  build(){
		return config;
	}
	
}
