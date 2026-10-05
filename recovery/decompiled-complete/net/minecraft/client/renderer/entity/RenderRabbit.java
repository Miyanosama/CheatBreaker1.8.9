package net.minecraft.client.renderer.entity;

import io.netty.channel.ThreadPerChannelEventLoopGroup$1;
import io.netty.channel.sctp.oio.OioSctpChannel$1;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntityEndPortalRenderer;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.play.server.S08PacketPlayerPosLook$EnumFlags;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.ClippingHelperShadow;
import org.java_websocket.extensions.permessage_deflate.PerMessageDeflateExtension;
import org.newsclub.net.unix.AFUNIXSocketImpl;

public class RenderRabbit extends RenderLiving<EntityRabbit> {
   public static ResourceLocation CAERBANNOG = new ResourceLocation("textures/entity/rabbit/caerbannog.png");
   public TileEntityEndPortalRenderer field_0014;
   public static ResourceLocation GOLD = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
   public static ResourceLocation WHITE_SPLOTCHED = new ResourceLocation("textures/entity/rabbit/salt.png");
   public static ResourceLocation SALT = new ResourceLocation("textures/entity/rabbit/gold.png");
   public S08PacketPlayerPosLook$EnumFlags field_0009;
   public ClippingHelperShadow field_0004;
   public OioSctpChannel$1 field_0011;
   public EnumPacketDirection field_0013;
   public static ResourceLocation WHITE = new ResourceLocation("textures/entity/rabbit/white.png");
   public AFUNIXSocketImpl field_0002;
   public static ResourceLocation TOAST = new ResourceLocation("textures/entity/rabbit/toast.png");
   public PerMessageDeflateExtension field_0012;
   public static ResourceLocation BROWN = new ResourceLocation("textures/entity/rabbit/brown.png");
   public ThreadPerChannelEventLoopGroup$1 field_0010;
   public static ResourceLocation BLACK = new ResourceLocation("textures/entity/rabbit/black.png");

   public RenderRabbit(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
   }

   public ResourceLocation getEntityTexture(EntityRabbit var1) {
      String var2 = EnumChatFormatting.getTextWithoutFormattingCodes(var1.z_());
      if (var2 != null && var2.equals("Toast")) {
         return TOAST;
      } else {
         switch (var1.getRabbitType()) {
            case 0:
            default:
               return BROWN;
            case 1:
               return WHITE;
            case 2:
               return BLACK;
            case 3:
               return GOLD;
            case 4:
               return SALT;
            case 5:
               return WHITE_SPLOTCHED;
            case 99:
               return CAERBANNOG;
         }
      }
   }
}
