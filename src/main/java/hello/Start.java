package hello;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.code.stackexchange.client.StackExchangeApiClient;
import com.google.code.stackexchange.client.impl.StackExchangeApiJsonClient;
import com.google.code.stackexchange.common.PagedArrayList;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.TimePeriod;

public class Start {

	@Autowired
	private QuestionRepository repository;
	private UI ui;
	
	public Start() {
		try {
			run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void run() throws Exception {
		StackExchangeApiClient stackexchange = new StackExchangeApiJsonClient("Iy3STd4JiwI4lMyY5GMB*Q((", StackExchangeSite.STACK_OVERFLOW);
		ui = new UI();
		ui.inputField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					print("Im here!");
					ui.setOutputText("haste richtig gemacht.");
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
//		JFrame uiFrame = new JFrame("BigData-Stackoverflow");
//		uiFrame.setSize(1000, 700);
//		uiFrame.setResizable(false);
//		
//		JLabel labelIn = new JLabel("Input");
//		JTextField textIn = new JTextField(1);
//		labelIn.add(textIn);
//		labelIn.setVisible(true);
//		uiFrame.add(labelIn);
//		textIn.setAlignmentX(20);
//		textIn.setAlignmentY(20);
//		
//		JLabel labelOut = new JLabel("Output");
//		JTextArea textOut = new JTextArea(10,1);
//		labelOut.setVisible(true);
//		labelOut.add(textOut);
//		uiFrame.add(labelOut);
//		textOut.setAlignmentX(80);
//		textOut.setAlignmentY(20);
//		
//		uiFrame.setVisible(true);
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		String[] inputparts = new String[10];
		List<Question> questions = new PagedArrayList<>();
		
		if (1 != 0) {
			switch (input) {
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
					questions = stackexchange.getQuestions(new TimePeriod(new Date(2017, Integer.parseInt("0"), Integer.parseInt("0")),
							new Date(2017, Integer.parseInt("0"), Integer.parseInt("0"))));
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
