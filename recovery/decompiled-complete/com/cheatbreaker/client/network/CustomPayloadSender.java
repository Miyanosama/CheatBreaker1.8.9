package com.cheatbreaker.client.network;

import com.cheatbreaker.client.CheatBreaker;
import java.io.IOException;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import org.json.XML;

public class CustomPayloadSender implements Packet<INetHandlerPlayServer> {
   public BlockWorldState field_0001;
   public PacketBuffer field_0003;
   public XML field_0000;
   public String field_0002;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.field_0002);
      var1.writeBytes(this.field_0003);
   }

   public void method_28756(INetHandlerPlayServer var1) {
      var1.method_29428(this);
   }

   public CustomPayloadSender(String var1, PacketBuffer var2) {
      this.field_0002 = var1;
      this.field_0003 = var2;
      if (var2.writerIndex() > 32767) {
         throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
      } else {
         if (CheatBreaker.getInstance().getGlobalSettings().field_0062) {
            System.out.println("Sending Custom Payload with Channel: " + var1);
            if (var1.equals("REGISTER")) {
               System.out.println("Channel: " + new String(var2.array()));
            }
         }
      }
   }

   public CustomPayloadSender() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.field_0002 = var1.readStringFromBuffer(20);
      int var2 = var1.readableBytes();
      if (var2 >= 0 && var2 <= 32767) {
         this.field_0003 = new PacketBuffer(var1.readBytes(var2));
      } else {
         throw new IOException("Payload may not be larger than 32767 bytes");
      }
   }

   public String method_28755() {
      return this.field_0002;
   }

   public PacketBuffer method_28757() {
      return this.field_0003;
   }
}
