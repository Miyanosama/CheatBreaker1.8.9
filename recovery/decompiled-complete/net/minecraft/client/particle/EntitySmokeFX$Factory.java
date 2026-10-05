package net.minecraft.client.particle;

import net.minecraft.world.World;
import net.optifine.entity.model.anim.RenderEntityParameterFloat$1;
import net.optifine.expr.TokenType;
import org.newsclub.net.unix.AFUNIXSocketImpl$AFUNIXOutputStream;

public class EntitySmokeFX$Factory implements IParticleFactory {
   public TokenType field_0001;
   public AFUNIXSocketImpl$AFUNIXOutputStream field_0002;
   public RenderEntityParameterFloat$1 field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntitySmokeFX(var2, var3, var5, var7, var9, var11, var13, null);
   }
}
