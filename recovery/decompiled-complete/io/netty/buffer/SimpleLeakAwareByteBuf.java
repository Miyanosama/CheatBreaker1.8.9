package io.netty.buffer;

import io.netty.util.ResourceLeak;
import java.nio.ByteOrder;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonEyes;
import net.minecraft.network.NetworkManager;
import net.optifine.render.VboRegion;

public class SimpleLeakAwareByteBuf extends WrappedByteBuf {
   public VboRegion __junk1124428263037077941;
   public NetworkManager __junk2638100982810071480;
   public ResourceLeak leak;
   public LayerEnderDragonEyes __junk1972055529889303378;

   @Override
   public ByteBuf duplicate() {
      return new SimpleLeakAwareByteBuf(super.duplicate(), this.leak);
   }

   @Override
   public boolean release() {
      boolean var1 = super.release();
      if (var1) {
         this.leak.close();
      }

      return var1;
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      this.leak.record();
      return this.order() == var1 ? this : new SimpleLeakAwareByteBuf(super.order(var1), this.leak);
   }

   public SimpleLeakAwareByteBuf(ByteBuf var1, ResourceLeak var2) {
      super(var1);
      this.leak = var2;
   }

   @Override
   public ByteBuf readSlice(int var1) {
      return new SimpleLeakAwareByteBuf(super.readSlice(var1), this.leak);
   }

   @Override
   public ByteBuf slice() {
      return new SimpleLeakAwareByteBuf(super.slice(), this.leak);
   }

   @Override
   public boolean release(int var1) {
      boolean var2 = super.release(var1);
      if (var2) {
         this.leak.close();
      }

      return var2;
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return new SimpleLeakAwareByteBuf(super.slice(var1, var2), this.leak);
   }
}
