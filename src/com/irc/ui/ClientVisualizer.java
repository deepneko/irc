package com.irc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import com.irc.IRCManager;
import com.irc.IRCSystem;
import com.irc.client.MessageClient;
import com.irc.util.Day;

/*
 * クライアントのチャット画面
 * 
 * author: deepneko
 */
public class ClientVisualizer {

	MessageClient mc;
	IRCManager manager;
	
	JFrame f;
	Container contentPane;
	JTextArea textField;
	JTextArea textArea;
	JScrollPane textAreaScroll;
	JScrollPane textFieldScroll;
	Font fontType1;
	Font fontBold;
	
	public ClientVisualizer(MessageClient mc){
		this.mc = mc;
		
		f = new JFrame("(・ω・ω・ω・ω・ω・ω・ω・ω・ω・ω・)");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 480);
		f.setLocationRelativeTo(null);

		fontType1 = new Font("type1", Font.TYPE1_FONT, 16);
		fontBold = new Font("bold", Font.BOLD, 12);

		textField = new JTextArea();
		textField.setColumns(2);
		textField.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent evt){
				if(evt.getKeyCode() == KeyEvent.VK_ENTER && evt.getModifiers() == 1){
					textField.append("\n");
				}else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					String str = textField.getText();
					if(str.trim().equals("") || str.trim() == null){
						textField.setText("");
					}else if(!str.equals("") && str != null){
						send(str);
						textField.setText(null);
					}
				}
			}
		});

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(fontType1);
		textArea.setText("This Version is " + IRCSystem.version.getVersion() + "\n");
		textAreaScroll = new JScrollPane(textArea);
		textFieldScroll = new JScrollPane(textField);
		
		contentPane = f.getContentPane();
		contentPane.add(textAreaScroll, BorderLayout.CENTER);
		contentPane.add(textFieldScroll, BorderLayout.SOUTH);
		
		f.setVisible(true);
	}
	
	public void send(String text){
		try {
			mc.send(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void receive(String text){
		textArea.setFont(fontType1);
		String setText = "";
		for(String s:text.split(Pattern.quote("\\n"))){
			setText = s + "\n";
		}
		textArea.setText(Day.getCurrentTime() + setText + textArea.getText());
	}
	
	public void setText(String text, Color c){
		textAreaScroll.getVerticalScrollBar().setValue(textAreaScroll.getVerticalScrollBar().getMaximum());
		textArea.append(text);
	}
}