package hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
@Qualifier("questionRepository")
public interface QuestionRepository extends MongoRepository<Question, String>{
	

}
