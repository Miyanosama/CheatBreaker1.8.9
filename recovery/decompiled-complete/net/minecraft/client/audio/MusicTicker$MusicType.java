package net.minecraft.client.audio;

import io.netty.util.Recycler;
import net.minecraft.util.ResourceLocation;
import net.optifine.render.VboRange;
import net.optifine.shaders.DefaultTexture;
import recovered.unidentified.UnidentifiedClass1810;

public enum MusicTicker$MusicType {
   NETHER(new ResourceLocation("minecraft:music.game.nether"), 1200, 3600),
   CREATIVE(new ResourceLocation("minecraft:music.game.creative"), 1200, 3600),
   END_BOSS(new ResourceLocation("minecraft:music.game.end.dragon"), 0, 0),
   CREDITS(new ResourceLocation("minecraft:music.game.end.credits"), Integer.MAX_VALUE, Integer.MAX_VALUE),
   GAME(new ResourceLocation("minecraft:music.game"), 12000, 24000),
   END(new ResourceLocation("minecraft:music.game.end"), 6000, 24000),
   MENU(new ResourceLocation("minecraft:music.menu"), 20, 600);
   public ResourceLocation musicLocation;
   public Recycler field_0012;
   public int maxDelay;
   // $VF: synthetic field
   public static MusicTicker$MusicType[] $VALUES = new MusicTicker$MusicType[]{
      MusicTicker$MusicType.MENU,
      MusicTicker$MusicType.GAME,
      MusicTicker$MusicType.CREATIVE,
      MusicTicker$MusicType.CREDITS,
      NETHER,
      MusicTicker$MusicType.END_BOSS,
      MusicTicker$MusicType.END
   };
   public VboRange field_0009;
   public UnidentifiedClass1810 field_0014;
   public int minDelay;
   public DefaultTexture field_0004;

   public ResourceLocation getMusicLocation() {
      return this.musicLocation;
   }

   public int getMaxDelay() {
      return this.maxDelay;
   }

   public MusicTicker$MusicType(ResourceLocation var3, int var4, int var5) {
      this.musicLocation = var3;
      this.minDelay = var4;
      this.maxDelay = var5;
   }

   public int getMinDelay() {
      return this.minDelay;
   }
}
