package net.minecraft.world;

import io.netty.channel.ThreadPerChannelEventLoop$1;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.optifine.model.QuadBounds$1;
import recovered.unidentified.UnidentifiedClass1583;

public class LockCode {
   public UnidentifiedClass1583 field_0003;
   public static LockCode EMPTY_CODE = new LockCode("");
   public ThreadPerChannelEventLoop$1 field_0002;
   public QuadBounds$1 field_0004;
   public String lock;
   public ClippingHelper field_0001;

   public LockCode(String var1) {
      this.lock = var1;
   }

   public void toNBT(NBTTagCompound var1) {
      var1.setString("Lock", this.lock);
   }

   public boolean isEmpty() {
      return this.lock == null || this.lock.isEmpty();
   }

   public static LockCode fromNBT(NBTTagCompound var0) {
      if (var0.hasKey("Lock", 8)) {
         String var1 = var0.getString("Lock");
         return new LockCode(var1);
      } else {
         return EMPTY_CODE;
      }
   }

   public String getLock() {
      return this.lock;
   }
}
