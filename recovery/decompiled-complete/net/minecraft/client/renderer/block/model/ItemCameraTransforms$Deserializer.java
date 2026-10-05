package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import recovered.unidentified.UnidentifiedClass1610;

public class ItemCameraTransforms$Deserializer implements JsonDeserializer<ItemCameraTransforms> {
   public TileEntityMobSpawnerRenderer field_0001;
   public UnidentifiedClass1610 field_0002;
   public S05PacketSpawnPosition field_0000;

   public ItemTransformVec3f func_181683_a(JsonDeserializationContext var1, JsonObject var2, String var3) {
      return var2.has(var3) ? (ItemTransformVec3f)var1.deserialize(var2.get(var3), ItemTransformVec3f.class) : ItemTransformVec3f.DEFAULT;
   }

   public ItemCameraTransforms deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      ItemTransformVec3f var5 = this.func_181683_a(var3, var4, "thirdperson");
      ItemTransformVec3f var6 = this.func_181683_a(var3, var4, "firstperson");
      ItemTransformVec3f var7 = this.func_181683_a(var3, var4, "head");
      ItemTransformVec3f var8 = this.func_181683_a(var3, var4, "gui");
      ItemTransformVec3f var9 = this.func_181683_a(var3, var4, "ground");
      ItemTransformVec3f var10 = this.func_181683_a(var3, var4, "fixed");
      return new ItemCameraTransforms(var5, var6, var7, var8, var9, var10);
   }
}
