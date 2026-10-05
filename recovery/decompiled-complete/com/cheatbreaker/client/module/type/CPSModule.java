package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.codec.socks.SocksCmdResponse$1;
import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart;
import org.apache.commons.lang3.StringUtils;
import recovered.unidentified.UnidentifiedClass0877;

public class CPSModule extends CombatCounterModule {
   public Setting field_0003;
   public SocksCmdResponse$1 field_0008;
   public BlockPartFace field_0000;
   public String field_0001;
   public Setting field_0009;
   public MobSpawnerBaseLogic$WeightedRandomMinecart field_0005;
   public Deque<Long> field_0002;
   public NBTTagIntArray field_0007;
   public String field_0006;
   public Deque<Long> field_0004 = new ArrayDeque<>();

   public void method_04334(String var1, String var2) {
      float var3 = (float)this.minecraft.fontRendererObj.getStringWidth(StringUtils.substringBefore(var1, "%VALUE%"))
         + this.minecraft.fontRendererObj.getStringWidth(this.field_0001)
         + (this.minecraft.fontRendererObj.getStringWidth(this.field_0006) / 2.0F - 0.1F);
      float var4 = !this.field_0005.method_08908() && !this.field_0007.method_08908() ? -1.0F : this.field_0018.method_08905() / 2.0F - 4.5F;
      boolean var5 = this.field_0005.method_08908() ? this.field_0015.method_08908() : this.field_0000.method_08908();
      if (this.field_0005.method_08908() || this.field_0007.method_08908()) {
         float var6 = this.field_0041 / 2.0F
            - this.minecraft.fontRendererObj.getStringWidth(var1.replaceAll("%LABEL%", this.method_00166()).replaceAll("%VALUE%", var2)) / 2;
         var3 += var6;
      }

      if (this.field_0009.getValue().equals("Both")) {
         RenderUtil.method_22058(var3, var4, var3 + 1.0F, var4 + 9.0F, this.field_0003.method_08901(), var5);
      }
   }

   @Override
   public String method_00164() {
      return this.field_0009.getValue().equals("Both") ? "9  2" : "9";
   }

   @Override
   public void method_04335() {
      this.field_0003 = new Setting(this, "Line Color")
         .setValue(-14671840)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0009.getValue().equals("Both"));
   }

   public void method_04337(UnidentifiedClass0877 var1) {
      if (var1.method_05882() == 0) {
         this.field_0004.add(System.currentTimeMillis());
      }

      if (var1.method_05882() == 1) {
         this.field_0002.add(System.currentTimeMillis());
      }
   }

   @Override
   public void method_04331(String var1) {
      super.method_04331(var1);
      this.method_04334((this.field_0005.method_08908() ? this.field_0003 : this.field_0006).method_08874(), var1);
   }

   @Override
   public String method_00166() {
      return (
            !this.field_0009.getValue().equals("Right Clicks")
                  && (!this.field_0009.getValue().equals("Higher") || this.field_0004.size() >= this.field_0002.size())
               ? ""
               : "R"
         )
         + "CPS";
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.field_0003.method_08894(() -> this.field_0009.method_08874().endsWith(" Clicks"));
      this.field_0004.method_08894(() -> !this.field_0003.getValue().equals("OFF") && this.field_0009.method_08874().endsWith(" Clicks"));
      this.field_0009 = new Setting(
            this,
            "Counter",
            "Determines which and when clicks should be shown.\n\n§bLeft Clicks:§r Only show left clicks.\n§bRight Clicks:§r Only show right clicks.\n§bBoth:§r Show both left and right clicks.\n§bHigher:§r Shows either left or right clicks depending which has a higher amount."
         )
         .setValue("Left Clicks")
         .acceptedValues("Left Clicks", "Right Clicks", "Both", "Higher")
         .method_08914(SettingsDetailLevel.field_0000);
   }

   public void onTick(TickEvent var1) {
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.field_0003, this.field_0004.size(), this.field_0004.method_08912()) && this.field_0009.getValue().equals("Left Clicks")) {
         return null;
      } else if (this.method_09815(this.field_0003, this.field_0002.size(), this.field_0004.method_08912())
         && this.field_0009.getValue().equals("Right Clicks")) {
         return null;
      } else if (field_0000 == (-1413642844429270240L & 335708164L) && (Boolean)this.field_0002.getValue()) {
         return null;
      } else {
         this.field_0004.removeIf(var0 -> var0 < System.currentTimeMillis() - (-9041199778376383510L & 16835560L));
         this.field_0002.removeIf(var0 -> var0 < System.currentTimeMillis() - (-4684203792405589016L & 4684203790374749163L));
         this.field_0001 = !this.field_0009.getValue().equals("Right Clicks") ? "" + this.field_0004.size() : "";
         String var1 = !this.field_0009.getValue().equals("Left Clicks") ? Integer.toString(this.field_0002.size()) : "";
         this.field_0006 = this.field_0009.getValue().equals("Both") ? "  " : "";
         return this.field_0009.getValue().equals("Higher")
            ? Math.max(this.field_0004.size(), this.field_0002.size()) + ""
            : this.field_0001 + this.field_0006 + var1;
      }
   }

   public CPSModule() {
      super("CPS", "[9 CPS]");
      this.field_0002 = new ArrayDeque<>();
      this.method_28821("Displays your clicks per second.");
      this.method_28829("Fyu");
      this.method_28820(UnidentifiedClass0877.class, this::method_04337);
      this.method_28820(TickEvent.class, this::onTick);
   }
}
