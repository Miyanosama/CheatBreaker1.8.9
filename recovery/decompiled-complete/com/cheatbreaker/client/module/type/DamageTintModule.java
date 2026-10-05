package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import io.netty.handler.codec.http.QueryStringEncoder;
import net.minecraft.realms.RealmsSharedConstants;
import net.minecraft.util.EntitySelectors$1;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.pattern.LiteralPatternConverter;

public class DamageTintModule extends AbstractModule {
   public Setting field_0002;
   public Setting field_0003;
   public Setting field_0000;
   public LiteralPatternConverter field_0001;
   public QueryStringEncoder field_0006;
   public Setting field_0004;
   public RealmsSharedConstants field_0007;
   public EntitySelectors$1 field_0005;

   public DamageTintModule() {
      super("Hit Color");
      this.setDefaultState(true);
      new Setting(this, "label").setValue("General Options");
      this.field_0002 = new Setting(this, "Affect Armor")
         .method_08917(var0 -> CheatBreaker.getInstance().getModuleManager().field_0041.field_0015.setValue(var0))
         .setValue(true);
      this.field_0000 = new Setting(this, "Affected by brightness").setValue(true);
      this.field_0004 = new Setting(this, "Animation Type").setValue("None").acceptedValues("None", "Linear In/Out", "Linear Out");
      new Setting(this, "label").setValue("Color Options");
      this.field_0003 = new Setting(this, "Hit Color").setValue(1727987712).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.method_28821("Customize the damage tint overlay on entities.");
      this.method_28829("aycy (Fade animation)");
      this.method_28807("Damage Tint");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/hittint.png"), 32, 32);
   }
}
