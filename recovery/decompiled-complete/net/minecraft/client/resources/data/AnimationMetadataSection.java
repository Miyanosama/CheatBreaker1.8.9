package net.minecraft.client.resources.data;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.enchantment.Enchantment;
import net.optifine.shaders.config.PropertyDefaultTrueFalse;
import org.apache.log4j.lf5.viewer.LogFactor5Dialog;

public class AnimationMetadataSection implements IMetadataSection {
   public List<AnimationFrame> animationFrames;
   public int frameTime;
   public PropertyDefaultTrueFalse field_0002;
   public LogFactor5Dialog field_0005;
   public boolean interpolate;
   public Enchantment field_0001;
   public int frameHeight;
   public int frameWidth;

   public int getFrameTime() {
      return this.frameTime;
   }

   public int getFrameIndex(int var1) {
      return this.animationFrames.get(var1).getFrameIndex();
   }

   public int getFrameTimeSingle(int var1) {
      AnimationFrame var2 = this.getAnimationFrame(var1);
      return var2.hasNoTime() ? this.frameTime : var2.getFrameTime();
   }

   public Set<Integer> getFrameIndexSet() {
      HashSet var1 = Sets.newHashSet();

      for (AnimationFrame var3 : this.animationFrames) {
         var1.add(var3.getFrameIndex());
      }

      return var1;
   }

   public boolean isInterpolate() {
      return this.interpolate;
   }

   public boolean frameHasTime(int var1) {
      return !this.animationFrames.get(var1).hasNoTime();
   }

   public int getFrameCount() {
      return this.animationFrames.size();
   }

   public int getFrameHeight() {
      return this.frameHeight;
   }

   public AnimationMetadataSection(List<AnimationFrame> var1, int var2, int var3, int var4, boolean var5) {
      this.animationFrames = var1;
      this.frameWidth = var2;
      this.frameHeight = var3;
      this.frameTime = var4;
      this.interpolate = var5;
   }

   public AnimationFrame getAnimationFrame(int var1) {
      return this.animationFrames.get(var1);
   }

   public int getFrameWidth() {
      return this.frameWidth;
   }
}
