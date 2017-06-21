package hello;

import static org.mockito.Mockito.calls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

import org.bson.Document;
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
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private QuestionRepository repository;
//	private MongoClient mongoClient;
//	private MongoDatabase database;
//	private MongoCollection<Document> collection;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {
//		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//		database = mongoClient.getDatabase("Stackoverflow");
//		collection = database.getCollection("questions");
		
		
		// save a couple of customers
//		repository.save(new Question("question1", "Smith", "34"));

		// fetch all customers
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
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
						+ "find ");
				break;
			case "get":
			case "Get":
				try {
					questions = stackexchange.getQuestions(new TimePeriod(new Date(2017, Integer.parseInt(inputparts[1]), Integer.parseInt(inputparts[2])),
							new Date(2017, Integer.parseInt(inputparts[3]), Integer.parseInt(inputparts[4]))));
				} catch (Exception e) {
					print(e.getMessage() + "\n" + e.getStackTrace());
				}
				break;
			case "find":
			case "Find":
				
				break;
			default:
				print("Type help or h to get instructions.");
			}
		}
	}

	private void addQuestions(List<QuestionDocument> questions) {
		repository.save(questions);
//		collection.insertMany(questions);
	}
	
	private void print(String message) {
		System.out.println(message);
	}
}
