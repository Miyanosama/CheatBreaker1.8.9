package net.optifine.config;

import junit.awtui.TestRunner$7;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.command.server.CommandPardonIp;

public class GlVersion {
   public TestRunner$7 field_0004;
   public CommandPardonIp field_0007;
   public int major;
   public ChunkRenderWorker field_0006;
   public String suffix;
   public GuiScreen field_0001;
   public int minor;
   public int release;
   public TileEntityItemStackRenderer field_0002;

   public int getMinor() {
      return this.minor;
   }

   public int getMajor() {
      return this.major;
   }

   public int toInt() {
      return this.minor > 9
         ? this.major * 100 + this.minor
         : (this.release > 9 ? this.major * 100 + this.minor * 10 + 9 : this.major * 100 + this.minor * 10 + this.release);
   }

   public int getRelease() {
      return this.release;
   }

   public GlVersion(int var1, int var2, int var3) {
      this(var1, var2, var3, (String)null);
   }

   public GlVersion(int var1, int var2) {
      this(var1, var2, 0);
   }

   public GlVersion(int var1, int var2, int var3, String var4) {
      this.major = var1;
      this.minor = var2;
      this.release = var3;
      this.suffix = var4;
   }

   @Override
   public String toString() {
      return this.suffix == null
         ? "" + this.major + "." + this.minor + "." + this.release
         : "" + this.major + "." + this.minor + "." + this.release + this.suffix;
   }
}
