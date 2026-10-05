package io.netty.handler.codec.compression;

import io.netty.buffer.PooledHeapByteBuf$1;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.sctp.SctpMessageCompletionHandler;
import net.minecraft.client.network.NetHandlerLoginClient$1;
import net.minecraft.client.stream.IngestServerTester$IngestTestState;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;

public class DecompressionException extends DecoderException {
   public NetHandlerLoginClient$1 __junk7596332557113780848;
   public BaseAttributeMap __junk4989801037901401600;
   public SctpMessageCompletionHandler __junk2048393744936554275;
   public PooledHeapByteBuf$1 __junk1841592045446891223;
   public EntityAIRunAroundLikeCrazy __junk2445339546296022267;
   public IngestServerTester$IngestTestState __junk2628048419939669797;
   public static long serialVersionUID;

   public DecompressionException() {
   }

   public DecompressionException(String var1) {
      super(var1);
   }

   public DecompressionException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public DecompressionException(Throwable var1) {
      super(var1);
   }
}
