package net.minecraft.client.particle;

import javax.vecmath.Quat4d;
import javazoom.jl.converter.WaveFile$WaveFileSample;
import net.minecraft.block.Block;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogTableColumn;
import recovered.unidentified.UnidentifiedClass1437;

public class EntityDiggingFX$Factory implements IParticleFactory {
   public WaveFile$WaveFileSample field_0002;
   public Quat4d field_0004;
   public ContainerMerchant field_0001;
   public UnidentifiedClass1437 field_0003;
   public LogTableColumn field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityDiggingFX(var2, var3, var5, var7, var9, var11, var13, Block.getStateById(var15[0])).func_174845_l();
   }
}
