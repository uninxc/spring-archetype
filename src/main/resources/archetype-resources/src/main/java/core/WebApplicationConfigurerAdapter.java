package ${package}.core;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebApplicationConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	@Bean
	public WechatAuthInterceptor authInterceptor() {
	    return new WechatAuthInterceptor();
	}

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	     registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
		  super.addInterceptors(registry);
	}
}
