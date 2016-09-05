package ${package}.model;

import org.springframework.data.annotation.Id;


import lombok.Data;
@Data
public class Test {
	@Id
	private String id;

	private String firstName;
	private String lastName;

	public Test() {
	}

	public Test(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Test[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);
	}
}
