package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.nethandler.client.PacketClientVoice;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.util.internal.PlatformDependent;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.network.play.server.S40PacketDisconnect;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.lf5.viewer.LogTableColumn;
import com.cheatbreaker.client.websocket.server.WSPacketFormattedConsoleOutput;
import com.cheatbreaker.client.ui.element.type.NumericSliderElement$EnumSwitch;

public class SpdySession {
   public SpdySession.StreamComparator streamComparator;
   public AtomicInteger sendWindowSize;
   public AtomicInteger activeLocalStreams = new AtomicInteger();
   public Map<Integer, SpdySession.StreamState> activeStreams;
   public AtomicInteger receiveWindowSize;
   public AtomicInteger activeRemoteStreams = new AtomicInteger();

   public void updateAllReceiveWindowSizes(int var1) {
      for (SpdySession.StreamState var3 : this.activeStreams.values()) {
         var3.updateReceiveWindowSize(var1);
         if (var1 < 0) {
            var3.setReceiveWindowSizeLowerBound(var1);
         }
      }
   }

   public boolean noActiveStreams() {
      return this.activeStreams.isEmpty();
   }

   public void receivedReply(int var1) {
      SpdySession.StreamState var2 = this.activeStreams.get(var1);
      if (var2 != null) {
         var2.receivedReply();
      }
   }

   public SpdySession.PendingWrite getPendingWrite(int var1) {
      if (var1 == 0) {
         for (Entry var3 : this.activeStreams().entrySet()) {
            SpdySession.StreamState var4 = (SpdySession.StreamState)var3.getValue();
            if (var4.getSendWindowSize() > 0) {
               SpdySession.PendingWrite var5 = var4.getPendingWrite();
               if (var5 != null) {
                  return var5;
               }
            }
         }

         return null;
      } else {
         SpdySession.StreamState var2 = this.activeStreams.get(var1);
         return var2 != null ? var2.getPendingWrite() : null;
      }
   }

   public int numActiveStreams(boolean var1) {
      return var1 ? this.activeRemoteStreams.get() : this.activeLocalStreams.get();
   }

   public void acceptStream(int var1, byte var2, boolean var3, boolean var4, int var5, int var6, boolean var7) {
      if (!var3 || !var4) {
         SpdySession.StreamState var8 = this.activeStreams.put(var1, new SpdySession.StreamState(var2, var3, var4, var5, var6));
         if (var8 == null) {
            if (var7) {
               this.activeRemoteStreams.incrementAndGet();
            } else {
               this.activeLocalStreams.incrementAndGet();
            }
         }
      }
   }

   public boolean hasReceivedReply(int var1) {
      SpdySession.StreamState var2 = this.activeStreams.get(var1);
      return var2 != null && var2.hasReceivedReply();
   }

   public boolean putPendingWrite(int var1, SpdySession.PendingWrite var2) {
      SpdySession.StreamState var3 = this.activeStreams.get(var1);
      return var3 != null && var3.putPendingWrite(var2);
   }

   public boolean isRemoteSideClosed(int var1) {
      SpdySession.StreamState var2 = this.activeStreams.get(var1);
      return var2 == null || var2.isRemoteSideClosed();
   }

   public int getSendWindowSize(int var1) {
      if (var1 == 0) {
         return this.sendWindowSize.get();
      } else {
         SpdySession.StreamState var2 = this.activeStreams.get(var1);
         return var2 != null ? var2.getSendWindowSize() : -1;
      }
   }

   public int getReceiveWindowSizeLowerBound(int var1) {
      if (var1 == 0) {
         return 0;
      } else {
         SpdySession.StreamState var2 = this.activeStreams.get(var1);
         return var2 != null ? var2.getReceiveWindowSizeLowerBound() : 0;
      }
   }

   public boolean isLocalSideClosed(int var1) {
      SpdySession.StreamState var2 = this.activeStreams.get(var1);
      return var2 == null || var2.isLocalSideClosed();
   }

   public int updateSendWindowSize(int var1, int var2) {
      if (var1 == 0) {
         return this.sendWindowSize.addAndGet(var2);
      } else {
         SpdySession.StreamState var3 = this.activeStreams.get(var1);
         return var3 != null ? var3.updateSendWindowSize(var2) : -1;
      }
   }

   public SpdySession.PendingWrite removePendingWrite(int var1) {
      SpdySession.StreamState var2 = this.activeStreams.get(var1);
      return var2 != null ? var2.removePendingWrite() : null;
   }

   public Map<Integer, SpdySession.StreamState> activeStreams() {
      TreeMap var1 = new TreeMap<>(this.streamComparator);
      var1.putAll(this.activeStreams);
      return var1;
   }

   public int updateReceiveWindowSize(int var1, int var2) {
      if (var1 == 0) {
         return this.receiveWindowSize.addAndGet(var2);
      } else {
         SpdySession.StreamState var3 = this.activeStreams.get(var1);
         if (var3 == null) {
            return -1;
         } else {
            if (var2 > 0) {
               var3.setReceiveWindowSizeLowerBound(0);
            }

            return var3.updateReceiveWindowSize(var2);
         }
      }
   }

