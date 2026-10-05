package io.netty.handler.codec.compression;

import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.sctp.SctpMessageCompletionHandler;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;

public class DecompressionException extends DecoderException {
   public static final long serialVersionUID = 3546272712208105199L;

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
