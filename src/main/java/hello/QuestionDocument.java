package hello;

import java.util.List;

import org.bson.Document;
import org.springframework.data.annotation.Id;

import com.google.code.stackexchange.schema.Answer;

@org.springframework.data.mongodb.core.mapping.Document(collection = "question")
public class QuestionDocument extends Document {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320645581722813226L;

	@Id
    public String id;

	public long questionId;
    public String owner;
    public long viewCount;
    public List<Answer> answers;
    
    public QuestionDocument() {}
    
    public QuestionEntity toEntity() {
    	QuestionEntity question = new QuestionEntity();
    	question.id = this.id;
    	question.questionId = this.questionId;
    	question.owner = this.owner;
    	question.viewCount = this.viewCount;
    	question.answers = this.answers;
    	return question;
    }
}
