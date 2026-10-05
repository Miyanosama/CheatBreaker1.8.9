package io.netty.channel.nio;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$FitSimpleRoomHelper;

public class AbstractNioByteChannel$1 implements Runnable {
   public EnumCreatureType __junk8850169620946067514;
   public StructureOceanMonumentPieces$FitSimpleRoomHelper __junk1525951698217491478;
   public S1BPacketEntityAttach __junk6392466871006069569;

   @Override
   public void run() {
      this.this$0.flush();
   }

   public AbstractNioByteChannel$1(AbstractNioByteChannel var1) {
      this.this$0 = var1;
      super();
   }
}
