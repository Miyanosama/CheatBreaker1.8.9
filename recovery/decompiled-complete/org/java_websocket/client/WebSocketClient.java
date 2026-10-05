package org.java_websocket.client;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForwardingNode;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;

public abstract class WebSocketClient extends AbstractWebSocket implements WebSocket, Runnable {
   public Proxy proxy;
   public WebSocketImpl engine;
   public URI uri = null;
   public OutputStream ostream;
   public Draft draft;
   public Thread writeThread;
   public Thread connectReadThread;
   public SocketFactory socketFactory;
   public int connectTimeout;
   public CountDownLatch connectLatch;
   public S06PacketUpdateHealth field_0008;
   public Socket socket;
   public DnsResolver dnsResolver;
   public Map<String, String> headers;
   public CountDownLatch closeLatch;
   public ConcurrentHashMapV8$ForwardingNode field_0005;

   @Override
   public InetSocketAddress getLocalSocketAddress(WebSocket var1) {
      return this.socket != null ? (InetSocketAddress)this.socket.getLocalSocketAddress() : null;
   }

   @Override
   public void onWebsocketClosing(WebSocket var1, int var2, String var3, boolean var4) {
      this.onClosing(var2, var3, var4);
   }

   @Override
   public boolean hasBufferedData() {
      return this.engine.hasBufferedData();
   }

   @Override
   public ReadyState getReadyState() {
      return this.engine.getReadyState();
   }

   @Override
   public boolean isFlushAndClose() {
      return this.engine.isFlushAndClose();
   }

   public void closeBlocking() {
      this.close();
      this.closeLatch.await();
   }

   @Override
   public void onWriteDemand(WebSocket var1) {
   }

   public boolean reconnectBlocking() {
      this.reset();
      return this.connectBlocking();
   }

   @Override
   public void sendFrame(Collection<Framedata> var1) {
      this.engine.sendFrame(var1);
   }

   @Override
   public boolean hasSSLSupport() {
      return this.engine.hasSSLSupport();
   }

   @Override
   public SSLSession getSSLSession() {
      return this.engine.getSSLSession();
   }

   @Override
   public InetSocketAddress getRemoteSocketAddress(WebSocket var1) {
      return this.socket != null ? (InetSocketAddress)this.socket.getRemoteSocketAddress() : null;
   }

   @Override
   public void run() {
      InputStream var1;
      try {
         boolean var2 = false;
         if (this.socketFactory != null) {
            this.socket = this.socketFactory.createSocket();
         } else if (this.socket == null) {
            this.socket = new Socket(this.proxy);
            var2 = true;
         } else if (this.socket.isClosed()) {
            throw new IOException();
         }

         this.socket.setTcpNoDelay(this.g_());
         this.socket.setReuseAddress(this.d_());
         if (!this.socket.isConnected()) {
            InetSocketAddress var10 = new InetSocketAddress(this.dnsResolver.resolve(this.uri), this.getPort());
            this.socket.connect(var10, this.connectTimeout);
         }

         if (var2 && "wss".equals(this.uri.getScheme())) {
            SSLContext var11 = SSLContext.getInstance("TLSv1.2");
            var11.init(null, null, null);
            SSLSocketFactory var4 = var11.getSocketFactory();
            this.socket = var4.createSocket(this.socket, this.uri.getHost(), this.getPort(), true);
         }

         if (this.socket instanceof SSLSocket) {
            SSLSocket var12 = (SSLSocket)this.socket;
            SSLParameters var14 = var12.getSSLParameters();
            this.onSetSSLParameters(var14);
            var12.setSSLParameters(var14);
         }

         var1 = this.socket.getInputStream();
         this.ostream = this.socket.getOutputStream();
         this.sendHandshake();
      } catch (Exception var7) {
         this.onWebsocketError(this.engine, var7);
         this.engine.closeConnection(-1, var7.getMessage());
         return;
      } catch (InternalError var8) {
         if (var8.getCause() instanceof InvocationTargetException && var8.getCause().getCause() instanceof IOException) {
            IOException var3 = (IOException)var8.getCause().getCause();
            this.onWebsocketError(this.engine, var3);
            this.engine.closeConnection(-1, var3.getMessage());
            return;
         }

         throw var8;
      }

      this.writeThread = new Thread(new WebSocketClient$WebsocketWriteThread(this, this));
      this.writeThread.start();
      byte[] var9 = new byte[16384];

      try {
         int var13;
         while (!this.isClosing() && !this.isClosed() && (var13 = var1.read(var9)) != -1) {
            this.engine.decode(ByteBuffer.wrap(var9, 0, var13));
         }

         this.engine.eot();
      } catch (IOException var5) {
         this.handleIOException(var5);
      } catch (RuntimeException var6) {
         this.onError(var6);
         this.engine.closeConnection(1006, var6.getMessage());
      }

      this.connectReadThread = null;
   }

