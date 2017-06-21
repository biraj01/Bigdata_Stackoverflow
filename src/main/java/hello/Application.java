package hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.code.stackexchange.client.StackExchangeApiClient;
import com.google.code.stackexchange.client.impl.StackExchangeApiJsonClient;
import com.google.code.stackexchange.common.PagedArrayList;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.TimePeriod;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private QuestionRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {
		StackExchangeApiClient stackexchange = new StackExchangeApiJsonClient("Iy3STd4JiwI4lMyY5GMB*Q((", StackExchangeSite.STACK_OVERFLOW);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		String[] inputparts = new String[1];
		List<Question> questions = new PagedArrayList<>();
		print("Grab Data from stackoverflow.com, sign in:");
		
		while (!(input = reader.readLine()).equals("exit")) {
			inputparts = input.split(" ");
			switch (inputparts[0]) {
			case "h":
			case "H":
			case "help":
			case "Help":
				print("Type either:\nget Number-Month-From Number-Day-From Number-Month-To Number-Day-To\n"
						+ "find "
						+ "'exit' to end the programm.");
				break;
			case "get":
			case "Get":
				try {
					questions = stackexchange.getQuestions(new TimePeriod(new Date(2017, Integer.parseInt(inputparts[1]), Integer.parseInt(inputparts[2])),
							new Date(2017, Integer.parseInt(inputparts[3]), Integer.parseInt(inputparts[4]))));
				} catch (Exception e) {
					print(e.getMessage() + "\n" + e.getStackTrace());
				}
				saveQuestions(questions);
				break;
			case "find":
			case "Find":
				
				break;
			default:
				print("Type 'help' or 'h' to get instructions.");
			}
		}
	}
	
	/**
	 * Saves questions to the Database.
	 * @param questions List<Question>
	 */
	private void saveQuestions(List<Question> questions) {
		QuestionDocument questionDocument = new QuestionDocument();
		for (Question question : questions) {
			questionDocument.questionId = question.getQuestionId();
			questionDocument.owner = question.getOwner().toString();
			questionDocument.viewCount = question.getViewCount();
			questionDocument.answers = question.getAnswers();
			repository.save(questionDocument);
		}
	}
	
	/**
	 * Console print.
	 * @param message String
	 */
	private void print(String message) {
		System.out.println(message);
	}
}
