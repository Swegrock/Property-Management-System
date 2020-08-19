package com.mootz.property.Managers;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class DialogManager {
	public static void DisplayMessage(String title, String contents) {
		JOptionPane.showMessageDialog(null, contents, title, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static String GetInput(String contents, String defaultText) {
		return JOptionPane.showInputDialog(null, contents, defaultText);
	}
	
	public static int GetDropDown(String contents, String[] drops, int num) {
		String choice = JOptionPane.showInputDialog(null, contents, "Input", JOptionPane.QUESTION_MESSAGE, null, drops, drops[num]).toString();
		return Arrays.asList(drops).indexOf(choice);
	}
}
