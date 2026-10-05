package net.minecraft.util;

import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import net.minecraft.client.gui.GuiPageButtonList$GuiListEntry;
import net.minecraft.client.renderer.WorldRenderer$1;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.optifine.http.HttpPipelineSender;

public class WeightedRandom$Item {
   public TextureAtlasSprite field_0001;
   public int a;
   public HttpPipelineSender field_0000;
   public PipeStatus field_0003;
   public GuiPageButtonList$GuiListEntry field_0004;
   public WorldRenderer$1 field_0002;

   public WeightedRandom$Item(int var1) {
      this.a = var1;
   }
}
