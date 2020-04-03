package com.github.zi_jing.qqrobot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static Robot robot;
	
	public static void main(String[] args) throws AWTException {
		robot = new Robot();
		File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.txt");
		System.out.printf("数据文件：%s\n", file.getAbsolutePath());
		if(!file.exists()) {
			System.out.println("数据文件不存在");
			return;
		}
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("无法读取数据");
			e.printStackTrace();
			return;
		}
		System.out.println("5秒后启动...");
		robot.delay(5000);
		String line;
		int lineNumber = 0;
		int delay = 0;
		try {
			while(!((line = reader.readLine()) == null)) {
				lineNumber++;
				if(line.startsWith("%%%%DELAY")) {
					delay = Integer.parseInt(line.substring(9));
					continue;
				}
				robot.delay(delay);
				typeLine(line);
			}
		} catch(Exception e) {
			System.out.printf("读取错误，行%d\n", lineNumber);
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("已停止");
	}
	
	private static void typeLine(String str) {
		try {
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
			clip.setContents(new StringSelection(str), null);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
