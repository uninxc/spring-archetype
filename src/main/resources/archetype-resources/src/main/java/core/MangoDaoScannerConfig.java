package ${package}.core;

import java.util.ArrayList;
import java.util.List;

import org.jfaster.mango.plugin.spring.MangoDaoScanner;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MangoMapperConfig.class)
public class MangoDaoScannerConfig {
	@Bean
	public MangoDaoScanner mangoDaoScanner() {

		List<String> list = new ArrayList<>();
		
		// 这个无需从配置文件中读取
		list.add("${package}.dao");

		MangoDaoScanner scanner = new MangoDaoScanner();
		scanner.setPackages(list);
		return scanner;
	}

}
