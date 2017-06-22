package hello;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

public class UI {

	private JFrame frame;
	protected JTextField inputField;
	protected JTextPane outputTextPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UI window = new UI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("BigData Stackoverflow");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout layout = new SpringLayout();
		frame.getContentPane().setLayout(layout);
		
		inputField = new JTextField();
		inputField.setBounds(283, 13, 116, 22);
		inputField.setToolTipText("Type commands, then enter");
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);
		inputField.setVisible(true);
		
		JLabel lblInput = new JLabel("Input:");
		lblInput.setBounds(230, 16, 41, 16);
		lblInput.setVisible(true);
		frame.getContentPane().add(lblInput);
		
		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setBounds(12, 32, 56, 16);
		lblOutput.setVisible(true);
		frame.getContentPane().add(lblOutput);
		
		outputTextPane = new JTextPane();
		outputTextPane.setBounds(12, 59, 408, 181);
		outputTextPane.setVisible(true);
		frame.getContentPane().add(outputTextPane);
	}
}