   public SpdySession(int var1, int var2) {
      this.activeStreams = PlatformDependent.newConcurrentHashMap();
      this.streamComparator = new SpdySession.StreamComparator();
      this.sendWindowSize = new AtomicInteger(var1);
      this.receiveWindowSize = new AtomicInteger(var2);
   }

   public boolean isActiveStream(int var1) {
      return this.activeStreams.containsKey(var1);
   }

   public void removeStream(int var1, Throwable var2, boolean var3) {
      SpdySession.StreamState var4 = this.removeActiveStream(var1, var3);
      if (var4 != null) {
         var4.clearPendingWrites(var2);
      }
   }

   public void updateAllSendWindowSizes(int var1) {
      for (SpdySession.StreamState var3 : this.activeStreams.values()) {
         var3.updateSendWindowSize(var1);
      }
   }

   public void closeLocalSide(int var1, boolean var2) {
      SpdySession.StreamState var3 = this.activeStreams.get(var1);
      if (var3 != null) {
         var3.closeLocalSide();
         if (var3.isRemoteSideClosed()) {
            this.removeActiveStream(var1, var2);
         }
      }
   }

   public SpdySession.StreamState removeActiveStream(int var1, boolean var2) {
      SpdySession.StreamState var3 = this.activeStreams.remove(var1);
      if (var3 != null) {
         if (var2) {
            this.activeRemoteStreams.decrementAndGet();
         } else {
            this.activeLocalStreams.decrementAndGet();
         }
      }

      return var3;
   }

   public void closeRemoteSide(int var1, boolean var2) {
      SpdySession.StreamState var3 = this.activeStreams.get(var1);
      if (var3 != null) {
         var3.closeRemoteSide();
         if (var3.isLocalSideClosed()) {
            this.removeActiveStream(var1, var2);
         }
      }
   }

   public static final class PendingWrite {
      public SpdyDataFrame spdyDataFrame;
      public ChannelPromise promise;

      public PendingWrite(SpdyDataFrame var1, ChannelPromise var2) {
         this.spdyDataFrame = var1;
         this.promise = var2;
      }

      public void fail(Throwable var1) {
         this.spdyDataFrame.release();
         this.promise.setFailure(var1);
      }
   }

   public final class StreamComparator implements Serializable, Comparator<Integer> {
      public static final long serialVersionUID = 1161471649740544848L;

      public int compare(Integer var1, Integer var2) {
         SpdySession.StreamState var3 = SpdySession.this.activeStreams.get(var1);
         SpdySession.StreamState var4 = SpdySession.this.activeStreams.get(var2);
         int var5 = var3.getPriority() - var4.getPriority();
         return var5 != 0 ? var5 : var1 - var2;
      }
   }

   public static final class StreamState {
      public boolean remoteSideClosed;
      public AtomicInteger receiveWindowSize;
      public AtomicInteger sendWindowSize;
      public boolean receivedReply;
      public int receiveWindowSizeLowerBound;
      public Queue<SpdySession.PendingWrite> pendingWriteQueue = new ConcurrentLinkedQueue<>();
      public byte priority;
      public boolean localSideClosed;

      public SpdySession.PendingWrite getPendingWrite() {
         return this.pendingWriteQueue.peek();
      }

      public void receivedReply() {
         this.receivedReply = true;
      }

      public StreamState(byte var1, boolean var2, boolean var3, int var4, int var5) {
         this.priority = var1;
         this.remoteSideClosed = var2;
         this.localSideClosed = var3;
         this.sendWindowSize = new AtomicInteger(var4);
         this.receiveWindowSize = new AtomicInteger(var5);
      }

      public int updateReceiveWindowSize(int var1) {
         return this.receiveWindowSize.addAndGet(var1);
      }

      public int getReceiveWindowSizeLowerBound() {
         return this.receiveWindowSizeLowerBound;
      }

      public void setReceiveWindowSizeLowerBound(int var1) {
         this.receiveWindowSizeLowerBound = var1;
      }

      public int getSendWindowSize() {
         return this.sendWindowSize.get();
      }

      public boolean isRemoteSideClosed() {
         return this.remoteSideClosed;
      }

      public byte getPriority() {
         return this.priority;
      }

      public int updateSendWindowSize(int var1) {
         return this.sendWindowSize.addAndGet(var1);
      }

      public boolean putPendingWrite(SpdySession.PendingWrite var1) {
         return this.pendingWriteQueue.offer(var1);
      }

      public void clearPendingWrites(Throwable var1) {
         while (true) {
            SpdySession.PendingWrite var2 = this.pendingWriteQueue.poll();
            if (var2 == null) {
               return;
            }

            var2.fail(var1);
         }
      }

      public void closeRemoteSide() {
         this.remoteSideClosed = true;
      }

      public boolean isLocalSideClosed() {
         return this.localSideClosed;
      }

      public void closeLocalSide() {
         this.localSideClosed = true;
      }

      public SpdySession.PendingWrite removePendingWrite() {
         return this.pendingWriteQueue.poll();
      }

      public boolean hasReceivedReply() {
         return this.receivedReply;
      }
   }
}
