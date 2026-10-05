package com.cheatbreaker.client.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.EventBus$Event;
import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.jagrosh.discordipc.entities.Callback;
import io.netty.handler.codec.rtsp.RtspResponseDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.server.MinecraftServer$3;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0806;
import recovered.unidentified.UnidentifiedClass0877;
import recovered.unidentified.UnidentifiedClass4000;

public abstract class AbstractModule {
   public Minecraft minecraft = Minecraft.getMinecraft();
   public float field_0041;
   public UnidentifiedClass4000 field_0023;
   public List<Setting> field_0038;
   public Map<Class<? extends EventBus$Event>, Consumer> field_0008 = new HashMap<>();
   public List<String> field_0011;
   public String field_0042;
   public CBGuiAnchor defaultGuiAnchor;
   public float field_0012;
   public Setting field_0044;
   public String[] field_0007;
   public Callback field_0027;
   public String[] field_0031;
   public MinecraftServer$3 field_0019;
   public Setting field_0036;
   public boolean staffModuleEnabled;
   public boolean field_0005;
   public List<Object> field_0018;
   public String field_0029;
   public AbstractModule$PreviewType previewType;
   public boolean field_0004;
   public Setting field_0003;
   public boolean enabled;
   public RtspResponseDecoder field_0002;
   public float previewIconWidth;
   public boolean field_0030;
   public float previewLabelSize;
   public float defaultXTranslation;
   public boolean field_0043;
   public String previewLabel;
   public boolean defaultState;
   public boolean field_0009;
   public float defaultYTranslation;
   public float xTranslation;
   public float previewIconHeight;
   public List<String> field_0014;
   public Setting field_0001;
   public String field_0045;
   public String field_0028;
   public float yTranslation;
   public Setting field_0025;
   public boolean field_0020;
   public CBGuiAnchor guiAnchor;
   public boolean field_0000;
   public ResourceLocation previewIconLocation;
   public boolean field_0013 = false;

   public float method_28810() {
      return this.field_0041;
   }

   public void method_28828(boolean var1) {
      this.field_0013 = var1;
   }

   public boolean method_28851() {
      return this.field_0030;
   }

   public void setYTranslation(float var1) {
      this.yTranslation = var1;
   }

   public void method_28819(CBGuiAnchor var1) {
      if (var1 != this.defaultGuiAnchor) {
         CheatBreaker.getInstance().getConfigManager().method_25097();
      }

      this.guiAnchor = var1;
   }

   public void method_28852(float var1) {
      this.field_0012 = var1;
   }

   public float getXTranslation() {
      return this.xTranslation;
   }

   public void setDefaultAnchor(CBGuiAnchor var1) {
      this.guiAnchor = var1;
      this.defaultGuiAnchor = var1;
   }

   public void setStaffModuleEnabled(boolean var1) {
      this.staffModuleEnabled = var1;
      if (!var1 && this.isEnabled()) {
         this.setState(false);
      }
   }

