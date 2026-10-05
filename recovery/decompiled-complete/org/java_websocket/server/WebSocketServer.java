package org.java_websocket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.SocketChannelIOHelper;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.exceptions.WrappedIOException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recovered.unidentified.UnidentifiedClass1095;

public abstract class WebSocketServer extends AbstractWebSocket implements Runnable {
   public BlockingQueue<ByteBuffer> buffers;
   public int maxPendingConnections;
   public AtomicBoolean isclosed;
   public ServerSocketChannel server;
   public AtomicInteger queuesize;
   public List<WebSocketServer$WebSocketWorker> decoders;
   public List<Draft> drafts;
   public WebSocketServerFactory wsf;
   public C01PacketEncryptionResponse field_0012;
   public Selector selector;
   public Thread selectorthread;
   public Collection<WebSocket> connections;
   public Logger log = LoggerFactory.getLogger(WebSocketServer.class);
   public List<WebSocketImpl> iqueue;
   public InetSocketAddress address;
   public int queueinvokes;
   public static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
   public UnidentifiedClass1095 field_0005;

   public void start() {
      if (this.selectorthread != null) {
         throw new IllegalStateException(this.getClass().getName() + " can only be started once.");
      } else {
         new Thread(this).start();
      }
   }

   public int getPort() {
      int var1 = this.getAddress().getPort();
      if (var1 == 0 && this.server != null) {
         var1 = this.server.socket().getLocalPort();
      }

      return var1;
   }

   public WebSocketServer(InetSocketAddress var1, int var2) {
      this(var1, var2, null);
   }

   @Override
   public void onWebsocketMessage(WebSocket var1, String var2) {
      this.onMessage(var1, var2);
   }

   public boolean onConnect(SelectionKey var1) {
      return true;
   }

   @Override
   public InetSocketAddress getLocalSocketAddress(WebSocket var1) {
      return (InetSocketAddress)this.getSocket(var1).getLocalSocketAddress();
   }

   public boolean doSetupSelectorAndServerThread() {
      this.selectorthread.setName("WebSocketSelector-" + this.selectorthread.getId());

      try {
         this.server = ServerSocketChannel.open();
         this.server.configureBlocking(false);
         ServerSocket var1 = this.server.socket();
         var1.setReceiveBufferSize(16384);
         var1.setReuseAddress(this.d_());
         var1.bind(this.address, this.getMaxPendingConnections());
         this.selector = Selector.open();
         this.server.register(this.selector, this.server.validOps());
         this.i_();

         for (WebSocketServer$WebSocketWorker var3 : this.decoders) {
            var3.start();
         }

         this.onStart();
         return true;
      } catch (IOException var4) {
         this.handleFatal(null, var4);
         return false;
      }
   }

   public void doAccept(SelectionKey var1, Iterator<SelectionKey> var2) {
      if (!this.onConnect(var1)) {
         var1.cancel();
      } else {
         SocketChannel var3 = this.server.accept();
         if (var3 != null) {
            var3.configureBlocking(false);
            Socket var4 = var3.socket();
            var4.setTcpNoDelay(this.g_());
            var4.setKeepAlive(true);
            WebSocketImpl var5 = this.wsf.createWebSocket(this, this.drafts);
            var5.setSelectionKey(var3.register(this.selector, 1, var5));

            try {
               var5.setChannel(this.wsf.wrapChannel(var3, var5.getSelectionKey()));
               var2.remove();
               this.allocateBuffers(var5);
            } catch (IOException var7) {
               if (var5.getSelectionKey() != null) {
                  var5.getSelectionKey().cancel();
               }

               this.handleIOException(var5.getSelectionKey(), null, var7);
            }
         }
      }
   }

   @Override
   public void onWebsocketMessage(WebSocket var1, ByteBuffer var2) {
      this.onMessage(var1, var2);
   }

   public void broadcast(String var1) {
      this.broadcast(var1, this.connections);
   }

   public void fillFrames(Draft var1, Map<Draft, List<Framedata>> var2, String var3, ByteBuffer var4) {
      if (!var2.containsKey(var1)) {
         List var5 = null;
         if (var3 != null) {
            var5 = var1.createFrames(var3, false);
         }

         if (var4 != null) {
            var5 = var1.createFrames(var4, false);
         }

         if (var5 != null) {
            var2.put(var1, var5);
         }
      }
   }

