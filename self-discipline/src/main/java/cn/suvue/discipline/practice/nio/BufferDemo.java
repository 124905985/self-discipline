package cn.suvue.discipline.practice.nio;

import java.nio.ByteBuffer;
/**
 * nio的常用api实例，用的是堆内存
 *
 * @author suvue
 * @date 2020/2/14
 */
public class BufferDemo {
    public static void main(String[] args) {
        //构建一个byteBuffer缓存区，大小是4字节
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        //默认写入模式，查看三个重要的指标
        //初始化：capacity容量：4,position位置：0,limit限制：4
        System.out.println(String.format("初始化：capacity容量：%s,position位置：%s,limit限制：%s",
                byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        //写入3字节的数据
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        //再看数据
        //写入3字节数据之后，初始化：capacity容量：4,position位置：3,limit限制：4
        System.out.println(String.format("写入3字节数据之后，初始化：capacity容量：%s,position位置：%s,limit限制：%s",
                byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));

        System.out.println("###开始读取");
        //切换读取模式（不切换也可以读取，只不过读取的位置不对）
        byteBuffer.flip();
        byte a = byteBuffer.get();
        //输出1
        System.out.println(a);
        byte b = byteBuffer.get();
        //输出2
        System.out.println(b);
        //再看数据
        //读取2字节数据之后，初始化：capacity容量：4,position位置：2,limit限制：3
        System.out.println(String.format("读取2字节数据之后，初始化：capacity容量：%s,position位置：%s,limit限制：%s",
                byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        //继续写入3字节数据，此时读模式下，position=2，limit=3，再往下写只能写一条数据了
        //clean()方法清除整个缓存区，compact()方法仅清除已阅读的数据，转换为写入模式
        byteBuffer.compact();//此方法之后，整个buffer内部残留了1字节数据（3），可再写入3字节数据。
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 6);
        //最终的结果，初始化：capacity容量：4,position位置：4,limit限制：4
        System.out.println(String.format("最终的结果，初始化：capacity容量：%s,position位置：%s,limit限制：%s",
                byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));

    }
}
