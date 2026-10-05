package net.minecraft.client.particle;

import com.cheatbreaker.client.module.type.CoordinatesModule;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import io.netty.util.HashedWheelTimer$1;
import net.minecraft.block.BlockSilverfish$EnumType$4;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass4074;

public class EntityAuraFX$Factory implements IParticleFactory {
   public BlockSilverfish$EnumType$4 field_0002;
   public UnidentifiedClass4074 field_0004;
   public CoordinatesModule field_0001;
   public NoDiscordClientException field_0003;
   public HashedWheelTimer$1 field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityAuraFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
