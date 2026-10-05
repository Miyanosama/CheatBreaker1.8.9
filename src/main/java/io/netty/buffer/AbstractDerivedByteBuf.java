package io.netty.buffer;

import java.nio.ByteBuffer;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.realms.RealmsSharedConstants;

public abstract class AbstractDerivedByteBuf extends AbstractByteBuf {

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      return this.nioBuffer(var1, var2);
   }

   @Override
   public boolean release() {
      return this.unwrap().release();
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      return this.unwrap().nioBuffer(var1, var2);
   }

   public AbstractDerivedByteBuf(int var1) {
      super(var1);
   }

   @Override
   public boolean release(int var1) {
      return this.unwrap().release(var1);
   }

   @Override
   public ByteBuf retain() {
      this.unwrap().retain();
      return this;
   }

   @Override
   public int refCnt() {
      return this.unwrap().refCnt();
   }

   @Override
   public ByteBuf retain(int var1) {
      this.unwrap().retain(var1);
      return this;
   }
}
