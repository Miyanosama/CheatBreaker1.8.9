package net.optifine.player;

import io.netty.buffer.PooledHeapByteBuf$1;
import io.netty.channel.udt.nio.NioUdtByteConnectorChannel$1;
import io.netty.handler.codec.string.StringEncoder;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderGiantZombie$1;
import net.minecraft.src.Config;

public class PlayerConfiguration {
   public StringEncoder field_0003;
   public RenderGiantZombie$1 field_0005;
   public NioUdtByteConnectorChannel$1 field_0002;
   public boolean initialized;
   public PooledHeapByteBuf$1 field_0000;
   public PlayerItemModel[] playerItemModels = new PlayerItemModel[0];

   public boolean isInitialized() {
      return this.initialized;
   }

   public void setInitialized(boolean var1) {
      this.initialized = var1;
   }

   public void renderPlayerItems(ModelBiped var1, AbstractClientPlayer var2, float var3, float var4) {
      if (this.initialized) {
         for (int var5 = 0; var5 < this.playerItemModels.length; var5++) {
            PlayerItemModel var6 = this.playerItemModels[var5];
            var6.render(var1, var2, var3, var4);
         }
      }
   }

   public PlayerConfiguration() {
      this.initialized = false;
   }

   public PlayerItemModel[] getPlayerItemModels() {
      return this.playerItemModels;
   }

   public void addPlayerItemModel(PlayerItemModel var1) {
      this.playerItemModels = (PlayerItemModel[])Config.addObjectToArray(this.playerItemModels, var1);
   }
}
