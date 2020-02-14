package cn.suvue.discipline.practice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 模拟一个客户端
 *
 * @author suvue
 * @date 2020/2/14
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        while (!socketChannel.finishConnect()) {
            //没连接上，则一直等待
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        //获取要发送的内容
        String msg = scanner.nextLine();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }

        //读取响应
        System.out.println("收到服务器响应：");
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.isOpen() && socketChannel.read(byteBuffer) != -1) {
            if (byteBuffer.position() > 0) {
                break;
            }
        }
        //切换读模式
        byteBuffer.flip();
        byte[] content = new byte[byteBuffer.limit()];
        byteBuffer.get(content);
        System.out.println(new String(content));
        scanner.close();
        socketChannel.close();

    }
}