   public void doAdditionalRead() {
      while (!this.iqueue.isEmpty()) {
         WebSocketImpl var1 = this.iqueue.remove(0);
         WrappedByteChannel var2 = (WrappedByteChannel)var1.getChannel();
         ByteBuffer var3 = this.takeBuffer();

         try {
            if (SocketChannelIOHelper.readMore(var3, var1, var2)) {
               this.iqueue.add(var1);
            }

            if (var3.hasRemaining()) {
               var1.inQueue.put(var3);
               this.queue(var1);
            } else {
               this.pushBuffer(var3);
            }
         } catch (IOException var5) {
            this.pushBuffer(var3);
            throw var5;
         }
      }
   }

   public boolean addConnection(WebSocket var1) {
      if (!this.isclosed.get()) {
         synchronized (this.connections) {
            return this.connections.add(var1);
         }
      } else {
         var1.close(1001);
         return true;
      }
   }

   public void doServerShutdown() {
      this.c_();
      if (this.decoders != null) {
         for (WebSocketServer$WebSocketWorker var2 : this.decoders) {
            var2.interrupt();
         }
      }

      if (this.selector != null) {
         try {
            this.selector.close();
         } catch (IOException var4) {
            this.log.error("IOException during selector.close", (Throwable)var4);
            this.onError(null, var4);
         }
      }

      if (this.server != null) {
         try {
            this.server.close();
         } catch (IOException var3) {
            this.log.error("IOException during server.close", (Throwable)var3);
            this.onError(null, var3);
         }
      }
   }

   public abstract void onStart();

   public void releaseBuffers(WebSocket var1) {
   }

   public void handleIOException(SelectionKey var1, WebSocket var2, IOException var3) {
      if (var1 != null) {
         var1.cancel();
      }

      if (var2 != null) {
         var2.closeConnection(1006, var3.getMessage());
      } else if (var1 != null) {
         SelectableChannel var4 = var1.channel();
         if (var4 != null && var4.isOpen()) {
            try {
               var4.close();
            } catch (IOException var6) {
            }

            this.log.trace("Connection closed because of exception", (Throwable)var3);
         }
      }
   }

   public void stop() {
      this.stop(0);
   }

   @Override
   public void onWebsocketClose(WebSocket var1, int var2, String var3, boolean var4) {
      this.selector.wakeup();

      try {
         if (this.removeConnection(var1)) {
            this.onClose(var1, var2, var3, var4);
         }
      } finally {
         try {
            this.releaseBuffers(var1);
         } catch (InterruptedException var11) {
            Thread.currentThread().interrupt();
         }
      }
   }

   public void onMessage(WebSocket var1, ByteBuffer var2) {
   }

   @Override
   public void onWebsocketClosing(WebSocket var1, int var2, String var3, boolean var4) {
      this.onClosing(var1, var2, var3, var4);
   }

   public void broadcast(ByteBuffer var1) {
      this.broadcast(var1, this.connections);
   }

   @Override
   public void onWebsocketError(WebSocket var1, Exception var2) {
      this.onError(var1, var2);
   }

