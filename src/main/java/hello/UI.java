package hello;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import org.springframework.core.io.support.SpringFactoriesLoader;

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
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setLocation(200, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout layout = new SpringLayout();
		
		Container contentPane = frame.getContentPane();
        contentPane.setLayout(layout);
        contentPane.setSize(800, 600);
        
        JLabel lblInput = new JLabel("Input:");
		lblInput.setBounds(230, 16, 41, 16);
		frame.getContentPane().add(lblInput);
		//Adjust constraints for the label so it's at (10,10).
		layout.putConstraint(SpringLayout.WEST, lblInput,
		                     10,
		                     SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, lblInput,
		                     10,
		                     SpringLayout.NORTH, contentPane);
		
		inputField = new JTextField();
		inputField.setBounds(283, 13, 116, 22);
		inputField.setToolTipText("Type commands, then enter");
		inputField.setEditable(true);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);
		//Adjust constraints for the text field so it's at
		//(<label's right edge> + 10, 10).
		layout.putConstraint(SpringLayout.WEST, inputField,
		                     10,
		                     SpringLayout.EAST, lblInput);
		layout.putConstraint(SpringLayout.NORTH, inputField,
		                     10,
		                     SpringLayout.NORTH, contentPane);
		
		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setBounds(12, 32, 56, 16);
		frame.getContentPane().add(lblOutput);
		//Adjust constraints for the label so it's at (10,10).
				layout.putConstraint(SpringLayout.WEST, lblOutput,
				                     10,
				                     SpringLayout.WEST, contentPane);
				layout.putConstraint(SpringLayout.NORTH, lblOutput,
				                     30,
				                     SpringLayout.NORTH, lblInput);
		
		outputTextPane = new JTextPane();
//		outputTextPane.setBounds(12, 59, 408, 181);
		frame.getContentPane().add(outputTextPane);
		//Adjust constraints for the label so it's at (10,10).
				layout.putConstraint(SpringLayout.WEST, outputTextPane,
				                     50,
				                     SpringLayout.WEST, lblOutput);
				layout.putConstraint(SpringLayout.NORTH, outputTextPane,
				                     20,
				                     SpringLayout.NORTH, inputField);
		
		frame.pack();
		frame.setVisible(true);
	}
}
