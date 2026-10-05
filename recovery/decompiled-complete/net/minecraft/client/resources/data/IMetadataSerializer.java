package net.minecraft.client.resources.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jagrosh.discordipc.entities.Callback;
import io.netty.handler.codec.spdy.SpdyFrameEncoder;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChatStyle$Serializer;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;

public class IMetadataSerializer {
   public VertexFormat field_0003;
   public IRegistry<String, IMetadataSerializer$Registration<? extends IMetadataSection>> metadataSectionSerializerRegistry = new RegistrySimple<>();
   public Callback field_0002;
   public EntityFlameFX field_0005;
   public TileEntity field_0000;
   public Gson gson;
   public SpdyFrameEncoder field_0007;
   public GsonBuilder gsonBuilder = new GsonBuilder();

   public Gson getGson() {
      if (this.gson == null) {
         this.gson = this.gsonBuilder.create();
      }

      return this.gson;
   }

   public <T extends IMetadataSection> void registerMetadataSectionType(IMetadataSectionSerializer<T> var1, Class<T> var2) {
      this.metadataSectionSerializerRegistry.putObject(var1.getSectionName(), new IMetadataSerializer$Registration<>(this, var1, var2, null));
      this.gsonBuilder.registerTypeAdapter(var2, var1);
      this.gson = null;
   }

   public <T extends IMetadataSection> T parseMetadataSection(String var1, JsonObject var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Metadata section name cannot be null");
      } else if (!var2.has(var1)) {
         return null;
      } else if (!var2.get(var1).isJsonObject()) {
         throw new IllegalArgumentException("Invalid metadata for '" + var1 + "' - expected object, found " + var2.get(var1));
      } else {
         IMetadataSerializer$Registration var3 = this.metadataSectionSerializerRegistry.getObject(var1);
         if (var3 == null) {
            throw new IllegalArgumentException("Don't know how to handle metadata section '" + var1 + "'");
         } else {
            return (T)this.getGson().fromJson(var2.getAsJsonObject(var1), var3.clazz);
         }
      }
   }

   public IMetadataSerializer() {
      this.gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent$Serializer());
      this.gsonBuilder.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle$Serializer());
      this.gsonBuilder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
   }
}
