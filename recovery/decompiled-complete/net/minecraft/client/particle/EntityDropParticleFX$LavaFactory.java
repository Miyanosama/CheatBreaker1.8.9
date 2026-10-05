package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.renderer.block.model.ModelBlock$Deserializer;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryAbstractCellEditor;
import recovered.unidentified.UnidentifiedClass4657;

public class EntityDropParticleFX$LavaFactory implements IParticleFactory {
   public ModelBlock$Deserializer field_0002;
   public EntityPickupFX field_0004;
   public CategoryAbstractCellEditor field_0001;
   public GuiSleepMP field_0003;
   public UnidentifiedClass4657 field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityDropParticleFX(var2, var3, var5, var7, Material.lava);
   }
}
