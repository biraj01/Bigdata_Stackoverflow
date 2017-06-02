package hello;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.code.stackexchange.schema.Tag;

@Document
public class Question {
	
	@Id
    public String id;

	public  String questionId;
    public String owner;
    public String viewCount;
    
    public Question(String questionId, String owner, String viewCount){
    	
    	this.questionId =questionId;
    	this.owner = owner;
    	this.viewCount = viewCount;
    }

}
