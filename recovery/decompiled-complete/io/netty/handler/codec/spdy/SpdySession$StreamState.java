package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.nethandler.client.PacketClientVoice;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.network.play.server.S40PacketDisconnect;

public class SpdySession$StreamState {
   public boolean remoteSideClosed;
   public AtomicInteger receiveWindowSize;
   public AtomicInteger sendWindowSize;
   public boolean receivedReply;
   public int receiveWindowSizeLowerBound;
   public Queue<SpdySession$PendingWrite> pendingWriteQueue = new ConcurrentLinkedQueue<>();
   public byte priority;
   public PacketClientVoice __junk3063113125105104975;
   public boolean localSideClosed;
   public S40PacketDisconnect __junk4310129424033964980;

   public SpdySession$PendingWrite getPendingWrite() {
      return this.pendingWriteQueue.peek();
   }

   public void receivedReply() {
      this.receivedReply = true;
   }

   public SpdySession$StreamState(byte var1, boolean var2, boolean var3, int var4, int var5) {
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

   public boolean putPendingWrite(SpdySession$PendingWrite var1) {
      return this.pendingWriteQueue.offer(var1);
   }

   public void clearPendingWrites(Throwable var1) {
      while (true) {
         SpdySession$PendingWrite var2 = this.pendingWriteQueue.poll();
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

   public SpdySession$PendingWrite removePendingWrite() {
      return this.pendingWriteQueue.poll();
   }

   public boolean hasReceivedReply() {
      return this.receivedReply;
   }
}
