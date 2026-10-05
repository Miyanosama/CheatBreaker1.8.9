package io.netty.handler.codec;

import net.minecraft.block.BlockColored;
import net.minecraft.crash.CrashReport$5;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityVillager$ItemAndEmeraldToItem;

public class CodecException extends RuntimeException {
   public CrashReport$5 __junk8155230725584504068;
   public BlockColored __junk1124470567459874369;
   public EntityVillager$ItemAndEmeraldToItem __junk7314335380691418470;
   public static long serialVersionUID;
   public EnumCreatureType __junk6182151265897885237;

   public CodecException() {
   }

   public CodecException(String var1) {
      super(var1);
   }

   public CodecException(Throwable var1) {
      super(var1);
   }

   public CodecException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
