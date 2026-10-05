package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item$1;
import net.minecraft.util.ChatComponentTranslation;
import net.optifine.shaders.config.ShaderProfile;
import recovered.unidentified.UnidentifiedClass4179;

public class LogFactor5ErrorDialog$1 implements ActionListener {
   public UnidentifiedClass4179 field_0003;
   public Item$1 field_0005;
   public ChatComponentTranslation field_0002;
   public EntityZombie field_0004;
   public LogFactor5ErrorDialog this$0;
   public ShaderProfile field_0001;

   public LogFactor5ErrorDialog$1(LogFactor5ErrorDialog var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.hide();
   }
}
