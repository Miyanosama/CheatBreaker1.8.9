package net.minecraft.client.resources;

import com.google.gson.JsonParseException;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourcePackListEntryDefault extends ResourcePackListEntry {
   public ResourceLocation resourcePackIcon;
   public IResourcePack field_148320_d = this.a.getResourcePackRepository().rprDefaultResourcePack;
   public static Logger logger = LogManager.getLogger();

   @Override
   public int func_183019_a() {
      return 1;
   }

   @Override
   public boolean func_148310_d() {
      return false;
   }

   @Override
   public boolean func_148308_f() {
      return false;
   }

   @Override
   public boolean func_148309_e() {
      return false;
   }

   @Override
   public boolean func_148307_h() {
      return false;
   }

   @Override
   public boolean func_148314_g() {
      return false;
   }

   @Override
   public String func_148311_a() {
      try {
         PackMetadataSection var1 = this.field_148320_d.getPackMetadata(this.a.getResourcePackRepository().rprMetadataSerializer, "pack");
         if (var1 != null) {
            return var1.getPackDescription().getFormattedText();
         }
      } catch (JsonParseException var2) {
         logger.error("Couldn't load metadata info", var2);
      } catch (IOException var3) {
         logger.error("Couldn't load metadata info", var3);
      }

      return EnumChatFormatting.RED + "Missing pack.mcmeta :(";
   }

   @Override
   public String func_148312_b() {
      return "Default";
   }

   public ResourcePackListEntryDefault(GuiScreenResourcePacks var1) {
      super(var1);

      DynamicTexture var2;
      try {
         var2 = new DynamicTexture(this.field_148320_d.getPackImage());
      } catch (IOException var4) {
         var2 = TextureUtil.missingTexture;
      }

      this.resourcePackIcon = this.a.getTextureManager().getDynamicTextureLocation("texturepackicon", var2);
   }

   @Override
   public void func_148313_c() {
      this.a.getTextureManager().bindTexture(this.resourcePackIcon);
   }
}
