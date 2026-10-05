package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.GuiLanguage$List;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.command.PlayerSelector$11;
import net.minecraft.entity.EntityHanging;
import org.jboss.marshalling.ByteInput;

public class ChannelBufferByteInput implements ByteInput {
   public GuiLanguage$List __junk3708824244335296443;
   public EntityHanging __junk7503703489397071384;
   public LayerArmorBase __junk8291404583272217892;
   public ByteBuf buffer;
   public PlayerSelector$11 __junk7861800124728818924;

   public long skip(long var1) {
      int var3 = this.buffer.readableBytes();
      if (var3 < var1) {
         var1 = var3;
      }

      this.buffer.readerIndex((int)(this.buffer.readerIndex() + var1));
      return var1;
   }

   public int available() {
      return this.buffer.readableBytes();
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public int read(byte[] var1, int var2, int var3) {
      int var4 = this.available();
      if (var4 == 0) {
         return -1;
      } else {
         var3 = Math.min(var4, var3);
         this.buffer.readBytes(var1, var2, var3);
         return var3;
      }
   }

   public void close() {
   }

   public ChannelBufferByteInput(ByteBuf var1) {
      this.buffer = var1;
   }

   public int read() {
      return this.buffer.isReadable() ? this.buffer.readByte() & 0xFF : -1;
   }
}