   public void broadcast(byte[] var1, Collection<WebSocket> var2) {
      if (var1 != null && var2 != null) {
         this.broadcast(ByteBuffer.wrap(var1), var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void onWriteDemand(WebSocket var1) {
      WebSocketImpl var2 = (WebSocketImpl)var1;

      try {
         var2.getSelectionKey().interestOps(5);
      } catch (CancelledKeyException var4) {
         var2.outQueue.clear();
      }

      this.selector.wakeup();
   }

   public InetSocketAddress getAddress() {
      return this.address;
   }

   public void setMaxPendingConnections(int var1) {
      this.maxPendingConnections = var1;
   }

   public boolean doRead(SelectionKey var1, Iterator<SelectionKey> var2) {
      WebSocketImpl var3 = (WebSocketImpl)var1.attachment();
      ByteBuffer var4 = this.takeBuffer();
      if (var3.getChannel() == null) {
         var1.cancel();
         this.handleIOException(var1, var3, new IOException());
         return false;
      } else {
         try {
            if (SocketChannelIOHelper.read(var4, var3, var3.getChannel())) {
               if (var4.hasRemaining()) {
                  var3.inQueue.put(var4);
                  this.queue(var3);
                  var2.remove();
                  if (var3.getChannel() instanceof WrappedByteChannel && ((WrappedByteChannel)var3.getChannel()).isNeedRead()) {
                     this.iqueue.add(var3);
                  }
               } else {
                  this.pushBuffer(var4);
               }
            } else {
               this.pushBuffer(var4);
            }

            return true;
         } catch (IOException var6) {
            this.pushBuffer(var4);
            throw new WrappedIOException(var3, var6);
         }
      }
   }

   public boolean removeConnection(WebSocket var1) {
      boolean var2 = false;
      synchronized (this.connections) {
         if (this.connections.contains(var1)) {
            var2 = this.connections.remove(var1);
         } else {
            this.log.trace("Removing connection which is not in the connections collection! Possible no handshake recieved! {}", var1);
         }
      }

      if (this.isclosed.get() && this.connections.isEmpty()) {
         this.selectorthread.interrupt();
      }

      return var2;
   }

   public int getMaxPendingConnections() {
      return this.maxPendingConnections;
   }

   public ByteBuffer takeBuffer() {
      return this.buffers.take();
   }

   @Override
   public void onWebsocketOpen(WebSocket var1, Handshakedata var2) {
      if (this.addConnection(var1)) {
         this.onOpen(var1, (ClientHandshake)var2);
      }
   }

   @Override
   public void onWebsocketCloseInitiated(WebSocket var1, int var2, String var3) {
      this.onCloseInitiated(var1, var2, var3);
   }

   public abstract void onClose(WebSocket var1, int var2, String var3, boolean var4);

   public abstract void onOpen(WebSocket var1, ClientHandshake var2);

   public void stop(int var1) {
      if (this.isclosed.compareAndSet(false, true)) {
         ArrayList var2;
         synchronized (this.connections) {
            var2 = new ArrayList<>(this.connections);
         }

         for (WebSocket var4 : var2) {
            var4.close(1001);
         }

         this.wsf.close();
         synchronized (this) {
            if (this.selectorthread != null && this.selector != null) {
               this.selector.wakeup();
               this.selectorthread.join(var1);
            }
         }
      }
   }

   public void setWebSocketFactory(WebSocketServerFactory var1) {
      if (this.wsf != null) {
         this.wsf.close();
      }

      this.wsf = var1;
   }

   public Socket getSocket(WebSocket var1) {
      WebSocketImpl var2 = (WebSocketImpl)var1;
      return ((SocketChannel)var2.getSelectionKey().channel()).socket();
   }

   @Override
   public Collection<WebSocket> getConnections() {
      synchronized (this.connections) {
         return Collections.unmodifiableCollection(new ArrayList<>(this.connections));
      }
   }

   public WebSocketServer() {
      this(new InetSocketAddress(80), AVAILABLE_PROCESSORS, null);
   }

   public abstract void onError(WebSocket var1, Exception var2);

   public List<Draft> getDraft() {
      return Collections.unmodifiableList(this.drafts);
   }

   @Override
   public void run() {
      if (this.doEnsureSingleThread()) {
         if (this.doSetupSelectorAndServerThread()) {
            try {
               int var1 = 5;
               byte var2 = 0;

               while (!this.selectorthread.isInterrupted() && var1 != 0) {
                  SelectionKey var3 = null;

                  try {
                     if (this.isclosed.get()) {
                        var2 = 5;
                     }

                     int var4 = this.selector.select(var2);
                     if (var4 == 0 && this.isclosed.get()) {
                        var1--;
                     }

                     Set var5 = this.selector.selectedKeys();
                     Iterator var6 = var5.iterator();

                     while (var6.hasNext()) {
                        var3 = (SelectionKey)var6.next();
                        if (var3.isValid()) {
                           if (var3.isAcceptable()) {
                              this.doAccept(var3, var6);
                           } else if ((!var3.isReadable() || this.doRead(var3, var6)) && var3.isWritable()) {
                              this.doWrite(var3);
                           }
                        }
                     }

                     this.doAdditionalRead();
                  } catch (CancelledKeyException var15) {
                  } catch (ClosedByInterruptException var16) {
                     return;
                  } catch (WrappedIOException var17) {
                     this.handleIOException(var3, var17.getConnection(), var17.getIOException());
                  } catch (IOException var18) {
                     this.handleIOException(var3, null, var18);
                  } catch (InterruptedException var19) {
                     Thread.currentThread().interrupt();
                  }
               }
            } catch (RuntimeException var20) {
               this.handleFatal(null, var20);
            } finally {
               this.doServerShutdown();
            }
         }
      }
   }

   public void broadcast(byte[] var1) {
      this.broadcast(var1, this.connections);
   }

   public void doWrite(SelectionKey var1) {
      WebSocketImpl var2 = (WebSocketImpl)var1.attachment();

      try {
         if (SocketChannelIOHelper.batch(var2, var2.getChannel()) && var1.isValid()) {
            var1.interestOps(1);
         }
      } catch (IOException var4) {
         throw new WrappedIOException(var2, var4);
      }
   }

   public boolean doEnsureSingleThread() {
      synchronized (this) {
         if (this.selectorthread != null) {
            throw new IllegalStateException(this.getClass().getName() + " can only be started once.");
         } else {
            this.selectorthread = Thread.currentThread();
            return !this.isclosed.get();
         }
      }
   }

   @Override
   public InetSocketAddress getRemoteSocketAddress(WebSocket var1) {
      return (InetSocketAddress)this.getSocket(var1).getRemoteSocketAddress();
   }

   public ByteBuffer createBuffer() {
      return ByteBuffer.allocate(16384);
   }

   public void doBroadcast(Object var1, Collection<WebSocket> var2) {
      String var3 = null;
      if (var1 instanceof String) {
         var3 = (String)var1;
      }

      ByteBuffer var4 = null;
      if (var1 instanceof ByteBuffer) {
         var4 = (ByteBuffer)var1;
      }

      if (var3 != null || var4 != null) {
         HashMap var5 = new HashMap();
         ArrayList var6;
         synchronized (var2) {
            var6 = new ArrayList(var2);
         }

         for (WebSocket var8 : var6) {
            if (var8 != null) {
               Draft var9 = var8.getDraft();
               this.fillFrames(var9, var5, var3, var4);

               try {
                  var8.sendFrame((Collection<Framedata>)var5.get(var9));
               } catch (WebsocketNotConnectedException var11) {
               }
            }
         }
      }
   }

   public void broadcast(String var1, Collection<WebSocket> var2) {
      if (var1 != null && var2 != null) {
         this.doBroadcast(var1, var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void broadcast(ByteBuffer var1, Collection<WebSocket> var2) {
      if (var1 != null && var2 != null) {
         this.doBroadcast(var1, var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public WebSocketServer(InetSocketAddress var1, List<Draft> var2) {
      this(var1, AVAILABLE_PROCESSORS, var2);
   }

   public void onClosing(WebSocket var1, int var2, String var3, boolean var4) {
   }

   public abstract void onMessage(WebSocket var1, String var2);

   public WebSocketServer(InetSocketAddress var1, int var2, List<Draft> var3) {
      this(var1, var2, var3, new HashSet<>());
   }

   public WebSocketServer(InetSocketAddress var1, int var2, List<Draft> var3, Collection<WebSocket> var4) {
      this.isclosed = new AtomicBoolean(false);
      this.queueinvokes = 0;
      this.queuesize = new AtomicInteger(0);
      this.wsf = new DefaultWebSocketServerFactory();
      this.maxPendingConnections = -1;
      if (var1 != null && var2 >= 1 && var4 != null) {
         if (var3 == null) {
            this.drafts = Collections.emptyList();
         } else {
            this.drafts = var3;
         }

         this.address = var1;
         this.connections = var4;
         this.setTcpNoDelay(false);
         this.setReuseAddr(false);
         this.iqueue = new LinkedList<>();
         this.decoders = new ArrayList<>(var2);
         this.buffers = new LinkedBlockingQueue<>();

         for (int var5 = 0; var5 < var2; var5++) {
            WebSocketServer$WebSocketWorker var6 = new WebSocketServer$WebSocketWorker(this);
            this.decoders.add(var6);
         }
      } else {
         throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
      }
   }

   public void queue(WebSocketImpl var1) {
      if (var1.getWorkerThread() == null) {
         var1.setWorkerThread(this.decoders.get(this.queueinvokes % this.decoders.size()));
         this.queueinvokes++;
      }

      var1.getWorkerThread().put(var1);
   }

   public void pushBuffer(ByteBuffer var1) {
      if (this.buffers.size() <= this.queuesize.intValue()) {
         this.buffers.put(var1);
      }
   }

   public void allocateBuffers(WebSocket var1) {
      if (this.queuesize.get() < 2 * this.decoders.size() + 1) {
         this.queuesize.incrementAndGet();
         this.buffers.put(this.createBuffer());
      }
   }

   public void onCloseInitiated(WebSocket var1, int var2, String var3) {
   }

   public WebSocketServer(InetSocketAddress var1) {
      this(var1, AVAILABLE_PROCESSORS, null);
   }

   public WebSocketFactory getWebSocketFactory() {
      return this.wsf;
   }

   public void handleFatal(WebSocket var1, Exception var2) {
      this.log.error("Shutdown due to fatal error", (Throwable)var2);
      this.onError(var1, var2);
      if (this.decoders != null) {
         for (WebSocketServer$WebSocketWorker var4 : this.decoders) {
            var4.interrupt();
         }
      }

      if (this.selectorthread != null) {
         this.selectorthread.interrupt();
      }

      try {
         this.stop();
      } catch (IOException var5) {
         this.log.error("Error during shutdown", (Throwable)var5);
         this.onError(null, var5);
      } catch (InterruptedException var6) {
         Thread.currentThread().interrupt();
         this.log.error("Interrupt during stop", (Throwable)var2);
         this.onError(null, var6);
      }
   }
}
