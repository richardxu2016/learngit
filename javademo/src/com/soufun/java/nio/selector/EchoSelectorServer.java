package com.soufun.java.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoSelectorServer {

	private static final int BUFSIZE = 256;
	private static final int TIMEOUT = 3000;
	private static final int PORT = 8888;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Selector selector = Selector.open();

		ServerSocketChannel listnChannel = ServerSocketChannel.open();
		listnChannel.socket().bind(new InetSocketAddress(PORT));
		listnChannel.configureBlocking(false);
		listnChannel.register(selector, SelectionKey.OP_ACCEPT);

		Protocol protocol = new EchoProtocol(BUFSIZE);

		while (true) {
			if (selector.select(TIMEOUT) == 0) {
				System.out.println("=");
				continue;
			}
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			while (keyIter.hasNext()) {
				SelectionKey key = keyIter.next();
				if (key.isAcceptable()) {
					protocol.handleAccept(key);
				}
				if (key.isReadable()) {
					protocol.handleRead(key);
				}
				if (key.isValid() && key.isWritable()) {
					protocol.handleWrite(key);
				}
				// ���� select() ����ֻ���� Selector �������ļ����������Ԫ��
				// ��ˣ�������Ƴ�ÿ��������ļ���
				// ���ͻ����´ε��� select() ����ʱ��Ȼ�����ڼ�����
				// ���ҿ��ܻ������õĲ�������������
				keyIter.remove();
			}
		}
	}
}
