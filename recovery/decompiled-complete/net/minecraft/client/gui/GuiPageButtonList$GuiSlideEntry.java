package net.minecraft.client.gui;

import io.netty.channel.group.ChannelGroupException;
import net.minecraft.entity.ai.EntityAIWander;

public class GuiPageButtonList$GuiSlideEntry extends GuiPageButtonList$GuiListEntry {
   public float field_178947_b;
   public float field_178948_c;
   public GuiSlider$FormatHelper field_178949_a;
   public float field_178946_d;
   public EntityAIWander field_0000;
   public ChannelGroupException field_0003;

   public float func_178944_f() {
      return this.field_178948_c;
   }

   public GuiPageButtonList$GuiSlideEntry(int var1, String var2, boolean var3, GuiSlider$FormatHelper var4, float var5, float var6, float var7) {
      super(var1, var2, var3);
      this.field_178949_a = var4;
      this.field_178947_b = var5;
      this.field_178948_c = var6;
      this.field_178946_d = var7;
   }

   public float func_178943_e() {
      return this.field_178947_b;
   }

   public float func_178942_g() {
      return this.field_178946_d;
   }

   public GuiSlider$FormatHelper func_178945_a() {
      return this.field_178949_a;
   }
}
