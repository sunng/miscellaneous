package self.sunng.miscellaneous.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunxiaodong on 2017/1/23.
 */
public class ClientSocketChannelTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open(); // 创建SocketChannel
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 10000);
        socketChannel.socket().connect(address);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ByteBuffer buffer = ByteBuffer.allocate(1024); // 创建Buffer
        while (true) {
            try {
                buffer.clear();
                String time = sdf.format(new Date());
                buffer.put(time.getBytes());
                buffer.flip(); // 设置成读取状态
                socketChannel.write(buffer); // 发送数据
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("Connection Close");
                break;
            }
        }
    }
}
