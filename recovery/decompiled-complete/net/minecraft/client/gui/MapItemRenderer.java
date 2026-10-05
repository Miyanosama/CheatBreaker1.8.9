package net.minecraft.client.gui;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.renderer.texture.LayeredColorMaskTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.enchantment.EnchantmentDigging;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import recovered.unidentified.UnidentifiedClass4798;

public class MapItemRenderer {
   public LayeredColorMaskTexture field_0004;
   public MusicTicker field_0007;
   public BlockSoulSand field_0003;
   public UnidentifiedClass4798 field_0006;
   public TextureManager textureManager;
   public static ResourceLocation mapIcons = new ResourceLocation("textures/map/map_icons.png");
   public Map<String, MapItemRenderer$Instance> loadedMaps = Maps.newHashMap();
   public EnchantmentDigging field_0005;
   public EnumConnectionState field_0002;

   public void renderMap(MapData var1, boolean var2) {
      MapItemRenderer$Instance.access$100(this.getMapRendererInstance(var1), var2);
   }

   public MapItemRenderer$Instance getMapRendererInstance(MapData var1) {
      MapItemRenderer$Instance var2 = this.loadedMaps.get(var1.a);
      if (var2 == null) {
         var2 = new MapItemRenderer$Instance(this, var1, null);
         this.loadedMaps.put(var1.a, var2);
      }

      return var2;
   }

   public void updateMapTexture(MapData var1) {
      MapItemRenderer$Instance.access$000(this.getMapRendererInstance(var1));
   }

   public void clearLoadedMaps() {
      for (MapItemRenderer$Instance var2 : this.loadedMaps.values()) {
         this.textureManager.deleteTexture(MapItemRenderer$Instance.access$300(var2));
      }

      this.loadedMaps.clear();
   }

   public MapItemRenderer(TextureManager var1) {
      this.textureManager = var1;
   }
}
