package com.example.demo.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.springframework.stereotype.Service;

@Service
public class TCPClient {

	private Socket socket;
	
	public TCPClient() {
		// TODO Auto-generated constructor stub
	}
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String connectAndSendAndReceive(String ip, Integer port, String message) {
        
		Socket socket = null;
		String result = null;
        
        try {
            socket = new Socket();
            logger.info("[ Request ... ]");
            socket.connect(new InetSocketAddress(ip, port));
            logger.info("[ Success ... ]");
 
            byte[] bytes = null;

            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            //전문 헤더+바디
            message = "IF0001      NEXT0112345678901234567890123456789099INQ0001 2023021520230215" + message; 
            bytes = message.getBytes("UTF-8");
            
            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);
            dos.flush();
            
            logger.info("[ Data Send Success ]" + message);

 
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            
            int leng = dis.readInt();
            logger.info("[ leng ] " + leng);

 
            if (leng > 0) {
                byte input[] = new byte[leng];
                dis.readFully(input, 0, leng);
                
                message = new String(input, 0, leng, "UTF-8");
                logger.info("[ Data Received ]" + message);

                result = message;
            }
 
            os.close();
            is.close();
 
            socket.close();
            logger.info("[ Socket closed ]");

 
        } catch (Exception e) {
        	e.printStackTrace();
        }
 
        if (!socket.isClosed()) {
            try {
                socket.close();
                logger.info("[ Socket closed ]");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return result;
	}
	
}
