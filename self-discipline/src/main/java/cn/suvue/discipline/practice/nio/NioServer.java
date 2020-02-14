package cn.suvue.discipline.practice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * nio服务端
 *
 * @author suvue
 * @date 2020/2/14
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("服务端启动成功！");
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("收到新连接：" + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                try {
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                        //长连接情况下，需要手动判断数据有没有读取结束（此处做一个简单的判断，超过0字节就认为请求结束了）
                        if (requestBuffer.position() > 0) {
                            break;
                        }
                    }
                    //如果没数据了，则不进行后续的处理
                    if (requestBuffer.position()==0){
                        continue;
                    }
                    //切换读模式
                    requestBuffer.flip();
                    byte[] content = new byte[requestBuffer.limit()];
                    requestBuffer.get(content);
                    System.out.println(new String(content));
                    System.out.println("收到数据，来自："+socketChannel.getRemoteAddress());

                    //响应结果200
                    String response = "HTTP/1.1 200 OK";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()){
                        socketChannel.write(buffer);//非阻塞
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
