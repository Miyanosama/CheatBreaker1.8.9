package org.java_websocket.handshake;

import io.netty.handler.codec.compression.SnappyFramedDecoder$ChunkType;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockSkull$2;
import net.minecraft.server.management.PlayerProfileCache$Serializer;

public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {
   public String resourceDescriptor = "*";
   public SnappyFramedDecoder$ChunkType field_0004;
   public BlockSkull$2 field_0001;
   public PlayerProfileCache$Serializer field_0003;
   public BlockPane field_0000;

   @Override
   public String getResourceDescriptor() {
      return this.resourceDescriptor;
   }

   @Override
   public void setResourceDescriptor(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("http resource descriptor must not be null");
      } else {
         this.resourceDescriptor = var1;
      }
   }
}
