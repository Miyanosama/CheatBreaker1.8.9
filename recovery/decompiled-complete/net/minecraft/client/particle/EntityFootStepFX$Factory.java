package net.minecraft.client.particle;

import com.cheatbreaker.client.module.type.BlockOverlayModule;
import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class EntityFootStepFX$Factory implements IParticleFactory {
   public PipeStatus field_0000;
   public BlockOverlayModule field_0001;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityFootStepFX(Minecraft.getMinecraft().getTextureManager(), var2, var3, var5, var7);
   }
}
