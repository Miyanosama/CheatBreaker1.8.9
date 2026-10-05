package net.optifine.shaders.uniform;

import io.netty.channel.AbstractServerChannel;
import java.util.Arrays;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.ARBShaderObjects;

public abstract class ShaderUniformBase {
   public String name;
   public int program = 0;
   public static int field_0000;
   public AbstractServerChannel field_0003;
   public int[] locations = new int[]{-1};
   public static int field_0002;

   public String getName() {
      return this.name;
   }

   public ShaderUniformBase(String var1) {
      this.name = var1;
   }

   public int getProgram() {
      return this.program;
   }

   public abstract void onProgramSet(int var1);

   public abstract void resetValue();

   public void reset() {
      this.program = 0;
      this.locations = new int[]{-1};
      this.resetValue();
   }

   @Override
   public String toString() {
      return this.name;
   }

   public void setProgram(int var1) {
      if (this.program != var1) {
         this.program = var1;
         this.expandLocations();
         this.onProgramSet(var1);
      }
   }

   public boolean isDefined() {
      return this.getLocation() >= 0;
   }

   public void checkGLError() {
      if (Shaders.checkGLError(this.name) != 0) {
         this.disable();
      }
   }

   public void disable() {
      this.locations[this.program] = -1;
   }

   public void expandLocations() {
      if (this.program >= this.locations.length) {
         int[] var1 = new int[this.program * 2];
         Arrays.fill(var1, Integer.MIN_VALUE);
         System.arraycopy(this.locations, 0, var1, 0, this.locations.length);
         this.locations = var1;
      }
   }

   public int getLocation() {
      if (this.program <= 0) {
         return -1;
      } else {
         int var1 = this.locations[this.program];
         if (var1 == Integer.MIN_VALUE) {
            var1 = ARBShaderObjects.glGetUniformLocationARB(this.program, this.name);
            this.locations[this.program] = var1;
         }

         return var1;
      }
   }
}
