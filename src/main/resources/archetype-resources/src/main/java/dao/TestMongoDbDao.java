package ${package}.dao;
import org.springframework.data.mongodb.repository.MongoRepository;

import ${package}.model.Test;

import java.util.List;

public interface  TestMongoDbDao  extends MongoRepository<Test, String>{
	
	 public Test findByFirstName(String firstName);
	 
	 public List<Test> findByLastName(String lastName);
	 
}
