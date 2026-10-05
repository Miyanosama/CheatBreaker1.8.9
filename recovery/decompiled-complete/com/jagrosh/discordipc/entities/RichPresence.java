package com.jagrosh.discordipc.entities;

import java.time.OffsetDateTime;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.optifine.texture.PixelType;
import org.json.JSONArray;
import org.json.JSONObject;

public class RichPresence {
   public String field_0008;
   public String field_0015;
   public String field_0007;
   public String field_0013;
   public String field_0002;
   public String field_0003;
   public S26PacketMapChunkBulk field_0016;
   public PixelType field_0011;
   public String field_0004;
   public int field_0017;
   public String field_0001;
   public String field_0009;
   public OffsetDateTime field_0010;
   public int field_0006;
   public boolean field_0012;
   public OffsetDateTime field_0014;
   public String field_0000;
   public RichPresence$Builder field_0005;

   public JSONObject method_13367() {
      return new JSONObject()
         .put("state", this.field_0000)
         .put("details", this.field_0008)
         .put(
            "timestamps",
            new JSONObject()
               .put("start", this.field_0014 == null ? null : this.field_0014.toEpochSecond())
               .put("end", this.field_0010 == null ? null : this.field_0010.toEpochSecond())
         )
         .put(
            "assets",
            new JSONObject()
               .put("large_image", this.field_0002)
               .put("large_text", this.field_0013)
               .put("small_image", this.field_0015)
               .put("small_text", this.field_0004)
         )
         .put(
            "party",
            this.field_0009 == null
               ? null
               : new JSONObject().put("id", this.field_0009).put("size", new JSONArray().method_08565(this.field_0006).method_08565(this.field_0017))
         )
         .put("secrets", new JSONObject().put("join", this.field_0007).put("spectate", this.field_0001).put("match", this.field_0003))
         .method_07225("instance", this.field_0012);
   }

   public RichPresence(
      String var1,
      String var2,
      OffsetDateTime var3,
      OffsetDateTime var4,
      String var5,
      String var6,
      String var7,
      String var8,
      String var9,
      int var10,
      int var11,
      String var12,
      String var13,
      String var14,
      boolean var15
   ) {
      this.field_0000 = var1;
      this.field_0008 = var2;
      this.field_0014 = var3;
      this.field_0010 = var4;
      this.field_0002 = var5;
      this.field_0013 = var6;
      this.field_0015 = var7;
      this.field_0004 = var8;
      this.field_0009 = var9;
      this.field_0006 = var10;
      this.field_0017 = var11;
      this.field_0003 = var12;
      this.field_0007 = var13;
      this.field_0001 = var14;
      this.field_0012 = var15;
   }
}
