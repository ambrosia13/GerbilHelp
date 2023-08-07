package com.ambrosia.gerbilhelp.window;

import com.ambrosia.gerbilhelp.internal.RequestResult;
import com.ambrosia.gerbilhelp.search.Search;
import com.ambrosia.gerbilhelp.search.parse.ResultParser;

import javax.swing.*;
import java.awt.*;

public class Window {
	private static final class Colors {
		public static final Color background = new Color(237, 211, 196);
		public static final Color answerText = new Color(220, 193, 170);
	}

	private final JFrame frame;
	private JTextPane answerText;

	private Search search;

	public Window() {
		frame = new JFrame("Gerbil Help");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setPreferredSize(new Dimension(800, 800));
		frame.pack();
		frame.setResizable(false);

		search = new Search("", "", "");

		this.config();
	}

	private void setSearchSubject(String subject) {
		this.search = new Search(subject, search.topic(), search.question());
	}
	private void setSearchTopic(String topic) {
		this.search = new Search(search.subject(), topic, search.question());
	}
	private void setSearchQuestion(String question) {
		this.search = new Search(search.subject(), search.topic(), question);
	}

	private void config() {
		final var title = new JLabel("Gerbil Help", SwingConstants.CENTER);
		this.frame.getContentPane().add(title, BorderLayout.BEFORE_FIRST_LINE);

		final var panel = new JPanel();
		panel.setBackground(Colors.background);

		panel.setLayout(null);
		this.frame.add(panel);

		// Size constants for the different elements
		final int buttonWidth = 100;
		final int buttonHeight = 30;

		final int labelWidth = 600;
		final int labelHeight = 15;

		final int textBoxSizeX = 460;
		final int textBoxSizeY = 30;

		var layout = new CustomLayout(20, 20, 20, 20);

		// Add the subject instruction label
		var subjectLabel = (JLabel) layout.manage(new JLabel("What subject do you need help with?"), labelWidth, labelHeight, true, false);
		layout.nextLineY(labelHeight);

		panel.add(subjectLabel);

		// Add buttons to select the subject
		var math = (JButton) layout.manage(new JButton("Math"), buttonWidth, buttonHeight, true, false);
		var english = (JButton) layout.manage(new JButton("English"), buttonWidth, buttonHeight, true, false);
		var science = (JButton) layout.manage(new JButton("Science"), buttonWidth, buttonHeight, true, false);
		var history = (JButton) layout.manage(new JButton("History"), buttonWidth, buttonHeight, true, false);
		var computerScience = (JButton) layout.manage(new JButton("Comp. Sci"), buttonWidth, buttonHeight, true, false);

		layout.nextLineY(buttonHeight);

		panel.add(math);
		panel.add(english);
		panel.add(science);
		panel.add(history);
		panel.add(computerScience);

		math.addActionListener(e -> setSearchSubject("Math"));
		english.addActionListener(e -> setSearchSubject("English"));
		science.addActionListener(e -> setSearchSubject("Science"));
		history.addActionListener(e -> setSearchSubject("History"));
		computerScience.addActionListener(e -> setSearchSubject("Computer Science"));

		// Add the topic instruction label
		var topicLabel = (JLabel) layout.manage(new JLabel("What topic are you confused about?"), labelWidth, labelHeight, true, false);
		layout.nextLineY(labelHeight);

		panel.add(topicLabel);

		// Add the text box for the topic
		var topic = (JTextField) layout.manage(new JTextField(), textBoxSizeX, textBoxSizeY, true, false);
		var topicDoneButton = (JButton) layout.manage(new JButton("Done"), buttonWidth, buttonHeight, true, false);

		layout.nextLineY(textBoxSizeY);

		topicDoneButton.addActionListener(e -> {
			setSearchTopic(topic.getText());
			topic.setText("");
		});

		panel.add(topic);
		panel.add(topicDoneButton);

		// Add the question instruction label
		var questionLabel = (JLabel) layout.manage(new JLabel("What specific question do you have about the topic?"), labelWidth, labelHeight, true, false);
		layout.nextLineY(labelHeight);

		panel.add(questionLabel);

		// Add the text box for the question
		var question = (JTextField) layout.manage(new JTextField(), textBoxSizeX, textBoxSizeY, true, false);
		var questionDoneButton = (JButton) layout.manage(new JButton("Done"), buttonWidth, buttonHeight, true, false);

		layout.nextLineY(textBoxSizeY);

		questionDoneButton.addActionListener(e -> {
			setSearchQuestion(question.getText());
			question.setText("");
		});

		panel.add(question);
		panel.add(questionDoneButton);

		// Add the answer instruction label
		var answerLabel = (JLabel) layout.manage(new JLabel("Here are some resources. Hope they help!"), labelWidth, labelHeight, true, false);
		layout.nextLineY(labelHeight);

		panel.add(answerLabel);

		// Add a button to complete the search
		var getAnswerButton = (JButton) layout.manage(new JButton("Get Answer"), buttonWidth, buttonHeight, true, false);
		getAnswerButton.addActionListener(e -> updateSearchText());

		layout.nextLineY(buttonHeight);

		panel.add(getAnswerButton);

		// Add the answer text box
		final int answerBoxWidth = 620;
		final int answerBoxHeight = 320;

		answerText = (JTextPane) layout.manage(new JTextPane(), answerBoxWidth, answerBoxHeight, true, false);
		answerText.setContentType("text/html");
		answerText.setText("");
		answerText.setEditable(false);
		answerText.setBackground(Colors.answerText);
		answerText.setBorder(null);

		layout.nextLineY(answerBoxHeight);

		panel.add(answerText);
	}

	private void updateSearchText() {
		var requestResult = search.evaluate();
		var answerTextContent = new StringBuilder("<html>");

		if (requestResult.isSuccess()) {
			var success = (RequestResult.Success) requestResult;

			String json = success.result().unwrap();
			var results = ResultParser.getResults(json);

			for (var result : results) {
				answerTextContent.append(result.display()).append("<br><br>");
			}
		}

		this.answerText.setText(answerTextContent.toString());
	}

	public void run() {
		this.frame.setVisible(true);
	}
}
