package cn.zhxu.stomp;

import java.util.function.Consumer;

/**
 * Stomp 消息编解码器
 * @since v3.1.0
 */
public interface MsgCodec {

    /**
     * 编码
     * @param input Stomp 消息
     * @return 编码后的文本
     */
    String encode(Message input);

    /**
     * 解码
     * @param input 输入
     * @param out 输出
     */
    void decode(String input, Consumer<Message> out);

}
