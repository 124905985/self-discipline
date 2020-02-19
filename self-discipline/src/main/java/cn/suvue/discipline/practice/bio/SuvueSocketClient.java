package cn.suvue.discipline.practice.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
/**
 * 测试粘包
 *
 * @author suvue
 * @date 2020/2/17
 */
public class SuvueSocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 8080);
        OutputStream outputStream = socket.getOutputStream();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        byte[] bytes = new byte[24];
        byte[] suvue = "我要努力成为大牛".getBytes();
        System.arraycopy(suvue,0,bytes,0,suvue.length);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    outputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        Thread.sleep(2000L);
    }
}
