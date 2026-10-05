package net.minecraft.network.play.server;

import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import io.netty.util.concurrent.DefaultPromise$3;
import net.minecraft.client.particle.EntityLavaFX;
import recovered.unidentified.UnidentifiedClass0631;
import recovered.unidentified.UnidentifiedClass3386;

public enum S3CPacketUpdateScore$Action {
   CHANGE,
   REMOVE;

   public UnidentifiedClass3386 field_0003;
   public EntityLavaFX field_0006;
   public SocksInitRequestDecoder field_0000;
   public UnidentifiedClass0631 field_0007;
   public DefaultPromise$3 field_0004;
}
