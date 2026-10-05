package com.jagrosh.discordipc.entities.pipe;

public enum PipeStatus {
   field_0003,
   field_0005,
   field_0002,
   field_0000,
   field_0001;
   // $VF: synthetic field
   public static PipeStatus[] field_0004 = new PipeStatus[]{field_0003, PipeStatus.field_0001, PipeStatus.field_0000, field_0002, field_0005};

   public static PipeStatus method_13419(String var0) {
      return Enum.valueOf(PipeStatus.class, var0);
   }
}
