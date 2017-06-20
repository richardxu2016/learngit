package com.soufun.java.nio.selector;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class EchoProtocol implements Protocol {

	// 为每个客户端信道创建的缓冲区大小
	private int bufSize;

	public EchoProtocol(int bufSize) {
		this.bufSize = bufSize;
	}

	@Override
	public void handleAccept(SelectionKey key) throws IOException {
		// channel() 方法返回注册时用来创建的 Channel ，该 Channel 是一个 ServerSocketChannel ，
		// 因为这是我们注册的唯一一种支持 accept 操作的信道，
		// accept() 方法为传入的连接返回一个 SocketChannel 实例。
		SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();

		// 这里无法注册阻塞式信道，必须是非阻塞式的
		channel.configureBlocking(false);

		// 可以通过 SelectionKey 类的 selector() 方法来获取相应的 Selector 。
		// 我们根据指定大小创建了一个新的 ByteBuffer 实例，
		// 并将其作为参数传递给 register() 方法。它将作为附件，与 regiter() 方法所返回的
		// SelectionKey 实例相关联。
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