package net.minecraft.client.particle;

import io.netty.handler.codec.http.DefaultFullHttpRequest;
import net.minecraft.client.renderer.BlockModelRenderer$1;
import net.minecraft.world.World;
import org.apache.log4j.Priority;
import recovered.unidentified.UnidentifiedClass3204;

public class MobAppearance$Factory implements IParticleFactory {
   public Priority field_0001;
   public DefaultFullHttpRequest field_0003;
   public UnidentifiedClass3204 field_0000;
   public BlockModelRenderer$1 field_0002;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new MobAppearance(var2, var3, var5, var7);
   }
}
