package cn.suvue.discipline.practice.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 测试粘包
 *
 * @author suvue
 * @date 2020/2/17
 */
public class SuvueSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            final Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            while (true){
                byte[] requestBytes = new byte[1024];
                int read = inputStream.read(requestBytes);
                if (read == -1){
                    break;
                }
                System.out.println(new String(requestBytes));
            }
        }

    }
}
