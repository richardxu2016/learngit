package com.soufun.java.nio.selector;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class EchoProtocol implements Protocol {

	// Ϊÿ���ͻ����ŵ������Ļ�������С
	private int bufSize;

	public EchoProtocol(int bufSize) {
		this.bufSize = bufSize;
	}

	@Override
	public void handleAccept(SelectionKey key) throws IOException {
		// channel() ��������ע��ʱ���������� Channel ���� Channel ��һ�� ServerSocketChannel ��
		// ��Ϊ��������ע���Ψһһ��֧�� accept �������ŵ���
		// accept() ����Ϊ��������ӷ���һ�� SocketChannel ʵ����
		SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();

		// �����޷�ע������ʽ�ŵ��������Ƿ�����ʽ��
		channel.configureBlocking(false);

		// ����ͨ�� SelectionKey ��� selector() ��������ȡ��Ӧ�� Selector ��
		// ���Ǹ���ָ����С������һ���µ� ByteBuffer ʵ����
		// ��������Ϊ�������ݸ� register() ������������Ϊ�������� regiter() ���������ص�
		// SelectionKey ʵ���������
		channel.register(key.selector(), SelectionKey.OP_READ,
				ByteBuffer.allocate(bufSize));
	}

	@Override
	public void handleRead(SelectionKey key) throws IOException {

		SocketChannel channel = (SocketChannel) key.channel();

		ByteBuffer buf = (ByteBuffer) key.attachment();

		long bytesRead = channel.read(buf);

		if (bytesRead == -1) {
			channel.close();
		} else if (bytesRead > 0) {
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException {

		ByteBuffer buf = (ByteBuffer) key.attachment();
		buf.flip();

		SocketChannel channel = (SocketChannel) key.channel();
		channel.write(buf);
		if (!buf.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		}
		
		buf.compact();
	}
}