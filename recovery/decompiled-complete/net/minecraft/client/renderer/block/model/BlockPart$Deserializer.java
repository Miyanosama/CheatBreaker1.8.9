package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.netty.util.ResourceLeakDetector$Level;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.layer.GenLayerHills;
import org.lwjgl.util.vector.Vector3f;
import recovered.unidentified.UnidentifiedClass3876;
import recovered.unidentified.UnidentifiedClass4040;

public class BlockPart$Deserializer implements JsonDeserializer<BlockPart> {
   public ResourceLeakDetector$Level field_0001;
   public UnidentifiedClass4040 field_0003;
   public UnidentifiedClass3876 field_0000;
   public GenLayerHills field_0002;

   public Map<EnumFacing, BlockPartFace> parseFacesCheck(JsonDeserializationContext var1, JsonObject var2) {
      Map var3 = this.parseFaces(var1, var2);
      if (var3.isEmpty()) {
         throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
      } else {
         return var3;
      }
   }

   public Vector3f parsePositionTo(JsonObject var1) {
      Vector3f var2 = this.parsePosition(var1, "to");
      if (var2.x >= -16.0F && var2.y >= -16.0F && var2.z >= -16.0F && var2.x <= 32.0F && var2.y <= 32.0F && var2.z <= 32.0F) {
         return var2;
      } else {
         throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + var2);
      }
   }

   public Map<EnumFacing, BlockPartFace> parseFaces(JsonDeserializationContext var1, JsonObject var2) {
      EnumMap var3 = Maps.newEnumMap(EnumFacing.class);
      JsonObject var4 = JsonUtils.getJsonObject(var2, "faces");

      for (Entry var6 : var4.entrySet()) {
         EnumFacing var7 = this.parseEnumFacing((String)var6.getKey());
         var3.put(var7, (BlockPartFace)var1.deserialize((JsonElement)var6.getValue(), BlockPartFace.class));
      }

      return var3;
   }

   public BlockPart deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      Vector3f var5 = this.parsePositionFrom(var4);
      Vector3f var6 = this.parsePositionTo(var4);
      BlockPartRotation var7 = this.parseRotation(var4);
      Map var8 = this.parseFacesCheck(var3, var4);
      if (var4.has("shade") && !JsonUtils.isBoolean(var4, "shade")) {
         throw new JsonParseException("Expected shade to be a Boolean");
      } else {
         boolean var9 = JsonUtils.getBoolean(var4, "shade", true);
         return new BlockPart(var5, var6, var8, var7, var9);
      }
   }

   public BlockPartRotation parseRotation(JsonObject var1) {
      BlockPartRotation var2 = null;
      if (var1.has("rotation")) {
         JsonObject var3 = JsonUtils.getJsonObject(var1, "rotation");
         Vector3f var4 = this.parsePosition(var3, "origin");
         var4.scale(0.0625F);
         EnumFacing$Axis var5 = this.parseAxis(var3);
         float var6 = this.parseAngle(var3);
         boolean var7 = JsonUtils.getBoolean(var3, "rescale", false);
         var2 = new BlockPartRotation(var4, var5, var6, var7);
      }

      return var2;
   }

   public Vector3f parsePosition(JsonObject var1, String var2) {
      JsonArray var3 = JsonUtils.getJsonArray(var1, var2);
      if (var3.size() != 3) {
         throw new JsonParseException("Expected 3 " + var2 + " values, found: " + var3.size());
      } else {
         float[] var4 = new float[3];

         for (int var5 = 0; var5 < var4.length; var5++) {
            var4[var5] = JsonUtils.getFloat(var3.get(var5), var2 + "[" + var5 + "]");
         }

         return new Vector3f(var4[0], var4[1], var4[2]);
      }
   }

   public Vector3f parsePositionFrom(JsonObject var1) {
      Vector3f var2 = this.parsePosition(var1, "from");
      if (var2.x >= -16.0F && var2.y >= -16.0F && var2.z >= -16.0F && var2.x <= 32.0F && var2.y <= 32.0F && var2.z <= 32.0F) {
         return var2;
      } else {
         throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + var2);
      }
   }

   public EnumFacing$Axis parseAxis(JsonObject var1) {
      String var2 = JsonUtils.getString(var1, "axis");
      EnumFacing$Axis var3 = EnumFacing$Axis.byName(var2.toLowerCase());
      if (var3 == null) {
         throw new JsonParseException("Invalid rotation axis: " + var2);
      } else {
         return var3;
      }
   }

   public EnumFacing parseEnumFacing(String var1) {
      EnumFacing var2 = EnumFacing.byName(var1);
      if (var2 == null) {
         throw new JsonParseException("Unknown facing: " + var1);
      } else {
         return var2;
      }
   }

   public float parseAngle(JsonObject var1) {
      float var2 = JsonUtils.getFloat(var1, "angle");
      if (var2 != 0.0F && MathHelper.abs(var2) != 22.5F && MathHelper.abs(var2) != 45.0F) {
         throw new JsonParseException("Invalid rotation " + var2 + " found, only -45/-22.5/0/22.5/45 allowed");
      } else {
         return var2;
      }
   }
}
