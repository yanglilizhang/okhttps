package cn.zhxu.okhttps;

public interface WebSocket extends Cancelable {

	int STATUS_CANCELED = 0;		// 	WebSocket 连接状态：已取消
	int STATUS_CONNECTING = 2;		// 	WebSocket 连接状态：正在连接
	int STATUS_CONNECTED = 1;		// 	WebSocket 连接状态：已连接
	int STATUS_DISCONNECTED = 3;	// 	WebSocket 连接状态：已断开连接
	int STATUS_NETWORK_ERROR = -2;	// 	WebSocket 连接状态：网络错误
	int STATUS_TIMEOUT = -3;		// 	WebSocket 连接状态：连接超时
	int STATUS_EXCEPTION = -1;		// 	WebSocket 连接状态：发生异常


	/**
	 * WebSocket 消息
	 */
	interface Message extends Toable {
		
		/**
		 * 判断是文本消息还是二进制消息
		 * @return 是否是文本消息
		 */
		boolean isText();

	}
	

	class Close {

		public static int CANCELED = 0;
		public static int EXCEPTION = -1;
		public static int NETWORK_ERROR = -2;
		public static int TIMEOUT = -3;

		private int code;
		private String reason;
		
		public Close(int code, String reason) {
			this.code = code;
			this.reason = reason;
		}

		/**
		 * @return 关闭状态码
		 */
		public int getCode() {
			return code;
		}

		/**
		 * @return 关闭原因
		 */
		public String getReason() {
			return reason;
		}

		/**
		 * @return 是否因 WebSocket 连接被取消而关闭
		 */
		public boolean isCanceled() {
			return code == CANCELED;
		}

		/**
		 * @return 是否因 WebSocket 连接发生异常而关闭
		 */
		public boolean isException() {
			return code == EXCEPTION;
		}

		/**
		 * @return 是否因 网络错误 而关闭
		 */
		public boolean isNetworkError() {
			return code == NETWORK_ERROR;
		}

		/**
		 * @return 是否因 网络超时 而关闭
		 */
		public boolean isTimeout() {
			return code == TIMEOUT;
		}

		@Override
		public String toString() {
			return "Close [code=" + code + ", reason=" + reason + "]";
		}
	}
	

	interface Listener<T> {
		
		void on(WebSocket ws, T data);
		
	}
	
	/**
	 * @return 若连接已打开，则：
	 * 同 {@link okhttp3.WebSocket#queueSize()}，返回排序消息的字节数
	 * 否则：
	 * 返回排队消息的数量
	 */
	long queueSize();

	/**
	 * @param object 待发送的对象，可以是 String | ByteString | byte[] | Java Bean
	 * @return 如果连接已断开 返回 false
	 */
	boolean send(Object object);
	
	/**
	 * 同 {@link okhttp3.WebSocket#close(int, String)}
	 * @param code Status code as defined by <a href="http://tools.ietf.org/html/rfc6455#section-7.4">Section 7.4 of RFC 6455</a>
	 * @param reason Reason for shutting down or {@code null}.
	 */
	void close(int code, String reason);

	/**
	 * 设置消息类型
	 * @param type 消息类型，如 json、xml、protobuf 等
	 */
	void msgType(String type);

	/**
	 * WebSocket 当前的连接状态
	 * @since v2.4.5
	 * @see #STATUS_CANCELED
	 * @see #STATUS_CONNECTING
	 * @see #STATUS_CONNECTED
	 * @see #STATUS_DISCONNECTED
	 * @see #STATUS_NETWORK_ERROR
	 * @see #STATUS_TIMEOUT
	 * @see #STATUS_EXCEPTION
	 * @return 连接状态标识
	 */
	int status();

}
