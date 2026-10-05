package net.minecraft.client.particle;

import io.netty.bootstrap.Bootstrap$1;
import io.netty.handler.codec.spdy.SpdyFrameCodec;
import io.netty.util.concurrent.SingleThreadEventExecutor$PurgeTask;
import junit.swingui.TestHierarchyRunView$1;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass3436;

public class EntityFishWakeFX$Factory implements IParticleFactory {
   public UnidentifiedClass3436 field_0002;
   public Bootstrap$1 field_0004;
   public SpdyFrameCodec field_0001;
   public TestHierarchyRunView$1 field_0003;
   public SingleThreadEventExecutor$PurgeTask field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityFishWakeFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
