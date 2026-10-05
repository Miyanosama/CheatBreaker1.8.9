package org.java_websocket;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import javazoom.jl.player.advanced.jlap;
import net.minecraft.client.audio.SoundList$SoundEntry$Type;
import net.minecraft.item.ItemSeedFood;
import org.java_websocket.enums.Role;

public class SocketChannelIOHelper {
   public jlap field_0001;
   public SoundList$SoundEntry$Type field_0002;
   public ItemSeedFood field_0000;

   public static boolean batch(WebSocketImpl var0, ByteChannel var1) {
      if (var0 == null) {
         return false;
      } else {
         ByteBuffer var2 = var0.outQueue.peek();
         WrappedByteChannel var3 = null;
         if (var2 == null) {
            if (var1 instanceof WrappedByteChannel) {
               var3 = (WrappedByteChannel)var1;
               if (var3.isNeedWrite()) {
                  var3.writeMore();
               }
            }
         } else {
            do {
               var1.write(var2);
               if (var2.remaining() > 0) {
                  return false;
               }

               var0.outQueue.poll();
               var2 = var0.outQueue.peek();
            } while (var2 != null);
         }

         if (var0.outQueue.isEmpty()
            && var0.isFlushAndClose()
            && var0.getDraft() != null
            && var0.getDraft().getRole() != null
            && var0.getDraft().getRole() == Role.SERVER) {
            var0.closeConnection();
         }

         return var3 == null || !((WrappedByteChannel)var1).isNeedWrite();
      }
   }

   public SocketChannelIOHelper() {
      throw new IllegalStateException("Utility class");
   }

   public static boolean read(ByteBuffer var0, WebSocketImpl var1, ByteChannel var2) {
      ((Buffer)var0).clear();
      int var3 = var2.read(var0);
      ((Buffer)var0).flip();
      if (var3 == -1) {
         var1.eot();
         return false;
      } else {
         return var3 != 0;
      }
   }

   public static boolean readMore(ByteBuffer var0, WebSocketImpl var1, WrappedByteChannel var2) {
      ((Buffer)var0).clear();
      int var3 = var2.readMore(var0);
      ((Buffer)var0).flip();
      if (var3 == -1) {
         var1.eot();
         return false;
      } else {
         return var2.isNeedRead();
      }
   }
}
