package net.minecraft.world.gen.structure;

import io.netty.channel.udt.nio.NioUdtProvider;
import java.util.Random;
import net.minecraft.client.gui.GuiMerchant$MerchantButton;
import net.minecraft.client.gui.spectator.SpectatorMenu$EndSpectatorObject;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import recovered.unidentified.UnidentifiedClass3499;

public class MapGenScatteredFeature$Start extends StructureStart {
   public GuiMerchant$MerchantButton field_0001;
   public NioUdtProvider field_0003;
   public SpectatorMenu$EndSpectatorObject field_0000;
   public C10PacketCreativeInventoryAction field_0002;

   public MapGenScatteredFeature$Start() {
   }

   public MapGenScatteredFeature$Start(World var1, Random var2, int var3, int var4) {
      super(var3, var4);
      BiomeGenBase var5 = var1.getBiomeGenForCoords(new BlockPos(var3 * 16 + 8, 0, var4 * 16 + 8));
      if (var5 == BiomeGenBase.jungle || var5 == BiomeGenBase.jungleHills) {
         ComponentScatteredFeaturePieces$JunglePyramid var8 = new ComponentScatteredFeaturePieces$JunglePyramid(var2, var3 * 16, var4 * 16);
         this.a.add(var8);
      } else if (var5 == BiomeGenBase.swampland) {
         UnidentifiedClass3499 var6 = new UnidentifiedClass3499(var2, var3 * 16, var4 * 16);
         this.a.add(var6);
      } else if (var5 == BiomeGenBase.desert || var5 == BiomeGenBase.desertHills) {
         ComponentScatteredFeaturePieces$DesertPyramid var7 = new ComponentScatteredFeaturePieces$DesertPyramid(var2, var3 * 16, var4 * 16);
         this.a.add(var7);
      }

      this.c();
   }
}
