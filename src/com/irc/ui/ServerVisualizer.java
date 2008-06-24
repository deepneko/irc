package com.irc.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.irc.server.Broadcaster;

/*
 * サーバメッセージを送るためのUI
 * 
 * author: deepneko
 */
public class ServerVisualizer {

	JFrame f;
	Container contentPane;
	JTextField textField;
	JTextArea textArea;
	JScrollPane scrollPane;
	Font fontType1;
	Font fontBold;
	
	public ServerVisualizer(){
		f = new JFrame("(＝ω＝.)");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 100);
		f.setLocationRelativeTo(null);

		fontType1 = new Font("type1", Font.TYPE1_FONT, 16);

		textField = new JTextField();
		textField.setFont(fontType1);

		JButton button = new JButton("Send");
		button.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
				if(!str.equals("") && str != null){
					send(textField.getText());
					textField.setText("");
				}
			}
		});
		
		contentPane = f.getContentPane();
		contentPane.add(textField, BorderLayout.CENTER);
		contentPane.add(button, BorderLayout.SOUTH);
		
		f.setVisible(true);
	}
	
	public void send(String text){
		try {
			new Broadcaster("Server message", text).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new ServerVisualizer();
	}
}