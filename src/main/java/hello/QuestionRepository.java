package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
@Qualifier("questionRepository")
public interface QuestionRepository extends MongoRepository<QuestionDocument, String>{
	
	/**
	 * Finds a question by id.
	 * @param questionId Id from Stackoverflow
	 * @return QuestionDocument
	 */
	public QuestionDocument findByQuestionId(String questionId);
	
	/**
	 * Find questions from one owner.
	 * @param owner Name of the owner
	 * @return List<QuestionDocument>
	 */
	public List<QuestionDocument> findByOwner(String owner);
}
