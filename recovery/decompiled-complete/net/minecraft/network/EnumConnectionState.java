package net.minecraft.network;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import java.util.Map;
import javazoom.jl.decoder.LayerIIIDecoder$temporaire;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.inventory.Slot;
import org.apache.log4j.chainsaw.MyTableModel$Processor;
import org.apache.logging.log4j.LogManager;

public enum EnumConnectionState {
   HANDSHAKING(-1),
   PLAY(0),
   LOGIN(2),
   STATUS(1);

   // $VF: synthetic field
   public static EnumConnectionState[] $VALUES = new EnumConnectionState[]{
      EnumConnectionState.HANDSHAKING, EnumConnectionState.PLAY, EnumConnectionState.STATUS, EnumConnectionState.LOGIN
   };
   public ModelMagmaCube field_0012;
   public static EnumConnectionState[] STATES_BY_ID = new EnumConnectionState[EnumConnectionState.field_181137_f - EnumConnectionState.field_181136_e + 1];
   public static int field_181137_f = 2;
   public int id;
   public LayerIIIDecoder$temporaire field_0003;
   public static int field_181136_e = -1;
   public MyTableModel$Processor field_0000;
   public static Map<Class<? extends Packet>, EnumConnectionState> STATES_BY_CLASS = Maps.newHashMap();
   public Map<EnumPacketDirection, BiMap<Integer, Class<? extends Packet>>> directionMaps = Maps.newEnumMap(EnumPacketDirection.class);
   public Slot field_0010;

   public EnumConnectionState registerPacket(EnumPacketDirection var1, Class<? extends Packet> var2) {
      Object var3 = this.directionMaps.get(var1);
      if (var3 == null) {
         var3 = HashBiMap.create();
         this.directionMaps.put(var1, (BiMap<Integer, Class<? extends Packet>>)var3);
      }

      if (var3.containsValue(var2)) {
         String var4 = var1 + " packet " + var2 + " is already known to ID " + var3.inverse().get(var2);
         LogManager.getLogger().fatal(var4);
         throw new IllegalArgumentException(var4);
      } else {
         var3.put(var3.size(), var2);
         return this;
      }
   }

   public static EnumConnectionState getById(int var0) {
      return var0 >= field_181136_e && var0 <= field_181137_f ? STATES_BY_ID[var0 - field_181136_e] : null;
   }

   public static EnumConnectionState getFromPacket(Packet var0) {
      return STATES_BY_CLASS.get(var0.getClass());
   }

   public Integer getPacketId(EnumPacketDirection var1, Packet var2) {
      return (Integer)this.directionMaps.get(var1).inverse().get(var2.getClass());
   }

   public int getId() {
      return this.id;
   }

   public Packet getPacket(EnumPacketDirection var1, int var2) {
      Class var3 = (Class)this.directionMaps.get(var1).get(var2);
      return var3 == null ? null : (Packet)var3.newInstance();
   }

   static {
      for (EnumConnectionState var3 : values()) {
         int var4 = var3.getId();
         if (var4 < field_181136_e || var4 > field_181137_f) {
            throw new Error("Invalid protocol ID " + Integer.toString(var4));
         }

         STATES_BY_ID[var4 - field_181136_e] = var3;

         for (EnumPacketDirection var6 : var3.directionMaps.keySet()) {
            for (Class var8 : var3.directionMaps.get(var6).values()) {
               if (STATES_BY_CLASS.containsKey(var8) && STATES_BY_CLASS.get(var8) != var3) {
                  throw new Error("Packet " + var8 + " is already assigned to protocol " + STATES_BY_CLASS.get(var8) + " - can't reassign to " + var3);
               }

               try {
                  var8.newInstance();
               } catch (Throwable var10) {
                  throw new Error("Packet " + var8 + " fails instantiation checks! " + var8);
               }

               STATES_BY_CLASS.put(var8, var3);
            }
         }
      }
   }

   public EnumConnectionState(int var3) {
      this.id = var3;
   }
}
