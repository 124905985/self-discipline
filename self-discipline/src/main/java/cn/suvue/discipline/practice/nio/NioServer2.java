package cn.suvue.discipline.practice.nio;

        import java.io.IOException;
        import java.net.InetSocketAddress;
        import java.nio.ByteBuffer;
        import java.nio.channels.SelectionKey;
        import java.nio.channels.Selector;
        import java.nio.channels.ServerSocketChannel;
        import java.nio.channels.SocketChannel;
        import java.util.Iterator;
        import java.util.Set;

/**
 * 通过Selector实现的服务端
 *
 * @author suvue
 * @date 2020/2/14
 */
public class NioServer2 {
    public static void main(String[] args) throws IOException {
        //1.创建网络服务端serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //2.绑定Selector选择器
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, serverSocketChannel);
        //设置只对accept方法感兴趣
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
        //3.绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8080));
        System.out.println("服务端启动完成！");
        while (true){
            //不再轮询通道，改用下面轮询事件的方式，select方法有阻塞效果，直到有事件通知才会返回
            selector.select();
            //获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历查询结果
            Iterator<SelectionKey> itr = selectionKeys.iterator();
            while (itr.hasNext()){
                //被封装的查询结果
                SelectionKey key = itr.next();
                //因为这个就要被处理了，所以从集合中移除掉
                itr.remove();
                //关注Read和Accept方法
                if (key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.attachment();
                    //将拿到的客户端通道注册到selector上面
                    SocketChannel clientSocketChannel = server.accept();
                    //设置为非阻塞模式
                    clientSocketChannel.configureBlocking(false);
                    clientSocketChannel.register(selector,SelectionKey.OP_READ,clientSocketChannel);
                    System.out.println("收到新连接："+clientSocketChannel.getRemoteAddress());
                }
                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.attachment();
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
                        key.cancel();
                    }

                }
            }
            selector.selectNow();
        }
        //问题：此处一个selector监听所有事件，一个线程处理所有请求事件，会成为瓶颈，要有多线程的运用

    }
}
