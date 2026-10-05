package io.netty.util;

import io.netty.handler.codec.spdy.SpdySession$PendingWrite;
import io.netty.util.internal.StringUtil;
import java.lang.ref.PhantomReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.util.BlockPos$2;

public class ResourceLeakDetector$DefaultResourceLeak extends PhantomReference<Object> implements ResourceLeak {
   public static int MAX_RECORDS;
   public AtomicBoolean freed;
   public Deque<String> lastRecords;
   public String creationRecord;
   public ChunkCompileTaskGenerator __junk6467141626052348836;
   public SpdySession$PendingWrite __junk4125742495694962642;
   public ResourceLeakDetector<T>.DefaultResourceLeak prev;
   public ResourceLeakDetector<T>.DefaultResourceLeak next;
   public BlockPos$2 __junk8984616242530345130;

   @Override
   public void record() {
      if (this.creationRecord != null) {
         String var1 = ResourceLeakDetector.newRecord(2);
         synchronized (this.lastRecords) {
            int var3 = this.lastRecords.size();
            if (var3 == 0 || !this.lastRecords.getLast().equals(var1)) {
               this.lastRecords.add(var1);
            }

            if (var3 > 4) {
               this.lastRecords.removeFirst();
            }
         }
      }
   }

   public ResourceLeakDetector$DefaultResourceLeak(ResourceLeakDetector var1, Object var2) {
      this.this$0 = var1;
      super(var2, var2 != null ? ResourceLeakDetector.access$200(var1) : null);
      this.lastRecords = new ArrayDeque<>();
      if (var2 != null) {
         ResourceLeakDetector$Level var3 = ResourceLeakDetector.getLevel();
         if (var3.ordinal() >= ResourceLeakDetector$Level.ADVANCED.ordinal()) {
            this.creationRecord = ResourceLeakDetector.newRecord(3);
         } else {
            this.creationRecord = null;
         }

         synchronized (ResourceLeakDetector.access$300(var1)) {
            this.prev = ResourceLeakDetector.access$300(var1);
            this.next = ResourceLeakDetector.access$300(var1).next;
            ResourceLeakDetector.access$300(var1).next.prev = this;
            ResourceLeakDetector.access$300(var1).next = this;
            ResourceLeakDetector.access$408(var1);
         }

         this.freed = new AtomicBoolean();
      } else {
         this.creationRecord = null;
         this.freed = new AtomicBoolean(true);
      }
   }

   @Override
   public String toString() {
      if (this.creationRecord == null) {
         return "";
      } else {
         Object[] var1;
         synchronized (this.lastRecords) {
            var1 = this.lastRecords.toArray();
         }

         StringBuilder var5 = new StringBuilder(16384);
         var5.append(StringUtil.NEWLINE);
         var5.append("Recent access records: ");
         var5.append(var1.length);
         var5.append(StringUtil.NEWLINE);
         if (var1.length > 0) {
            for (int var3 = var1.length - 1; var3 >= 0; var3--) {
               var5.append('#');
               var5.append(var3 + 1);
               var5.append(':');
               var5.append(StringUtil.NEWLINE);
               var5.append(var1[var3]);
            }
         }

         var5.append("Created at:");
         var5.append(StringUtil.NEWLINE);
         var5.append(this.creationRecord);
         var5.setLength(var5.length() - StringUtil.NEWLINE.length());
         return var5.toString();
      }
   }

   @Override
   public boolean close() {
      if (this.freed.compareAndSet(false, true)) {
         synchronized (ResourceLeakDetector.access$300(this.this$0)) {
            ResourceLeakDetector.access$410(this.this$0);
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.prev = null;
            this.next = null;
            return true;
         }
      } else {
         return false;
      }
   }
}
