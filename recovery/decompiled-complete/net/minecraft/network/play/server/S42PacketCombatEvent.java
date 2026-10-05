package net.minecraft.network.play.server;

import io.netty.handler.codec.http.DefaultHttpObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.CombatTracker;

public class S42PacketCombatEvent implements Packet<INetHandlerPlayClient> {
   public String deathMessage;
   public int field_179775_c;
   public S42PacketCombatEvent$Event eventType;
   public int field_179772_d;
   public DefaultHttpObject field_0000;
   public int field_179774_b;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeEnumValue(this.eventType);
      if (this.eventType == S42PacketCombatEvent$Event.END_COMBAT) {
         var1.writeVarIntToBuffer(this.field_179772_d);
         var1.writeInt(this.field_179775_c);
      } else if (this.eventType == S42PacketCombatEvent$Event.ENTITY_DIED) {
         var1.writeVarIntToBuffer(this.field_179774_b);
         var1.writeInt(this.field_179775_c);
         var1.writeString(this.deathMessage);
      }
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCombatEvent(this);
   }

   public S42PacketCombatEvent() {
   }

   public S42PacketCombatEvent(CombatTracker var1, S42PacketCombatEvent$Event var2) {
      this.eventType = var2;
      EntityLivingBase var3 = var1.func_94550_c();
      switch (S42PacketCombatEvent$1.field_179944_a[var2.ordinal()]) {
         case 1:
            this.field_179772_d = var1.func_180134_f();
            this.field_179775_c = var3 == null ? -1 : var3.F();
            break;
         case 2:
            this.field_179774_b = var1.getFighter().F();
            this.field_179775_c = var3 == null ? -1 : var3.F();
            this.deathMessage = var1.getDeathMessage().getUnformattedText();
      }
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.eventType = var1.readEnumValue(S42PacketCombatEvent$Event.class);
      if (this.eventType == S42PacketCombatEvent$Event.END_COMBAT) {
         this.field_179772_d = var1.readVarIntFromBuffer();
         this.field_179775_c = var1.readInt();
      } else if (this.eventType == S42PacketCombatEvent$Event.ENTITY_DIED) {
         this.field_179774_b = var1.readVarIntFromBuffer();
         this.field_179775_c = var1.readInt();
         this.deathMessage = var1.readStringFromBuffer(32767);
      }
   }
}
