package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.JsonUtils;

public class BlockPartFace {
   public BlockFaceUV blockFaceUV;
   public EnumFacing cullFace;
   public static EnumFacing FACING_DEFAULT = null;
   public int tintIndex;
   public String texture;

   public BlockPartFace(EnumFacing var1, int var2, String var3, BlockFaceUV var4) {
      this.cullFace = var1;
      this.tintIndex = var2;
      this.texture = var3;
      this.blockFaceUV = var4;
   }

   public static class Deserializer implements JsonDeserializer<BlockPartFace> {
      public BlockPartFace deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws com.google.gson.JsonParseException {
         JsonObject var4 = var1.getAsJsonObject();
         EnumFacing var5 = this.parseCullFace(var4);
         int var6 = this.parseTintIndex(var4);
         String var7 = this.parseTexture(var4);
         BlockFaceUV var8 = var3.deserialize(var4, BlockFaceUV.class);
         return new BlockPartFace(var5, var6, var7, var8);
      }

      public String parseTexture(JsonObject var1) {
         return JsonUtils.getString(var1, "texture");
      }

      public int parseTintIndex(JsonObject var1) {
         return JsonUtils.getInt(var1, "tintindex", -1);
      }

      public EnumFacing parseCullFace(JsonObject var1) {
         String var2 = JsonUtils.getString(var1, "cullface", "");
         return EnumFacing.byName(var2);
      }
   }
}
