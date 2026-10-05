package recovered.unidentified;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.client.renderer.entity.RenderPotion;
import net.minecraft.client.stream.IngestServerTester$2;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.biome.BiomeGenBase$1;
import org.apache.log4j.helpers.CountingQuietWriter;

// $VF: synthetic class
public class UnidentifiedClass4816 {
   public IngestServerTester$2 field_0003;
   public BlockRedstoneWire field_0005;
   public BiomeGenBase$1 field_0004;
   public EntityAIMate field_0000;
   public RenderPotion field_0001;
   public CountingQuietWriter field_0006;

   static {
      try {
         field_0002[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0002[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0002[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0002[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
