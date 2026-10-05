package com.cheatbreaker.client.module.type;

import io.netty.handler.codec.http.HttpServerCodec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.world.storage.MapData$MapInfo;
import net.optifine.CustomPanoramaProperties;

public class ComboCounterModule extends CombatCounterModule {
   public static long field_0003;
   public static int field_0006;
   public EntityAIFindEntityNearest field_0000;
   public MapData$MapInfo field_0001;
   public CustomPanoramaProperties field_0007;
   public static int field_0004 = -1;
   public HttpServerCodec field_0002;
   public static List<Long> field_0005 = new ArrayList<>();

   @Override
   public String method_00164() {
      return "6";
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.field_0003, field_0005.size(), this.field_0004.method_08912())) {
         return null;
      } else {
         return field_0000 == (-3067100752778625013L & 1115718336L) && this.field_0002.getValue() ? null : field_0005.size() + "";
      }
   }

   @Override
   public void method_09625() {
      field_0005.clear();
   }

   @Override
   public String method_00166() {
      return "Combo";
   }

   public ComboCounterModule() {
      super("Combo Counter", "[6 Combo]");
      this.method_28821("Displays how many times you hit someone in a row within 2 seconds.");
      this.method_28829("Erouax");
   }

   @Override
   public String method_09624() {
      return "No %LABEL%";
   }
}
