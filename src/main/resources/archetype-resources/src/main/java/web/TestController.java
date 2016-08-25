package ${package}.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ${package}.service.TestService;

@Controller
public class TestController  {

	@Autowired
	private TestService testService;
	@RequestMapping("/sss")
	public String test(Model model){
		model.addAttribute("mongo",testService.getFromMongodbDemo("xing"));
		model.addAttribute("rds",testService.getFromRdsDemo("xing"));
		model.addAttribute("redis",testService.getFromRedisDemo("xing"));

		return "index";
		
	}


	
}