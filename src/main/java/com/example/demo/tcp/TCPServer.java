package com.example.demo.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TCPServer extends FixedData {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		new TCPServer(tcpServerPort);
	}

	public static int tcpServerPort = 9999;

	public class Server extends Thread {
		private Socket socket;

		public Server(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				while (true) {
					OutputStream os = this.socket.getOutputStream();
					DataOutputStream dos = new DataOutputStream(os);

					InputStream is = this.socket.getInputStream();
					DataInputStream dis = new DataInputStream(is);

					// read int
					int recieveLength = dis.readInt();
					logger.info("[ RecieveLength ] " + recieveLength);

					// receive bytes
					byte receiveByte[] = new byte[recieveLength];
					dis.readFully(receiveByte, 0, recieveLength);

					byte bodyReceiveByte[] = new byte[recieveLength - 74];
					System.arraycopy(receiveByte, 74, bodyReceiveByte, 0, recieveLength - 74);
					String bodyMessage = new String(bodyReceiveByte);

					// read and set received data
					ReceivedLinkedMapData receivedLinkedMapData = new ReceivedLinkedMapData();
					String receiveMessage = readAndSetData(receiveByte, receivedLinkedMapData);

					// set response data
					String dataAll = buildData(bodyMessage);
					logger.info("[ ClientSendBodyMessage ] " + dataAll);

					String sendMessage = receiveMessage + dataAll;

					// send bytes
					byte[] sendBytes = sendMessage.getBytes("UTF-8");
					int sendLength = sendBytes.length;
					dos.writeInt(sendLength);
					dos.write(sendBytes, 0, sendLength);
					dos.flush();

					logger.info("[ Data Send Success ]");
					logger.info("[ Length ] " + sendLength);
					logger.info("[ Message ] " + sendMessage);
				}
			} catch (IOException e) {
//				e.printStackTrace();
			} finally {
				try {
					if (this.socket != null) {
						logger.info("[ Socket closed ] ");
						logger.info("Disconnected :" + this.socket.getInetAddress().getHostAddress() + ":"
								+ this.socket.getPort());

						this.socket.close();
					}
				} catch (Exception e) {
				}
			}
		}
	}

	public TCPServer(int portNo) {
		tcpServerPort = portNo;
		try {
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(tcpServerPort));
			logger.info("Starting tcp Server: " + tcpServerPort);
			logger.info("[ Waiting ]");
			
			while (true) {
				Socket socket = serverSocket.accept();
				logger.info("[ Connected ] " + socket.getLocalPort());
			
				Server tcpServer = new Server(socket);
				tcpServer.start();
			}
		} catch (IOException io) {
			io.getStackTrace();
		}
	}

	private String readAndSetData(byte[] receiveByte, ReceivedLinkedMapData receivedLinkedMapData)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		if (ReceivedMapDataGlobal.receivedMapDataGlobal == null
				|| ReceivedMapDataGlobal.receivedMapDataGlobal.size() == 0) {
			ReceivedMapDataGlobal.receivedMapDataGlobal = new HashMap<String, ReceivedLinkedMapData>();
		}

		for (int i = 0; i < arrayId.length - 1; i++) {
			System.arraycopy(receiveByte, sumUpArray(arrayId, i), arrayByte[i + 1], 0, arrayId[i + 1]);
			String newStr = new String(arrayByte[i + 1], "UTF-8");
			logger.info("[ " + arrayStr[i + 1] + " ] " + "\'" + newStr + "\'");
			
			sb.append(newStr);
			linkedHashMap.put(arrayKey[i + 1], newStr.trim());
		}
		String sendMessage = sb.toString();
		receivedLinkedMapData.receivedLinkedHashMap = linkedHashMap;

		System.arraycopy(receiveByte, sumUpArray(arrayId, 2), arrayByte[3], 0, arrayId[3]);
		String newStr = new String(arrayByte[3], "UTF-8");

		if (ReceivedMapDataGlobal.receivedMapDataGlobal.containsKey(newStr)) {
			ReceivedMapDataGlobal.receivedMapDataGlobal.get(newStr);
		} else {
			ReceivedMapDataGlobal.receivedMapDataGlobal.put(newStr, receivedLinkedMapData);
		}

		return sendMessage;
	}

	private String buildData(String clientBodyMessage) throws IOException {

		// Input 데이터 중 입력건수 저장
		String inputCount = clientBodyMessage.substring(16, 20);
		logger.info("[ ClientReceiveBodyMessage ]: " + clientBodyMessage);
		logger.info("[ inputCount ]: " + inputCount);

		String msgCode = "";
		String cnt = inputCount;

		Integer sendCount = Integer.parseInt(inputCount);
		String repeatData = "";

		String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\demo\\tcp\\";

		BufferedReader reader = new BufferedReader(new FileReader(path + "response.dat"));

		String[] responseBodyArray = new String[4];
		String str;
		Integer i = 0;
		while ((str = reader.readLine()) != null) {
			responseBodyArray[i] = str;
			i++;
		}

		reader.close();

		for (i = 0; i < sendCount; i++) {
			repeatData = repeatData + responseBodyArray[i].toString();

		}

		String dataAll = msgCode + cnt + repeatData;

		return dataAll;
	}


	private int sumUpArray(int[] arrayId, int i) {
		int sum = 0;
		for (int j = 0; j <= i; j++) {
			sum += arrayId[j];
		}
		return sum;
	}

}