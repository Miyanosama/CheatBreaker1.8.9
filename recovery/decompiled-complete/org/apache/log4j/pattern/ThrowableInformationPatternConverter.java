package org.apache.log4j.pattern;

import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import net.minecraft.client.renderer.chunk.ListChunkFactory;
import net.minecraft.server.integrated.IntegratedServer$3;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.structure.StructureVillagePieces$1;
import net.optifine.ConnectedTextures;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

public class ThrowableInformationPatternConverter extends LoggingEventPatternConverter {
   public StructureVillagePieces$1 field_0003;
   public InsecureTrustManagerFactory field_0005;
   public IntegratedServer$3 field_0002;
   public int maxLines = Integer.MAX_VALUE;
   public WorldGenCactus field_0000;
   public ListChunkFactory field_0001;
   public ConnectedTextures field_0006;

   public ThrowableInformationPatternConverter(String[] var1) {
      super("Throwable", "throwable");
      if (var1 != null && var1.length > 0) {
         if ("none".equals(var1[0])) {
            this.maxLines = 0;
         } else if ("short".equals(var1[0])) {
            this.maxLines = 1;
         } else {
            try {
               this.maxLines = Integer.parseInt(var1[0]);
            } catch (NumberFormatException var3) {
            }
         }
      }
   }

   public boolean handlesThrowable() {
      return true;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      if (this.maxLines != 0) {
         ThrowableInformation var3 = var1.getThrowableInformation();
         if (var3 != null) {
            String[] var4 = var3.getThrowableStrRep();
            int var5 = var4.length;
            if (this.maxLines < 0) {
               var5 += this.maxLines;
            } else if (var5 > this.maxLines) {
               var5 = this.maxLines;
            }

            for (int var6 = 0; var6 < var5; var6++) {
               String var7 = var4[var6];
               var2.append(var7).append("\n");
            }
         }
      }
   }

   public static ThrowableInformationPatternConverter newInstance(String[] var0) {
      return new ThrowableInformationPatternConverter(var0);
   }
}
