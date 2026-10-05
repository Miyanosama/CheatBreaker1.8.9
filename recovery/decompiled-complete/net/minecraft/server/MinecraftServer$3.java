package net.minecraft.server;

import io.netty.channel.AbstractChannel$AbstractUnsafe$6;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$TransferEncodingMechanism;
import java.util.concurrent.Callable;
import net.minecraft.block.BlockSandStone;

public class MinecraftServer$3 implements Callable<String> {
   public BlockSandStone field_0001;
   public AbstractChannel$AbstractUnsafe$6 field_0003;
   public HttpPostBodyUtil$TransferEncodingMechanism field_0000;

   public MinecraftServer$3(MinecraftServer var1) {
      this.field_73716_a = var1;
      super();
   }

   public String call() {
      return this.field_73716_a.theProfiler.profilingEnabled ? this.field_73716_a.theProfiler.getNameOfLastSection() : "N/A (disabled)";
   }
}
