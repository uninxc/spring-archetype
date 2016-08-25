package ${package}.core;

import javax.sql.DataSource;

import org.jfaster.mango.datasource.DataSourceFactory;
import org.jfaster.mango.datasource.SimpleDataSourceFactory;
import org.jfaster.mango.operator.Mango;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class MangoMapperConfig implements EnvironmentAware {


	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Override
	public void setEnvironment(Environment environment) {
		new RelaxedPropertyResolver(environment, "mango.");
	}
	
	
	/**
	 * 以后根据实际情况，变更 DataSourceFactory 实现类，比如读写分离、分库分表等！
	 * @return
	 */
	@Bean
	public DataSourceFactory dsf(){
		return new SimpleDataSourceFactory(primaryDataSource());
	}
	
	@Bean
	public Mango mango(){
		return Mango.newInstance().setDataSourceFactory(dsf());
	}
	
	

}
