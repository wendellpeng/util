package com.wen.util.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.xinfan.msgbox.service.messagecache.MessageContext;
import com.xinfan.msgbox.vo.CachedMessage;
import com.xinfan.msgbox.vo.CachedUser;


public class Handler extends Thread{

	private Socket socket;

	private String welcome = "Welcome  to console. \r\n" +
			"'countUser' :count all users\r\n" +
			"'countTopic' :count all user topics\r\n"+
			"'countMsgs' :count all messages in cache\r\n"+
			"'listUser' :show all the users\r\n"+
			"'listTopic' :show all users's topic\r\n"+
			"'listMsgs' :show all messages in cache\r\n"+
			"'quit>' to quit .\r\n";
	
	public Handler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		BufferedReader breader = null;
		BufferedWriter bwriter = null;
		try {
			breader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			bwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			bwriter.write(welcome);
			bwriter.flush();
			for(;;)
			{
				
				String line = null;
				try {
					line = breader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if("quit".equalsIgnoreCase(line))
				{
					bwriter.write("bye.\r\n");
					bwriter.flush();
					break;
				}else {
					handleRequest(bwriter, line);
				}
				
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			try {
				if(null != breader)
					breader.close();
				if(null != socket)
					socket.close();
				if(null != bwriter)
					bwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
	}

	private void handleRequest(BufferedWriter bwriter, String line)
			throws IOException {
			bwriter.write(welcome+"\r\n");
			bwriter.flush();
	};
}
