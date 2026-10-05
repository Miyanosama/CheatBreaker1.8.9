package net.minecraft.client.renderer.chunk;

import io.netty.channel.sctp.oio.OioSctpChannel$2;
import net.minecraft.client.particle.EntityHeartFX$Factory;
import org.apache.log4j.spi.DefaultRepositorySelector;
import recovered.unidentified.UnidentifiedClass4790;

public enum ChunkCompileTaskGenerator$Status {
   DONE,
   COMPILING,
   UPLOADING,
   PENDING;

   public OioSctpChannel$2 field_0004;
   // $VF: synthetic field
   public static ChunkCompileTaskGenerator$Status[] $VALUES = new ChunkCompileTaskGenerator$Status[]{
      ChunkCompileTaskGenerator$Status.PENDING, ChunkCompileTaskGenerator$Status.COMPILING, ChunkCompileTaskGenerator$Status.UPLOADING, DONE
   };
   public DefaultRepositorySelector field_0006;
   public EntityHeartFX$Factory field_0001;
   public UnidentifiedClass4790 field_0002;
}
