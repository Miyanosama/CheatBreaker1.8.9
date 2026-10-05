package com.jagrosh.discordipc.entities;

import net.minecraft.nbt.NBTTagCompound$2;
import net.minecraft.network.ServerStatusResponse$PlayerCountData$Serializer;
import net.minecraft.world.storage.SaveDataMemoryStorage;
import recovered.unidentified.UnidentifiedClass4377;

public enum Packet$OpCode {
   field_0007,
   field_0003,
   field_0008,
   field_0002,
   field_0009;
   public SaveDataMemoryStorage field_0004;
   public UnidentifiedClass4377 field_0006;
   public ServerStatusResponse$PlayerCountData$Serializer field_0000;
   // $VF: synthetic field
   public static Packet$OpCode[] field_0001 = new Packet$OpCode[]{
      Packet$OpCode.field_0002, field_0007, field_0003, Packet$OpCode.field_0008, Packet$OpCode.field_0009
   };
   public NBTTagCompound$2 field_0005;

   public static Packet$OpCode method_13364(String var0) {
      return Enum.valueOf(Packet$OpCode.class, var0);
   }
}
