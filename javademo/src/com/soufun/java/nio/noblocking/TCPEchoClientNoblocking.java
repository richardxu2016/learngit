package com.soufun.java.nio.noblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author RICHARD
 *  @date 5/13
 */
public class TCPEchoClientNoblocking {

	public static void main(String[] args) throws IOException {
		
		String server = "127.0.0.1";
		byte[] data = "abcdefghijklmnopqrstuvwxyz".getBytes();
		int serverPort = 8888;
		SocketChannel clntChan = SocketChannel.open();
		clntChan.configureBlocking(false);
		/*
		 * ����ͨ���������� finishConnect() ����������ѯ������״̬���÷��������ӳɹ�����֮ǰ
		 *  һֱ���� false ����ӡ������ʾ���ڵȴ����ӽ����Ĺ����У����򻹿���ִ���������񡣲���
		 *  ����æ�ȵķ����ǳ��˷�ϵͳ��Դ������������ֻ��Ϊ����ʾ�÷�����ʹ�á�
		 */
		if (!clntChan.connect(new InetSocketAddress(server, serverPort))) {
			while (!clntChan.finishConnect()) {
				System.out.println("=");
			}
		}

		ByteBuffer writeBuf = ByteBuffer.wrap(data);
		ByteBuffer readBuf = ByteBuffer.allocate(data.length);

		int totalBytesRcvd = 0;
		int bytesRcvd;
		while (totalBytesRcvd < data.length) {
			if (writeBuf.hasRemaining()) {
				clntChan.write(writeBuf);
			}
			if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}

			totalBytesRcvd += bytesRcvd;
			System.out.println("=");
		}

		System.out.println("Recieved: "
				+ new String(readBuf.array(), 0, totalBytesRcvd));

		clntChan.close();
	}
}