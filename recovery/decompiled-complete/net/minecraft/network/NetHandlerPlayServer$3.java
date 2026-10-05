package net.minecraft.network;

import io.netty.buffer.SimpleLeakAwareByteBuf;
import io.netty.channel.local.LocalChannel$5;
import java.util.concurrent.Callable;
import net.minecraft.client.main.llIlllIIlllIIllIIlllIlIII;
import net.minecraft.world.storage.WorldInfo$8;
import net.optifine.shaders.config.ShaderParser;

public class NetHandlerPlayServer$3 implements Callable<String> {
   public LocalChannel$5 field_0003;
   public WorldInfo$8 field_0005;
   public llIlllIIlllIIllIIlllIlIII field_0002;
   public ShaderParser field_0004;
   public SimpleLeakAwareByteBuf field_0000;

   public String call() {
      return this.field_180227_a.getClass().getCanonicalName();
   }

   public NetHandlerPlayServer$3(NetHandlerPlayServer var1, Packet var2) {
      this.field_180226_b = var1;
      this.field_180227_a = var2;
      super();
   }
}