   public void onMessage(ByteBuffer var1) {
   }

   @Override
   public <T> void setAttachment(T var1) {
      this.engine.setAttachment(var1);
   }

   public abstract void onClose(int var1, String var2, boolean var3);

   public void connect() {
      if (this.connectReadThread != null) {
         throw new IllegalStateException("WebSocketClient objects are not reuseable");
      } else {
         this.connectReadThread = new Thread(this);
         this.connectReadThread.setName("WebSocketConnectReadThread-" + this.connectReadThread.getId());
         this.connectReadThread.start();
      }
   }

   @Override
   public void onWebsocketMessage(WebSocket var1, ByteBuffer var2) {
      this.onMessage(var2);
   }

   @Override
   public boolean isClosed() {
      return this.engine.isClosed();
   }

   public String removeHeader(String var1) {
      return this.headers == null ? null : this.headers.remove(var1);
   }

   public void onSetSSLParameters(SSLParameters var1) {
      var1.setEndpointIdentificationAlgorithm("HTTPS");
   }

   public void setSocketFactory(SocketFactory var1) {
      this.socketFactory = var1;
   }

   @Override
   public void sendFragmentedFrame(Opcode var1, ByteBuffer var2, boolean var3) {
      this.engine.sendFragmentedFrame(var1, var2, var3);
   }

   public void handleIOException(IOException var1) {
      if (var1 instanceof SSLException) {
         this.onError(var1);
      }

      this.engine.eot();
   }

   @Override
   public void sendFrame(Framedata var1) {
      this.engine.sendFrame(var1);
   }

   public boolean connectBlocking() {
      this.connect();
      this.connectLatch.await();
      return this.engine.isOpen();
   }

   public void clearHeaders() {
      this.headers = null;
   }

   @Override
   public boolean isOpen() {
      return this.engine.isOpen();
   }

   public void setDnsResolver(DnsResolver var1) {
      this.dnsResolver = var1;
   }

   public void reset() {
      Thread var1 = Thread.currentThread();
      if (var1 != this.writeThread && var1 != this.connectReadThread) {
         try {
            this.closeBlocking();
            if (this.writeThread != null) {
               this.writeThread.interrupt();
               this.writeThread = null;
            }

            if (this.connectReadThread != null) {
               this.connectReadThread.interrupt();
               this.connectReadThread = null;
            }

            this.draft.reset();
            if (this.socket != null) {
               this.socket.close();
               this.socket = null;
            }
         } catch (Exception var3) {
            this.onError(var3);
            this.engine.closeConnection(1006, var3.getMessage());
            return;
         }

         this.connectLatch = new CountDownLatch(1);
         this.closeLatch = new CountDownLatch(1);
         this.engine = new WebSocketImpl(this, this.draft);
      } else {
         throw new IllegalStateException(
            "You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to insure a successful cleanup."
         );
      }
   }

   @Override
   public void close(int var1) {
      this.engine.close(var1);
   }

   @Override
   public void send(byte[] var1) {
      this.engine.send(var1);
   }

   public WebSocketClient(URI var1, Map<String, String> var2) {
      this(var1, new Draft_6455(), var2);
   }

   public abstract void onError(Exception var1);

   @Override
   public void onWebsocketCloseInitiated(WebSocket var1, int var2, String var3) {
      this.onCloseInitiated(var2, var3);
   }

   @Override
   public void send(String var1) {
      this.engine.send(var1);
   }

   public WebSocketClient(URI var1, Draft var2, Map<String, String> var3) {
      this(var1, var2, var3, 0);
   }

   public abstract void onOpen(ServerHandshake var1);

   @Override
   public void close() {
      if (this.writeThread != null) {
         this.engine.close(1000);
      }
   }

   @Override
   public boolean isClosing() {
      return this.engine.isClosing();
   }

   @Override
   public void close(int var1, String var2) {
      this.engine.close(var1, var2);
   }

   @Override
   public Draft getDraft() {
      return this.draft;
   }

   public void onClosing(int var1, String var2, boolean var3) {
   }

   public Socket getSocket() {
      return this.socket;
   }

   public WebSocketClient(URI var1) {
      this(var1, new Draft_6455());
   }

