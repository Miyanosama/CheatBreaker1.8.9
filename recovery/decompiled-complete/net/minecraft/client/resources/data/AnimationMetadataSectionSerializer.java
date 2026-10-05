package net.minecraft.client.resources.data;

import com.cheatbreaker.client.module.ModuleRule;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.vecmath.Matrix4d;
import net.minecraft.realms.RealmsServerPing;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

public class AnimationMetadataSectionSerializer
   extends BaseMetadataSectionSerializer<AnimationMetadataSection>
   implements JsonSerializer<AnimationMetadataSection> {
   public Matrix4d field_0001;
   public RealmsServerPing field_0002;
   public ModuleRule field_0000;

   @Override
   public String getSectionName() {
      return "animation";
   }

   public JsonElement serialize(AnimationMetadataSection var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("frametime", var1.getFrameTime());
      if (var1.getFrameWidth() != -1) {
         var4.addProperty("width", var1.getFrameWidth());
      }

      if (var1.getFrameHeight() != -1) {
         var4.addProperty("height", var1.getFrameHeight());
      }

      if (var1.getFrameCount() > 0) {
         JsonArray var5 = new JsonArray();

         for (int var6 = 0; var6 < var1.getFrameCount(); var6++) {
            if (var1.frameHasTime(var6)) {
               JsonObject var7 = new JsonObject();
               var7.addProperty("index", var1.getFrameIndex(var6));
               var7.addProperty("time", var1.getFrameTimeSingle(var6));
               var5.add(var7);
            } else {
               var5.add(new JsonPrimitive(var1.getFrameIndex(var6)));
            }
         }

         var4.add("frames", var5);
      }

      return var4;
   }

   public AnimationMetadataSection deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      ArrayList var4 = Lists.newArrayList();
      JsonObject var5 = JsonUtils.getJsonObject(var1, "metadata section");
      int var6 = JsonUtils.getInt(var5, "frametime", 1);
      if (var6 != 1) {
         Validate.inclusiveBetween(58722309L & 3998529847304782113L, -3383318214208389121L & 3383318216355872767L, var6, "Invalid default frame time");
      }

      if (var5.has("frames")) {
         try {
            JsonArray var7 = JsonUtils.getJsonArray(var5, "frames");

            for (int var8 = 0; var8 < var7.size(); var8++) {
               JsonElement var9 = var7.get(var8);
               AnimationFrame var10 = this.parseAnimationFrame(var8, var9);
               if (var10 != null) {
                  var4.add(var10);
               }
            }
         } catch (ClassCastException var11) {
            throw new JsonParseException("Invalid animation->frames: expected array, was " + var5.get("frames"), var11);
         }
      }

      int var12 = JsonUtils.getInt(var5, "width", -1);
      int var13 = JsonUtils.getInt(var5, "height", -1);
      if (var12 != -1) {
         Validate.inclusiveBetween(6877157836657336841L & -6877157837198173119L, -2472346307025436673L & 2472346309172920319L, var12, "Invalid width");
      }

      if (var13 != -1) {
         Validate.inclusiveBetween(1682158593L & -1783628612979899915L, 3567846499771482111L & -3567846497623998465L, var13, "Invalid height");
      }

      boolean var14 = JsonUtils.getBoolean(var5, "interpolate", false);
      return new AnimationMetadataSection(var4, var12, var13, var6, var14);
   }

   public AnimationFrame parseAnimationFrame(int var1, JsonElement var2) {
      if (var2.isJsonPrimitive()) {
         return new AnimationFrame(JsonUtils.getInt(var2, "frames[" + var1 + "]"));
      } else if (var2.isJsonObject()) {
         JsonObject var3 = JsonUtils.getJsonObject(var2, "frames[" + var1 + "]");
         int var4 = JsonUtils.getInt(var3, "time", -1);
         if (var3.has("time")) {
            Validate.inclusiveBetween(-7599739079310046655L & 7599739078052938809L, 2147483647L & 7949508120810618879L, var4, "Invalid frame time");
         }

         int var5 = JsonUtils.getInt(var3, "index");
         Validate.inclusiveBetween(1015857220L & 1090523409L, -1112311214433632257L & 1112311216581115903L, var5, "Invalid frame index");
         return new AnimationFrame(var5, var4);
      } else {
         return null;
      }
   }
}
