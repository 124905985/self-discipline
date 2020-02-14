package cn.suvue.discipline.practice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * nio服务端，升级了一下
 *
 * @author suvue
 * @date 2020/2/14
 */
public class NioServer1 {
    private static List<SocketChannel> channels = new ArrayList<>();

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
                channels.add(socketChannel);
            } else {
                //没有新连接的情况下，则去处理现有连接的数据，处理完就删除掉
                Iterator<SocketChannel> iterator = channels.iterator();
                while (iterator.hasNext()) {
                    SocketChannel sc = iterator.next();
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        if (sc.read(byteBuffer) == 0) {
                            //等于0，代表这个通道当前没有数据需要处理，那就待会处理。
                            continue;
                        }
                        while (sc.isOpen() && sc.read(byteBuffer) != -1) {
                            //长连接情况下，需要手动判断数据有没有读取结束（此处做一个简单的判断，超过0字节就认为请求结束了）
                            if (byteBuffer.position() > 0) {
                                break;
                            }
                        }
                        //如果没数据了，则不进行后续的处理
                        if (byteBuffer.position() == 0) {
                            continue;
                        }
                        //切换读模式
                        byteBuffer.flip();
                        byte[] content = new byte[byteBuffer.limit()];
                        byteBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("收到数据，来自：" + sc.getRemoteAddress());

                        //响应结果200
                        String response = "HTTP/1.1 200 OK";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while (buffer.hasRemaining()) {
                            sc.write(buffer);//非阻塞
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        }
    }
}
