package org.apache.log4j.spi;

import net.minecraft.client.audio.SoundManager$1$1;
import net.minecraft.entity.monster.EntityEnderman$AITakeBlock;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import recovered.unidentified.UnidentifiedClass4197;

public class RootCategory extends Logger {
   public UnidentifiedClass4197 field_0001;
   public EntityEnderman$AITakeBlock field_0000;
   public SoundManager$1$1 field_0002;

   public RootCategory(Level var1) {
      super("root");
      this.setLevel(var1);
   }

   public void setLevel(Level var1) {
      if (var1 == null) {
         LogLog.error("You have tried to set a null level to root.", new Throwable());
      } else {
         this.level = var1;
      }
   }

   public void setPriority(Level var1) {
      this.setLevel(var1);
   }

   public Level getChainedLevel() {
      return this.level;
   }
}
