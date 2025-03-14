package cn.zhxu.okhttps;

import cn.zhxu.data.Array;
import cn.zhxu.data.Mapper;
import cn.zhxu.data.TypeRef;
import okio.ByteString;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public interface Toable {

    /**
     * @return 消息体转字节流
     */
    InputStream toByteStream();

    /**
     * @return 消息体转字节数组
     */
    byte[] toBytes();

    /**
     * will be removed in next version
     * @return ByteString
     */
    @Deprecated
    ByteString toByteString();

    /**
     * @return 消息体转字符流
     */
    Reader toCharStream();

    /**
     * @return 消息体转字符串
     */
    String toString();

    /**
     * @return 消息体转 Mapper 对象（不想定义 Java Bean 时使用）
     */
    Mapper toMapper();

    /**
     * @return 消息体转 Array 数组（不想定义 Java Bean 时使用）
     */
    Array toArray();

    /**
     * @param <T> 目标泛型
     * @param type 目标类型
     * @return 报文体Json文本转JavaBean
     */
    <T> T toBean(Class<T> type);

    /**
     * @param <T> 目标泛型
     * @param type 目标类型
     * @return 报文体Json文本转JavaBean
     */
    <T> T toBean(Type type);

    /**
     * @param <T> 目标泛型
     * @param type 目标类型
     * @return 报文体Json文本转JavaBean
     */
    <T> T toBean(TypeRef<T> type);

    /**
     * @param <T> 目标泛型
     * @param type 目标类型
     * @return 报文体Json文本转JavaBean列表
     */
    <T> List<T> toList(Class<T> type);

}
