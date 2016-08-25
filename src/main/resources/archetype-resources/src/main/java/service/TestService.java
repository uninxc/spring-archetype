package ${package}.service;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTerm;
import org.jfaster.mango.transaction.Transaction;
import org.jfaster.mango.transaction.TransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import ${package}.core.RedisClientWrapper;
import ${package}.dao.TestDao;
import ${package}.dao.TestMongoDbDao;
import ${package}.model.Test;


@Service
public class TestService  {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestService.class);
//	
	@Autowired  

	private TestMongoDbDao mongoDbDao;
	
	@Autowired  
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private TestDao dao;
	
	public String getFromRedisDemo(String key){
		RedisClientWrapper.createInstance().set("abc", "efg");
		
		return 	RedisClientWrapper.createInstance().getString("abc");
	}
	public String getFromRdsDemo(String key){
		
		return Integer.toString(dao.selctCount());
	}
	
	public String getFromMongodbDemo(String key){
		Test test = new Test("xing","baichao");
		test = mongoDbDao.insert(test);
		LOGGER.info("insert ok ..."+test.getId());
		
		return mongoDbDao.findByFirstName("xing").getLastName();
	}
}