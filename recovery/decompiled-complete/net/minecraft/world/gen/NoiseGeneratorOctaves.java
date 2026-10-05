package net.minecraft.world.gen;

import com.cheatbreaker.client.ui.overlay.element.RadioElement;
import io.netty.channel.ChannelDuplexHandler;
import java.util.Random;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.MathHelper;
import org.java_websocket.server.WebSocketServer;

public class NoiseGeneratorOctaves extends NoiseGenerator {
   public ChannelDuplexHandler field_0003;
   public TileEntityHopper field_0005;
   public RadioElement field_0002;
   public NoiseGeneratorImproved[] generatorCollection;
   public RenderWither field_0000;
   public int octaves;
   public WebSocketServer field_0006;

   public NoiseGeneratorOctaves(Random var1, int var2) {
      this.octaves = var2;
      this.generatorCollection = new NoiseGeneratorImproved[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         this.generatorCollection[var3] = new NoiseGeneratorImproved(var1);
      }
   }

   public double[] generateNoiseOctaves(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7, double var8, double var10, double var12) {
      if (var1 == null) {
         var1 = new double[var5 * var6 * var7];
      } else {
         for (int var14 = 0; var14 < var1.length; var14++) {
            var1[var14] = 0.0;
         }
      }

      double var27 = 1.0;

      for (int var16 = 0; var16 < this.octaves; var16++) {
         double var17 = var2 * var27 * var8;
         double var19 = var3 * var27 * var10;
         double var21 = var4 * var27 * var12;
         long var23 = MathHelper.floor_double_long(var17);
         long var25 = MathHelper.floor_double_long(var21);
         var17 -= var23;
         var21 -= var25;
         var23 %= -908892186015035224L & 151880192L;
         var25 %= 2907025769793348736L & 16810037L;
         var17 += var23;
         var21 += var25;
         this.generatorCollection[var16].populateNoiseArray(var1, var17, var19, var21, var5, var6, var7, var8 * var27, var10 * var27, var12 * var27, var27);
         var27 /= 2.0;
      }

      return var1;
   }

   public double[] generateNoiseOctaves(double[] var1, int var2, int var3, int var4, int var5, double var6, double var8, double var10) {
      return this.generateNoiseOctaves(var1, var2, 10, var3, var4, 1, var5, var6, 1.0, var8);
   }
}
