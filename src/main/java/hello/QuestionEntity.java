package hello;

import org.springframework.data.annotation.Id;

import com.google.code.stackexchange.schema.Answer;

import java.util.List;

import org.bson.Document;

public class QuestionEntity {

	@Id
    public String id;

	public long questionId;
    public String owner;
    public long viewCount;
    public List<Answer> answers;
    
    public QuestionEntity() {}
    
    public Document toDocument() {
    	return new QuestionDocument()
    			.append("_id", this.id)
    			.append("questionId", this.questionId)
    			.append("owner", this.owner)
    			.append("viewCount", this.viewCount)
    			.append("Answers", answers);
    }
}
