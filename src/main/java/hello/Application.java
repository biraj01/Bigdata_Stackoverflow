package hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
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
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String input = "";
//		String[] inputparts = new String[10];
		List<Question> questions = new PagedArrayList<>();
		
		if (args.length != 0) {
			switch (args[0]) {
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
				print("Getting data from stackoverflow.com");
				try {
					questions = stackexchange.getQuestions(new TimePeriod(new Date(2017, Integer.parseInt(args[1]), Integer.parseInt(args[2])),
							new Date(2017, Integer.parseInt(args[3]), Integer.parseInt(args[4]))));
				} catch (Exception e) {
					print(e.getMessage() + "\n" + e.getStackTrace());
				}
				saveQuestions(questions);
				break;
			case "find":
			case "Find":
				print("Searching for data...");
				break;
			default:
				print("Type 'help' or 'h' to get instructions.");
			}
		}
		
		
//		while (true) {
//			input = reader.readLine();
//			if (input != null && !input.isEmpty()) {
//				inputparts = input.split(" ");
//				
//			}
//			if (input != null && input.equals("exit")) {
//				break;
//			}
//		}
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
