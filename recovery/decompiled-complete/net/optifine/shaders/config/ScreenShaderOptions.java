package net.optifine.shaders.config;

import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockSilverfish$EnumType$3;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import org.java_websocket.framing.FramedataImpl1$1;

public class ScreenShaderOptions {
   public String name;
   public int columns;
   public BlockSilverfish$EnumType$3 field_0002;
   public FramedataImpl1$1 field_0005;
   public C0APacketAnimation field_0000;
   public BlockBed field_0001;
   public ShaderOption[] shaderOptions;
   public S11PacketSpawnExperienceOrb field_0004;

   public ShaderOption[] getShaderOptions() {
      return this.shaderOptions;
   }

   public String getName() {
      return this.name;
   }

   public ScreenShaderOptions(String var1, ShaderOption[] var2, int var3) {
      this.name = var1;
      this.shaderOptions = var2;
      this.columns = var3;
   }

   public int getColumns() {
      return this.columns;
   }
}
