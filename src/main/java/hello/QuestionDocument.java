package hello;

import org.bson.Document;
import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document(collection = "question")
public class QuestionDocument extends Document {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320645581722813226L;

	@Id
    public String id;

	public String questionId;
    public String owner;
    public String viewCount;
    
    public QuestionDocument() {}
    
    public QuestionEntity toEntity() {
    	QuestionEntity question = new QuestionEntity();
    	question.id = this.id;
    	question.questionId = this.questionId;
    	question.owner = this.owner;
    	question.viewCount = this.viewCount;
    	return question;
    }
}