   public void setSocket(Socket var1) {
      if (this.socket != null) {
         throw new IllegalStateException("socket has already been set");
      } else {
         this.socket = var1;
      }
   }

   @Override
   public void onWebsocketClose(WebSocket var1, int var2, String var3, boolean var4) {
      this.c_();
      if (this.writeThread != null) {
         this.writeThread.interrupt();
      }

      this.onClose(var2, var3, var4);
      this.connectLatch.countDown();
      this.closeLatch.countDown();
   }

   @Override
   public void closeConnection(int var1, String var2) {
      this.engine.closeConnection(var1, var2);
   }

   public void addHeader(String var1, String var2) {
      if (this.headers == null) {
         this.headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
      }

      this.headers.put(var1, var2);
   }

   @Override
   public InetSocketAddress method_09011() {
      return this.engine.method_09011();
   }

   @Override
   public <T> T getAttachment() {
      return this.engine.getAttachment();
   }

   public WebSocketClient(URI var1, Draft var2) {
      this(var1, var2, null, 0);
   }

   public abstract void onMessage(String var1);

   public WebSocketClient(URI var1, Draft var2, Map<String, String> var3, int var4) {
      this.engine = null;
      this.socket = null;
      this.socketFactory = null;
      this.proxy = Proxy.NO_PROXY;
      this.connectLatch = new CountDownLatch(1);
      this.closeLatch = new CountDownLatch(1);
      this.connectTimeout = 0;
      this.dnsResolver = null;
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var2 == null) {
         throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
      } else {
         this.uri = var1;
         this.draft = var2;
         this.dnsResolver = new WebSocketClient$1(this);
         if (var3 != null) {
            this.headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            this.headers.putAll(var3);
         }

         this.connectTimeout = var4;
         this.setTcpNoDelay(false);
         this.setReuseAddr(false);
         this.engine = new WebSocketImpl(this, var2);
      }
   }

   @Override
   public InetSocketAddress method_09027() {
      return this.engine.method_09027();
   }

   @Override
   public void send(ByteBuffer var1) {
      this.engine.send(var1);
   }

   public int getPort() {
      int var1 = this.uri.getPort();
      if (var1 == -1) {
         String var2 = this.uri.getScheme();
         if ("wss".equals(var2)) {
            return 443;
         } else if ("ws".equals(var2)) {
            return 80;
         } else {
            throw new IllegalArgumentException("unknown scheme: " + var2);
         }
      } else {
         return var1;
      }
   }

   @Override
   public void onWebsocketError(WebSocket var1, Exception var2) {
      this.onError(var2);
   }

   @Override
   public void onWebsocketMessage(WebSocket var1, String var2) {
      this.onMessage(var2);
   }

   @Override
   public void onWebsocketOpen(WebSocket var1, Handshakedata var2) {
      this.i_();
      this.onOpen((ServerHandshake)var2);
      this.connectLatch.countDown();
   }

   public boolean connectBlocking(long var1, TimeUnit var3) {
      this.connect();
      return this.connectLatch.await(var1, var3) && this.engine.isOpen();
   }

   public URI getURI() {
      return this.uri;
   }

   @Override
   public Collection<WebSocket> getConnections() {
      return Collections.singletonList(this.engine);
   }

   public WebSocket getConnection() {
      return this.engine;
   }

   public void reconnect() {
      this.reset();
      this.connect();
   }

   public void onCloseInitiated(int var1, String var2) {
   }

   public void setProxy(Proxy var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.proxy = var1;
      }
   }

   public void sendHandshake() {
      String var2 = this.uri.getRawPath();
      String var3 = this.uri.getRawQuery();
      String var1;
      if (var2 != null && var2.length() != 0) {
         var1 = var2;
      } else {
         var1 = "/";
      }

      if (var3 != null) {
         var1 = var1 + '?' + var3;
      }

      int var4 = this.getPort();
      String var5 = this.uri.getHost() + (var4 != 80 && var4 != 443 ? ":" + var4 : "");
      HandshakeImpl1Client var6 = new HandshakeImpl1Client();
      var6.setResourceDescriptor(var1);
      var6.put("Host", var5);
      if (this.headers != null) {
         for (Entry var8 : this.headers.entrySet()) {
            var6.put((String)var8.getKey(), (String)var8.getValue());
         }
      }

      this.engine.startHandshake(var6);
   }

   @Override
   public void sendPing() {
      this.engine.sendPing();
   }

   @Override
   public String getResourceDescriptor() {
      return this.uri.getPath();
   }
}
