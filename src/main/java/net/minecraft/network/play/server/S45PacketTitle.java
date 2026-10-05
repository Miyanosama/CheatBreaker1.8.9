package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S45PacketTitle implements Packet<INetHandlerPlayClient> {
   public IChatComponent message;
   public S45PacketTitle.Type type;
   public int fadeInTime;
   public int fadeOutTime;
   public int displayTime;

   public int getFadeOutTime() {
      return this.fadeOutTime;
   }

   public IChatComponent getMessage() {
      return this.message;
   }

   public S45PacketTitle() {
   }

   public int getDisplayTime() {
      return this.displayTime;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTitle(this);
   }

   public int getFadeInTime() {
      return this.fadeInTime;
   }

   public S45PacketTitle(int var1, int var2, int var3) {
      this(S45PacketTitle.Type.TIMES, (IChatComponent)null, var1, var2, var3);
   }

   public S45PacketTitle(S45PacketTitle.Type var1, IChatComponent var2) {
      this(var1, var2, -1, -1, -1);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.type = var1.readEnumValue(S45PacketTitle.Type.class);
      if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE) {
         this.message = var1.readChatComponent();
      }

      if (this.type == S45PacketTitle.Type.TIMES) {
         this.fadeInTime = var1.readInt();
         this.displayTime = var1.readInt();
         this.fadeOutTime = var1.readInt();
      }
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeEnumValue(this.type);
      if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE) {
         var1.writeChatComponent(this.message);
      }

      if (this.type == S45PacketTitle.Type.TIMES) {
         var1.writeInt(this.fadeInTime);
         var1.writeInt(this.displayTime);
         var1.writeInt(this.fadeOutTime);
      }
   }

   public S45PacketTitle(S45PacketTitle.Type var1, IChatComponent var2, int var3, int var4, int var5) {
      this.type = var1;
      this.message = var2;
      this.fadeInTime = var3;
      this.displayTime = var4;
      this.fadeOutTime = var5;
   }

   public S45PacketTitle.Type getType() {
      return this.type;
   }

   public static enum Type {
      TITLE,
      SUBTITLE,
      TIMES,
      CLEAR,
      RESET;
      // $VF: synthetic field
      public static S45PacketTitle.Type[] $VALUES = new S45PacketTitle.Type[]{S45PacketTitle.Type.TITLE, S45PacketTitle.Type.SUBTITLE, TIMES, CLEAR, RESET};

      public static String[] getNames() {
         String[] var0 = new String[values().length];
         int var1 = 0;

         for (S45PacketTitle.Type var5 : values()) {
            var0[var1++] = var5.name().toLowerCase();
         }

         return var0;
      }

      public static S45PacketTitle.Type byName(String var0) {
         for (S45PacketTitle.Type var4 : values()) {
            if (var4.name().equalsIgnoreCase(var0)) {
               return var4;
            }
         }

         return TITLE;
      }
   }
}
