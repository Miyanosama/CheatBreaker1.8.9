package com.jagrosh.discordipc.entities;

import java.time.OffsetDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

public class RichPresence {
   public String recoveredField3682;
   public String recoveredField3683;
   public String recoveredField3684;
   public String recoveredField3685;
   public String recoveredField3686;
   public String recoveredField3687;
   public String recoveredField3688;
   public int recoveredField3689;
   public String recoveredField3690;
   public String recoveredField3691;
   public OffsetDateTime recoveredField3692;
   public int recoveredField3693;
   public boolean recoveredField3694;
   public OffsetDateTime recoveredField3695;
   public String recoveredField3696;

   public JSONObject method_13367() {
      return new JSONObject()
         .put("state", this.recoveredField3696)
         .put("details", this.recoveredField3682)
         .put(
            "timestamps",
            new JSONObject()
               .put("start", this.recoveredField3695 == null ? null : this.recoveredField3695.toEpochSecond())
               .put("end", this.recoveredField3692 == null ? null : this.recoveredField3692.toEpochSecond())
         )
         .put(
            "assets",
            new JSONObject()
               .put("large_image", this.recoveredField3686)
               .put("large_text", this.recoveredField3685)
               .put("small_image", this.recoveredField3683)
               .put("small_text", this.recoveredField3688)
         )
         .put(
            "party",
            this.recoveredField3691 == null
               ? null
               : new JSONObject()
                  .put("id", this.recoveredField3691)
                  .put("size", new JSONArray().put(this.recoveredField3693).put(this.recoveredField3689))
         )
         .put("secrets", new JSONObject().put("join", this.recoveredField3684).put("spectate", this.recoveredField3690).put("match", this.recoveredField3687))
         .put("instance", this.recoveredField3694);
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
      this.recoveredField3696 = var1;
      this.recoveredField3682 = var2;
      this.recoveredField3695 = var3;
      this.recoveredField3692 = var4;
      this.recoveredField3686 = var5;
      this.recoveredField3685 = var6;
      this.recoveredField3683 = var7;
      this.recoveredField3688 = var8;
      this.recoveredField3691 = var9;
      this.recoveredField3693 = var10;
      this.recoveredField3689 = var11;
      this.recoveredField3687 = var12;
      this.recoveredField3684 = var13;
      this.recoveredField3690 = var14;
      this.recoveredField3694 = var15;
   }
}
