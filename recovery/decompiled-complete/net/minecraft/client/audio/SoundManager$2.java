package net.minecraft.client.audio;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import net.minecraft.realms.RealmsEditBox;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.chunk.EmptyChunk;
import net.optifine.entity.model.ModelAdapter;

public class SoundManager$2 extends URLStreamHandler {
   public ModelAdapter field_0002;
   public EmptyChunk field_0004;
   public BiomeGenPlains field_0001;
   public RealmsEditBox field_0003;

   public SoundManager$2(ResourceLocation var1) {
      this.field_0000 = var1;
      super();
   }

   @Override
   public URLConnection openConnection(URL var1) {
      return new SoundManager$2$1(this, var1);
   }
}
