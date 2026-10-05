package io.netty.channel.epoll;

import net.minecraft.client.model.ModelHorse;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$1;

public class Native$NativeInetAddress {
   public StructureMineshaftPieces$1 __junk73556184144523567;
   public byte[] address;
   public ModelHorse __junk5862732317580902574;
   public S2APacketParticles __junk1553856662803266151;
   public int scopeId;

   public Native$NativeInetAddress(byte[] var1, int var2) {
      this.address = var1;
      this.scopeId = var2;
   }

   public Native$NativeInetAddress(byte[] var1) {
      this(var1, 0);
   }
}