   public AbstractModule(String var1, String var2) {
      this.staffModuleEnabled = false;
      this.defaultState = false;
      this.field_0005 = true;
      this.field_0004 = true;
      this.field_0020 = true;
      this.field_0043 = false;
      this.enabled = false;
      this.field_0009 = false;
      this.field_0030 = false;
      this.field_0000 = false;
      this.xTranslation = 0.0F;
      this.yTranslation = 0.0F;
      this.defaultXTranslation = 0.0F;
      this.defaultYTranslation = 0.0F;
      this.field_0041 = 0.0F;
      this.field_0012 = 0.0F;
      this.field_0031 = new String[]{"OFF", "Above", "Below", "At", "Not At"};
      this.field_0042 = var1;
      this.field_0038 = new ArrayList<>();
      this.field_0018 = new ArrayList<>();
      this.field_0045 = var2;
      this.field_0044 = new Setting(this, "Scale", "Change the scale of the mod.").setValue(1.0F).setMinMax(0.5F, 1.5F).method_08892("x").method_08915("1.0x");
      this.field_0001 = new Setting(this, "GUI Scale", "Change the scale of the mod.")
         .setValue(var2)
         .acceptedValues("Global", "Default", "Small", "Normal", "Large", "Auto")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0025 = new Setting(this, "Toggle Mod Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0003 = new Setting(this, "Toggle HUD Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0036 = new Setting(this, "Temp Toggle HUD Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.field_0000);
      this.method_28856(UnidentifiedClass0806.class, this::method_28813);
      this.method_28856(UnidentifiedClass0877.class, this::method_28817);
   }

   public float[] getScaledPoints(ScaledResolution var1, boolean var2) {
      float var3 = 0.0F;
      float var4 = 0.0F;
      float var5 = this.field_0041 * this.method_28770();
      float var6 = this.field_0012 * this.method_28770();
      switch (AbstractModule$1.field_0004[this.guiAnchor.ordinal()]) {
         case 1:
            var3 = 2.0F;
            var4 = 2.0F;
            break;
         case 2:
            var3 = 2.0F;
            var4 = var1.getScaledHeight() / 2 - var6 / 2.0F;
            break;
         case 3:
            var4 = var1.getScaledHeight() - var6 - 2.0F;
            var3 = 2.0F;
            break;
         case 4:
            var3 = var1.getScaledWidth() / 2 - var5 / 2.0F;
            var4 = 2.0F;
            break;
         case 5:
            var3 = var1.getScaledWidth() / 2 - var5 / 2.0F;
            var4 = var1.getScaledHeight() / 2 - var6 / 2.0F;
            break;
         case 6:
            var3 = var1.getScaledWidth() / 2 - var5;
            var4 = var1.getScaledHeight() - var6 - 2.0F;
            break;
         case 7:
            var3 = var1.getScaledWidth() / 2.0F;
            var4 = var1.getScaledHeight() - var6 - 2.0F;
            break;
         case 8:
            var3 = var1.getScaledWidth() - var5 - 2.0F;
            var4 = 2.0F;
            break;
         case 9:
            var3 = var1.getScaledWidth() - var5;
            var4 = var1.getScaledHeight() / 2 - var6 / 2.0F;
            break;
         case 10:
            var3 = var1.getScaledWidth() - var5;
            var4 = var1.getScaledHeight() - var6;
      }

      return new float[]{(var3 + (var2 ? this.xTranslation : 0.0F)) / this.method_28770(), (var4 + (var2 ? this.yTranslation : 0.0F)) / this.method_28770()};
   }

   public boolean method_28842() {
      return this.field_0020;
   }

   public void method_28806(boolean var1) {
      this.enabled = var1;
   }

   public Setting method_28777() {
      return this.field_0001;
   }

   public void method_28813(UnidentifiedClass0806 var1) {
      if (var1.method_05523() != 0) {
         if (var1.method_05523() == this.field_0025.method_08912() && !this.field_0025.method_08879()) {
            this.setState(!this.enabled);
         }

         if (var1.method_05523() == this.field_0003.method_08912() && !this.field_0003.method_08879() && !this.field_0020) {
            this.method_28847(!this.field_0005);
         }

         if (var1.method_05523() == this.field_0036.method_08912() && !this.field_0036.method_08879()) {
            this.method_28789(!this.field_0043);
         }
      }
   }

   public boolean method_09815(Setting var1, int var2, float var3) {
      String var4 = var1.method_08874();
      switch (var4) {
         case "Above":
            return var2 > var3;
         case "Below":
            return var2 < var3;
         case "At":
            return var2 == var3;
         case "Not At":
            return var2 != var3;
         default:
            return false;
      }
   }

   public void method_28829(String... var1) {
      this.field_0014 = Arrays.asList(var1);
   }

   public void method_28801(float var1) {
      this.defaultXTranslation = var1;
   }

   public void method_28823(String var1, String... var2) {
      this.field_0029 = var1;
      this.field_0007 = var2;
   }

   public void method_28821(String var1) {
      this.field_0028 = var1;
   }

   public String method_28784() {
      return this.field_0029;
   }

   public Setting method_28860() {
      return this.field_0025;
   }

   public void method_28857(String var1) {
      this.previewLabel = var1;
   }

   public void removeAllEvents() {
      for (Entry var2 : this.field_0008.entrySet()) {
         CheatBreaker.getInstance().method_19817().method_21939((Class)var2.getKey(), (Consumer)var2.getValue());
      }
   }

   public void method_28805(String var1) {
      this.field_0045 = var1;
   }

   public void method_28812(float var1, float var2) {
      this.field_0041 = var1;
      this.field_0012 = var2;
   }

   public boolean method_28787() {
      return this.field_0013;
   }

   public void scaleAndTranslate(ScaledResolution var1, float var2, float var3) {
      float var4 = 0.0F;
      float var5 = 0.0F;
      float var6 = this.method_28770();
      GL11.glScalef(var6, var6, var6);
      var2 *= var6;
      var3 *= var6;
      switch (AbstractModule$1.field_0004[this.guiAnchor.ordinal()]) {
         case 1:
            var4 = 2.0F;
            var5 = 2.0F;
            break;
         case 2:
            var4 = 2.0F;
            var5 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case 3:
            var5 = var1.getScaledHeight() - var3 - 2.0F;
            var4 = 2.0F;
            break;
         case 4:
            var4 = var1.getScaledWidth() / 2 - var2 / 2.0F;
            var5 = 2.0F;
            break;
         case 5:
            var4 = var1.getScaledWidth() / 2 - var2 / 2.0F;
            var5 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case 6:
            var4 = var1.getScaledWidth() / 2 - var2;
            var5 = var1.getScaledHeight() - var3 - 2.0F;
            break;
         case 7:
            var4 = var1.getScaledWidth() / 2.0F;
            var5 = var1.getScaledHeight() - var3 - 2.0F;
            break;
         case 8:
            var4 = var1.getScaledWidth() - var2 - 2.0F;
            var5 = 2.0F;
            break;
         case 9:
            var4 = var1.getScaledWidth() - var2;
            var5 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case 10:
            var4 = var1.getScaledWidth() - var2;
            var5 = var1.getScaledHeight() - var3;
      }

      GL11.glTranslatef(var4 / var6, var5 / var6, 0.0F);
      GL11.glTranslatef(this.xTranslation / var6, this.yTranslation / var6, 0.0F);
   }

   public ResourceLocation getPreviewIcon() {
      return this.previewIconLocation;
   }

   public float getYTranslation() {
      return this.yTranslation;
   }

   public CBGuiAnchor method_28830() {
      return this.defaultGuiAnchor;
   }

   public boolean method_28834() {
      return this.field_0043;
   }

   public boolean method_28796() {
      return this.field_0000;
   }

   public void method_28827(AbstractModule$PreviewType var1) {
      this.previewType = var1;
   }

   public String[] method_28840() {
      return this.field_0031;
   }

   public boolean method_28850() {
      return this.field_0004;
   }

   public Setting method_28773() {
      return this.field_0036;
   }

   public void method_28847(boolean var1) {
      if (var1 != this.field_0004) {
         CheatBreaker.getInstance().getConfigManager().method_25097();
      }

      this.field_0005 = var1;
   }

   public void method_28825(ResourceLocation var1) {
      this.previewIconLocation = var1;
   }

   public void method_28781(boolean var1) {
      this.field_0020 = var1;
   }

   public CBPositionEnum getPosition() {
      return CBAnchorHelper.getHorizontalPositionEnum(this.guiAnchor);
   }

   public void method_28804(CBGuiAnchor var1) {
      this.defaultGuiAnchor = var1;
   }

   public void method_28817(UnidentifiedClass0877 var1) {
      if (var1.method_05882() != 0) {
         if (var1.method_05882() == this.field_0025.method_08912() && this.field_0025.method_08879()) {
            this.setState(!this.enabled);
         }

         if (var1.method_05882() == this.field_0003.method_08912() && this.field_0003.method_08879() && !this.field_0020) {
            this.method_28847(!this.field_0005);
         }

         if (var1.method_05882() == this.field_0036.method_08912() && this.field_0036.method_08879()) {
            this.method_28789(!this.field_0043);
         }
      }
   }

   public void setPreviewIcon(ResourceLocation var1, int var2, int var3) {
      this.previewType = AbstractModule$PreviewType.ICON;
      this.previewIconLocation = var1;
      this.previewIconWidth = var2;
      this.previewIconHeight = var3;
   }

   public void addAllEvents() {
      for (Entry var2 : this.field_0008.entrySet()) {
         CheatBreaker.getInstance().method_19817().method_21938((Class)var2.getKey(), (Consumer)var2.getValue());
      }
   }

   public Setting method_28849() {
      return this.field_0044;
   }

   public void method_28843(float var1) {
      this.field_0041 = var1;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void method_28786(boolean var1) {
      this.field_0009 = var1;
   }

   public Map<Class<? extends EventBus$Event>, Consumer> method_28771() {
      return this.field_0008;
   }

   public void method_28778(float var1) {
      this.previewLabelSize = var1;
   }

   public String[] method_28774() {
      return this.field_0007;
   }

   public float method_28770() {
      float var1 = 1.0F;
      if (this.field_0001.getValue().equals("Global")) {
         String var2 = (String)CheatBreaker.getInstance().getGlobalSettings().field_0115.getValue();
         switch (var2) {
            case "Small":
               var1 = var1 * 0.5F / CheatBreaker.method_19763();
               break;
            case "Normal":
               var1 /= CheatBreaker.method_19763();
               break;
            case "Large":
               var1 = var1 * 1.5F / CheatBreaker.method_19763();
               break;
            case "Auto":
               var1 = var1 * 2.0F / CheatBreaker.method_19763();
         }
      } else {
         String var4 = (String)this.field_0001.getValue();
         switch (var4) {
            case "Small":
               var1 = var1 * 0.5F / CheatBreaker.method_19763();
               break;
            case "Normal":
               var1 /= CheatBreaker.method_19763();
               break;
            case "Large":
               var1 = var1 * 1.5F / CheatBreaker.method_19763();
               break;
            case "Auto":
               var1 = var1 * 2.0F / CheatBreaker.method_19763();
         }
      }

      return (Float)this.field_0044.getValue() * (Float)CheatBreaker.getInstance().getGlobalSettings().field_0102.getValue() * var1;
   }

   public void method_28818(Setting var1) {
      this.field_0001 = var1;
   }

   public void method_28862(boolean var1) {
      this.field_0000 = var1;
   }

   public <T extends EventBus$Event> void method_28820(Class<T> var1, Consumer<T> var2) {
      this.field_0008.put(var1, var2);
   }

   public void method_28854(Setting var1) {
      this.field_0003 = var1;
   }

   public String getName() {
      return this.field_0042;
   }

   public List<Setting> getSettingsList() {
      return this.field_0038;
   }

   public List<String> method_28839() {
      return this.field_0011;
   }

   public void setDefaultTranslations(float var1, float var2) {
      this.xTranslation = var1;
      this.yTranslation = var2;
      this.defaultXTranslation = var1;
      this.defaultYTranslation = var2;
   }

   public void scaleAndTranslate(ScaledResolution var1) {
      this.scaleAndTranslate(var1, this.field_0041, this.field_0012);
   }

   public String getPreviewLabel() {
      return this.previewLabel;
   }

   public void method_28845(CBGuiAnchor var1) {
      this.guiAnchor = var1;
   }

   public Minecraft method_28863() {
      return this.minecraft;
   }

   public float method_28835() {
      return this.field_0012;
   }

   public void method_28838(boolean var1) {
      this.field_0030 = var1;
   }

   public float getPreviewIconHeight() {
      return this.previewIconHeight;
   }

   public List<Object> method_28782() {
      return this.field_0018;
   }

   public String method_28798() {
      return this.field_0045;
   }

   public void method_28789(boolean var1) {
      this.field_0043 = var1;
   }

   public void method_28859(String[] var1) {
      this.field_0031 = var1;
   }

   public void setPreviewLabel(String var1, float var2) {
      this.previewType = AbstractModule$PreviewType.LABEL;
      this.previewLabel = var1;
      this.previewLabelSize = var2;
   }

   public float getPreviewLabelSize() {
      return this.previewLabelSize;
   }

   public void method_28807(String... var1) {
      this.field_0011 = Arrays.asList(var1);
   }

   public AbstractModule$PreviewType getPreviewType() {
      return this.previewType;
   }

   public void method_28803(Setting var1) {
      this.field_0025 = var1;
   }

   public boolean method_28791() {
      return this.defaultState;
   }

   public <T extends EventBus$Event> void method_28856(Class<T> var1, Consumer<T> var2) {
      CheatBreaker.getInstance().method_19817().method_21938(var1, var2);
   }

   public float method_28769() {
      return this.defaultXTranslation;
   }

   public void method_28785(float var1) {
      this.previewIconHeight = var1;
   }

   public void method_28844(Setting var1) {
      this.field_0044 = var1;
   }

   public void method_28861(float var1) {
      this.defaultYTranslation = var1;
   }

   public boolean method_28866() {
      return this.field_0005;
   }

   public void method_28824(Minecraft var1) {
      this.minecraft = var1;
   }

   public boolean method_28846(String var1) {
      for (Setting var3 : this.getSettingsList()) {
         if (var3.method_08911().equalsIgnoreCase(var1)) {
            return true;
         }
      }

      return false;
   }

   public void method_28848(String[] var1) {
      this.field_0007 = var1;
   }

   public float getPreviewIconWidth() {
      return this.previewIconWidth;
   }

   public void setState(boolean var1) {
      if (var1 != this.defaultState) {
         CheatBreaker.getInstance().getConfigManager().method_25097();
      }

      if (var1) {
         if (!this.enabled) {
            this.enabled = true;
            this.addAllEvents();
         }
      } else if (this.enabled) {
         this.enabled = false;
         this.removeAllEvents();
      }
   }

   public float method_28836() {
      return this.defaultYTranslation;
   }

   public String method_28809() {
      return this.field_0028;
   }

   public Setting method_28797() {
      return this.field_0003;
   }

   public void method_28837(float var1) {
      this.previewIconWidth = var1;
   }

   public AbstractModule(String var1) {
      this.staffModuleEnabled = false;
      this.defaultState = false;
      this.field_0005 = true;
      this.field_0004 = true;
      this.field_0020 = true;
      this.field_0043 = false;
      this.enabled = false;
      this.field_0009 = false;
      this.field_0030 = false;
      this.field_0000 = false;
      this.xTranslation = 0.0F;
      this.yTranslation = 0.0F;
      this.defaultXTranslation = 0.0F;
      this.defaultYTranslation = 0.0F;
      this.field_0041 = 0.0F;
      this.field_0012 = 0.0F;
      this.field_0031 = new String[]{"OFF", "Above", "Below", "At", "Not At"};
      this.field_0042 = var1;
      this.field_0038 = new ArrayList<>();
      this.field_0018 = new ArrayList<>();
      this.field_0045 = "Global";
      this.field_0044 = new Setting(this, "Scale", "Change the scale of the mod.").setValue(1.0F).setMinMax(0.5F, 1.5F).method_08892("x").method_08915("1.0x");
      this.field_0001 = new Setting(this, "GUI Scale", "Change the scale of the mod.")
         .setValue(this.field_0045)
         .acceptedValues("Global", "Default", "Small", "Normal", "Large", "Auto")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0025 = new Setting(this, "Toggle Mod Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0003 = new Setting(this, "Toggle HUD Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0036 = new Setting(this, "Temp Toggle HUD Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.field_0000);
      this.method_28856(UnidentifiedClass0806.class, this::method_28813);
      this.method_28856(UnidentifiedClass0877.class, this::method_28817);
   }

   public List<String> method_28808() {
      return this.field_0014;
   }

   public boolean isStaffEnabledModule() {
      return this.staffModuleEnabled;
   }

   public void method_28780(String var1) {
      this.field_0029 = var1;
   }

   public void setTranslations(float var1, float var2) {
      this.xTranslation = var1;
      this.yTranslation = var2;
   }

   public void method_28779(Setting var1) {
      this.field_0036 = var1;
   }

   public void setDefaultState(boolean var1) {
      if (var1) {
         if (!this.enabled) {
            this.enabled = true;
            this.addAllEvents();
         }
      } else if (this.enabled) {
         this.enabled = false;
         this.removeAllEvents();
      }

      this.defaultState = this.enabled;
   }

   public CBGuiAnchor getGuiAnchor() {
      return this.guiAnchor;
   }

   public boolean method_28790() {
      return this.field_0009;
   }

   public void method_28831(boolean var1) {
      this.field_0004 = var1;
   }

   public void setXTranslation(float var1) {
      this.xTranslation = var1;
   }
}
