package net.minecraft.client.particle;

import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.spdy.DefaultSpdyRstStreamFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.world.World;
import net.optifine.config.Matches;

public class EntityLargeExplodeFX$Factory implements IParticleFactory {
   public AbstractEpollChannel field_0002;
   public UserListOpsEntry field_0004;
   public DefaultSpdyRstStreamFrame field_0001;
   public HttpResponseStatus field_0003;
   public Matches field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityLargeExplodeFX(Minecraft.getMinecraft().getTextureManager(), var2, var3, var5, var7, var9, var11, var13);
   }
}
