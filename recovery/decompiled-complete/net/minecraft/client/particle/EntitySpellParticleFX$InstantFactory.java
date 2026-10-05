package net.minecraft.client.particle;

import net.minecraft.item.Item$8;
import net.minecraft.item.ItemRecord;
import net.minecraft.world.World;
import net.minecraft.world.storage.SaveHandler;

public class EntitySpellParticleFX$InstantFactory implements IParticleFactory {
   public SaveHandler field_0001;
   public Item$8 field_0002;
   public ItemRecord field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      EntitySpellParticleFX var16 = new EntitySpellParticleFX(var2, var3, var5, var7, var9, var11, var13);
      var16.setBaseSpellTextureIndex(144);
      return var16;
   }
}
