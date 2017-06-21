package hello;

import org.springframework.data.annotation.Id;
import org.bson.Document;

public class QuestionEntity {

	@Id
    public String id;

	public String questionId;
    public String owner;
    public String viewCount;
    
    public QuestionEntity(String questionId, String owner, String viewCount) {
    	
    	this.questionId =questionId;
    	this.owner = owner;
    	this.viewCount = viewCount;
    }
    
    public QuestionEntity() {}
    
    public Document toDocument() {
    	return new QuestionDocument()
    			.append("_id", this.id)
    			.append("questionId", this.questionId)
    			.append("owner", this.owner)
    			.append("viewCount", this.viewCount);
    }
}
