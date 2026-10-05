package net.optifine;

import io.netty.channel.ChannelPipelineException;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntitySilverfish$AIHideInStone;
import net.optifine.http.IFileUploadListener;

public class CrashReporter$1 implements IFileUploadListener {
   public EntitySilverfish$AIHideInStone field_0001;
   public ChannelPipelineException field_0003;
   public EntityEnderPearl field_0000;
   public EntityReddustFX field_0002;

   @Override
   public void fileUploadFinished(String var1, byte[] var2, Throwable var3) {
   }
}
