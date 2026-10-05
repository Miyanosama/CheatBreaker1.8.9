package org.newsclub.net.unix;

import io.netty.buffer.PooledHeapByteBuf$1;
import io.netty.handler.codec.http.cors.CorsConfig$ConstantValueGenerator;
import java.net.SocketException;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.boss.EntityWither$1;
import net.minecraft.nbt.NBTSizeTracker$1;
import net.optifine.shaders.uniform.Smoother;

public class AFUNIXSocketImpl$Lenient extends AFUNIXSocketImpl {
   public EntityWither$1 field_0002;
   public NBTSizeTracker$1 field_0003;
   public CorsConfig$ConstantValueGenerator field_0004;
   public GLAllocation field_0005;
   public Smoother field_0001;
   public PooledHeapByteBuf$1 field_0000;

   @Override
   public Object getOption(int var1) {
      try {
         return super.getOption(var1);
      } catch (SocketException var3) {
         switch (var1) {
            case 1:
            case 8:
               return false;
            default:
               throw var3;
         }
      }
   }

   @Override
   public void setOption(int var1, Object var2) {
      try {
         super.setOption(var1, var2);
      } catch (SocketException var4) {
         switch (var1) {
            case 1:
               return;
            default:
               throw var4;
         }
      }
   }
}
