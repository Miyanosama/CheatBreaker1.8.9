package net.minecraft.client.renderer.block.model;

import com.cheatbreaker.client.websocket.server.WSPacketBulkFriends;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.netty.bootstrap.ServerBootstrap$ServerBootstrapAcceptor$2;
import io.netty.buffer.ByteBufProcessor$2;
import java.lang.reflect.Type;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.server.management.ServerConfigurationManager$1;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class ModelBlockDefinition$Variant$Deserializer implements JsonDeserializer<ModelBlockDefinition$Variant> {
   public WSPacketBulkFriends field_0002;
   public ServerConfigurationManager$1 field_0004;
   public ServerBootstrap$ServerBootstrapAcceptor$2 field_0001;
   public BlockRailBase field_0003;
   public ByteBufProcessor$2 field_0000;

   public String parseModel(JsonObject var1) {
      return JsonUtils.getString(var1, "model");
   }

   public ModelRotation parseRotation(JsonObject var1) {
      int var2 = JsonUtils.getInt(var1, "x", 0);
      int var3 = JsonUtils.getInt(var1, "y", 0);
      ModelRotation var4 = ModelRotation.getModelRotation(var2, var3);
      if (var4 == null) {
         throw new JsonParseException("Invalid BlockModelRotation x: " + var2 + ", y: " + var3);
      } else {
         return var4;
      }
   }

   public int parseWeight(JsonObject var1) {
      return JsonUtils.getInt(var1, "weight", 1);
   }

   public ResourceLocation makeModelLocation(String var1) {
      ResourceLocation var2 = new ResourceLocation(var1);
      return new ResourceLocation(var2.getResourceDomain(), "block/" + var2.getResourcePath());
   }

   public boolean parseUvLock(JsonObject var1) {
      return JsonUtils.getBoolean(var1, "uvlock", false);
   }

   public ModelBlockDefinition$Variant deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      String var5 = this.parseModel(var4);
      ModelRotation var6 = this.parseRotation(var4);
      boolean var7 = this.parseUvLock(var4);
      int var8 = this.parseWeight(var4);
      return new ModelBlockDefinition$Variant(this.makeModelLocation(var5), var6, var7, var8);
   }
}
