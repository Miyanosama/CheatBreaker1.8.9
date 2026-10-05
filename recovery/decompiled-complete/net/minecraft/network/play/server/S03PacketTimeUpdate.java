package net.minecraft.network.play.server;

import io.netty.handler.codec.compression.JdkZlibEncoder$2;
import io.netty.handler.codec.string.StringEncoder;
import net.minecraft.client.gui.GuiCustomizeWorldScreen$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import org.apache.log4j.helpers.PatternParser$MDCPatternConverter;

public class S03PacketTimeUpdate implements Packet<INetHandlerPlayClient> {
   public StringEncoder field_0003;
   public long worldTime;
   public PatternParser$MDCPatternConverter field_0002;
   public long totalWorldTime;
   public JdkZlibEncoder$2 field_0000;
   public GuiCustomizeWorldScreen$1 field_0001;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTimeUpdate(this);
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.totalWorldTime = var1.readLong();
      this.worldTime = var1.readLong();
   }

   public S03PacketTimeUpdate(long var1, long var3, boolean var5) {
      this.totalWorldTime = var1;
      this.worldTime = var3;
      if (!var5) {
         this.worldTime = -this.worldTime;
         if (this.worldTime == (34898360L & -9208077738840156096L)) {
            this.worldTime = -1L & -1L;
         }
      }
   }

   public S03PacketTimeUpdate() {
   }

   public long getTotalWorldTime() {
      return this.totalWorldTime;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeLong(this.totalWorldTime);
      var1.writeLong(this.worldTime);
   }
}
