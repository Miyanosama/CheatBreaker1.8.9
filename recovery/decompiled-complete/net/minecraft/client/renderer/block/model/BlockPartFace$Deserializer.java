package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.handler.codec.http.HttpObjectDecoder$1;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.spdy.SpdyStreamStatus;
import io.netty.handler.ssl.OpenSsl;
import java.lang.reflect.Type;
import net.minecraft.client.renderer.GlStateManager$Color;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.client.model.TRSRTransformation;

public class BlockPartFace$Deserializer implements JsonDeserializer<BlockPartFace> {
   public OpenSsl field_0003;
   public TRSRTransformation field_0005;
   public SpdyStreamStatus field_0002;
   public WebSocketFrameAggregator field_0004;
   public HttpObjectDecoder$1 field_0000;
   public GlStateManager$Color field_0001;

   public BlockPartFace deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      EnumFacing var5 = this.parseCullFace(var4);
      int var6 = this.parseTintIndex(var4);
      String var7 = this.parseTexture(var4);
      BlockFaceUV var8 = (BlockFaceUV)var3.deserialize(var4, BlockFaceUV.class);
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
