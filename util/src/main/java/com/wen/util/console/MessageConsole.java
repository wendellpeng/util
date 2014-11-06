package com.wen.util.console;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageConsole extends Thread{

	private int port = 18019;
	private ServerSocket ss;
	
	public MessageConsole(int port) {
		this.port = port;
	}
	public MessageConsole() {
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread main = new MessageConsole();
		main.start();
	}
	
	@Override
	public void run() {
		for(;;)
		{
			try {
				Socket s = ss.accept();
				new Handler(s).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
