package net.minecraft.world.chunk.storage;

import io.netty.handler.codec.spdy.SpdySessionHandler$2;
import java.io.File;
import java.io.FilenameFilter;
import net.minecraft.world.storage.WorldInfo$6;

public class AnvilSaveConverter$1 implements FilenameFilter {
   public SpdySessionHandler$2 field_0001;
   public WorldInfo$6 field_0000;

   public AnvilSaveConverter$1(AnvilSaveConverter var1) {
      this.field_76172_a = var1;
      super();
   }

   @Override
   public boolean accept(File var1, String var2) {
      return var2.endsWith(".mcr");
   }
}
