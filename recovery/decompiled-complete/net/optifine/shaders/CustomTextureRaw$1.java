package net.optifine.shaders;

import io.netty.channel.ChannelMetadata;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.util.ResourceLeakDetector;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX$EnchantmentTable;
import net.minecraft.client.resources.ResourcePackFileNotFoundException;
import net.minecraft.util.EntitySelectors$ArmoredMob;
import net.optifine.texture.TextureType;
import org.apache.log4j.net.SMTPAppender$1;
import org.java_websocket.drafts.Draft_6455$TranslatedPayloadMetaData;

// $VF: synthetic class
public class CustomTextureRaw$1 {
   public SMTPAppender$1 field_0004;
   public DefaultHttpContent field_0003;
   public ResourceLeakDetector field_0006;
   public Draft_6455$TranslatedPayloadMetaData field_0000;
   public ResourcePackFileNotFoundException field_0001;
   public ChannelMetadata field_0008;
   public EntityEnchantmentTableParticleFX$EnchantmentTable field_0005;
   public EntitySelectors$ArmoredMob field_0002;

   static {
      try {
         $SwitchMap$net$optifine$texture$TextureType[TextureType.TEXTURE_1D.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$net$optifine$texture$TextureType[TextureType.TEXTURE_2D.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$optifine$texture$TextureType[TextureType.TEXTURE_3D.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$optifine$texture$TextureType[TextureType.TEXTURE_RECTANGLE.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
