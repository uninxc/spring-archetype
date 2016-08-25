package ${package}.model;


import java.io.Serializable;

import lombok.Data;

@Data
public class SessionModel implements Serializable{
	
	private String openId;
	private String returnUrl;

}
