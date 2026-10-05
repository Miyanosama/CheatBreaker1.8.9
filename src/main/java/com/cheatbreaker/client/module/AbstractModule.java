package com.cheatbreaker.client.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.EventBus$Event;
import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.event.type.KeyPressEvent;
import com.cheatbreaker.client.event.type.MouseClickEvent;

public abstract class AbstractModule {
   public Minecraft minecraft = Minecraft.getMinecraft();
   public float recoveredField3889;
   public List<Setting> recoveredField3890;
   public Map<Class<? extends EventBus$Event>, Consumer> recoveredField3891 = new HashMap<>();
   public List<String> recoveredField3892;
   public String recoveredField3893;
   public CBGuiAnchor defaultGuiAnchor;
   public float recoveredField3894;
   public Setting recoveredField3895;
   public String[] recoveredField3896;
   public String[] recoveredField3897;
   public Setting recoveredField3898;
   public boolean staffModuleEnabled;
   public boolean recoveredField3899;
   public List<Object> recoveredField3900;
   public String recoveredField3901;
   public AbstractModule.PreviewType previewType;
   public boolean recoveredField3902;
   public Setting recoveredField3903;
   public boolean enabled;
   public float previewIconWidth;
   public boolean recoveredField3904;
   public float previewLabelSize;
   public float defaultXTranslation;
   public boolean recoveredField3905;
   public String previewLabel;
   public boolean defaultState;
   public boolean recoveredField3906;
   public float defaultYTranslation;
   public float xTranslation;
   public float previewIconHeight;
   public List<String> recoveredField3907;
   public Setting recoveredField3908;
   public String recoveredField3909;
   public String recoveredField3910;
   public float yTranslation;
   public Setting recoveredField3911;
   public boolean recoveredField3912;
   public CBGuiAnchor guiAnchor;
   public boolean recoveredField3913;
   public ResourceLocation previewIconLocation;
   public boolean recoveredField3914 = false;

   public float method_28810() {
      return this.recoveredField3889;
   }

   public void method_28828(boolean var1) {
      this.recoveredField3914 = var1;
   }

   public boolean method_28851() {
      return this.recoveredField3904;
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
      this.recoveredField3894 = var1;
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
      this.recoveredField3899 = true;
      this.recoveredField3902 = true;
      this.recoveredField3912 = true;
      this.recoveredField3905 = false;
      this.enabled = false;
      this.recoveredField3906 = false;
      this.recoveredField3904 = false;
      this.recoveredField3913 = false;
      this.xTranslation = 0.0F;
      this.yTranslation = 0.0F;
      this.defaultXTranslation = 0.0F;
      this.defaultYTranslation = 0.0F;
      this.recoveredField3889 = 0.0F;
      this.recoveredField3894 = 0.0F;
      this.recoveredField3897 = new String[]{"OFF", "Above", "Below", "At", "Not At"};
      this.recoveredField3893 = var1;
      this.recoveredField3890 = new ArrayList<>();
      this.recoveredField3900 = new ArrayList<>();
      this.recoveredField3909 = var2;
      this.recoveredField3895 = new Setting(this, "Scale", "Change the scale of the mod.")
         .setValue(1.0F)
         .setMinMax(0.5F, 1.5F)
         .method_08892("x")
         .method_08915("1.0x");
      this.recoveredField3908 = new Setting(this, "GUI Scale", "Change the scale of the mod.")
         .setValue(var2)
         .acceptedValues("Global", "Default", "Small", "Normal", "Large", "Auto")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField3911 = new Setting(this, "Toggle Mod Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3903 = new Setting(this, "Toggle HUD Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3898 = new Setting(this, "Temp Toggle HUD Keybind")
         .setValue(0)
         .method_08909(false)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.method_28856(KeyPressEvent.class, this::method_28813);
      this.method_28856(MouseClickEvent.class, this::method_28817);
   }

   public float[] getScaledPoints(ScaledResolution var1, boolean var2) {
      float var3 = 0.0F;
      float var4 = 0.0F;
      float var5 = this.recoveredField3889 * this.method_28770();
      float var6 = this.recoveredField3894 * this.method_28770();
      switch (this.guiAnchor) {
         case LEFT_TOP:
            var3 = 2.0F;
            var4 = 2.0F;
            break;
         case LEFT_MIDDLE:
            var3 = 2.0F;
            var4 = var1.getScaledHeight() / 2 - var6 / 2.0F;
            break;
         case LEFT_BOTTOM:
            var4 = var1.getScaledHeight() - var6 - 2.0F;
            var3 = 2.0F;
            break;
         case MIDDLE_TOP:
            var3 = var1.getScaledWidth() / 2 - var5 / 2.0F;
            var4 = 2.0F;
            break;
         case MIDDLE_MIDDLE:
            var3 = var1.getScaledWidth() / 2 - var5 / 2.0F;
            var4 = var1.getScaledHeight() / 2 - var6 / 2.0F;
            break;
         case MIDDLE_BOTTOM_LEFT:
            var3 = var1.getScaledWidth() / 2 - var5;
            var4 = var1.getScaledHeight() - var6 - 2.0F;
            break;
         case MIDDLE_BOTTOM_RIGHT:
            var3 = var1.getScaledWidth() / 2.0F;
            var4 = var1.getScaledHeight() - var6 - 2.0F;
            break;
         case RIGHT_TOP:
            var3 = var1.getScaledWidth() - var5 - 2.0F;
            var4 = 2.0F;
            break;
         case RIGHT_MIDDLE:
            var3 = var1.getScaledWidth() - var5;
            var4 = var1.getScaledHeight() / 2 - var6 / 2.0F;
            break;
         case RIGHT_BOTTOM:
            var3 = var1.getScaledWidth() - var5;
            var4 = var1.getScaledHeight() - var6;
      }

      return new float[]{(var3 + (var2 ? this.xTranslation : 0.0F)) / this.method_28770(), (var4 + (var2 ? this.yTranslation : 0.0F)) / this.method_28770()};
   }

   public boolean method_28842() {
      return this.recoveredField3912;
   }

   public void method_28806(boolean var1) {
      this.enabled = var1;
   }

   public Setting method_28777() {
      return this.recoveredField3908;
   }

   public void method_28813(KeyPressEvent var1) {
      if (var1.method_05523() != 0) {
         if (var1.method_05523() == this.recoveredField3911.method_08912() && !this.recoveredField3911.method_08879()) {
            this.setState(!this.enabled);
         }

         if (var1.method_05523() == this.recoveredField3903.method_08912() && !this.recoveredField3903.method_08879() && !this.recoveredField3912) {
            this.method_28847(!this.recoveredField3899);
         }

         if (var1.method_05523() == this.recoveredField3898.method_08912() && !this.recoveredField3898.method_08879()) {
            this.method_28789(!this.recoveredField3905);
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
      this.recoveredField3907 = Arrays.asList(var1);
   }

   public void method_28801(float var1) {
      this.defaultXTranslation = var1;
   }

   public void method_28823(String var1, String... var2) {
      this.recoveredField3901 = var1;
      this.recoveredField3896 = var2;
   }

   public void method_28821(String var1) {
      this.recoveredField3910 = var1;
   }

   public String method_28784() {
      return this.recoveredField3901;
   }

   public Setting method_28860() {
      return this.recoveredField3911;
   }

   public void method_28857(String var1) {
      this.previewLabel = var1;
   }

   public void removeAllEvents() {
      for (Entry var2 : this.recoveredField3891.entrySet()) {
         CheatBreaker.getInstance().method_19817().method_21939((Class)var2.getKey(), (Consumer)var2.getValue());
      }
   }

   public void method_28805(String var1) {
      this.recoveredField3909 = var1;
   }

   public void method_28812(float var1, float var2) {
      this.recoveredField3889 = var1;
      this.recoveredField3894 = var2;
   }

   public boolean method_28787() {
      return this.recoveredField3914;
   }

   public void scaleAndTranslate(ScaledResolution var1, float var2, float var3) {
      float var4 = 0.0F;
      float var5 = 0.0F;
      float var6 = this.method_28770();
      GL11.glScalef(var6, var6, var6);
      var2 *= var6;
      var3 *= var6;
      switch (this.guiAnchor) {
         case LEFT_TOP:
            var4 = 2.0F;
            var5 = 2.0F;
            break;
         case LEFT_MIDDLE:
            var4 = 2.0F;
            var5 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case LEFT_BOTTOM:
            var5 = var1.getScaledHeight() - var3 - 2.0F;
            var4 = 2.0F;
            break;
         case MIDDLE_TOP:
            var4 = var1.getScaledWidth() / 2 - var2 / 2.0F;
            var5 = 2.0F;
            break;
         case MIDDLE_MIDDLE:
            var4 = var1.getScaledWidth() / 2 - var2 / 2.0F;
            var5 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case MIDDLE_BOTTOM_LEFT:
            var4 = var1.getScaledWidth() / 2 - var2;
            var5 = var1.getScaledHeight() - var3 - 2.0F;
            break;
         case MIDDLE_BOTTOM_RIGHT:
            var4 = var1.getScaledWidth() / 2.0F;
            var5 = var1.getScaledHeight() - var3 - 2.0F;
            break;
         case RIGHT_TOP:
            var4 = var1.getScaledWidth() - var2 - 2.0F;
            var5 = 2.0F;
            break;
         case RIGHT_MIDDLE:
            var4 = var1.getScaledWidth() - var2;
            var5 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case RIGHT_BOTTOM:
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
      return this.recoveredField3905;
   }

   public boolean method_28796() {
      return this.recoveredField3913;
   }

   public void method_28827(AbstractModule.PreviewType var1) {
      this.previewType = var1;
   }

   public String[] method_28840() {
      return this.recoveredField3897;
   }

   public boolean method_28850() {
      return this.recoveredField3902;
   }

   public Setting method_28773() {
      return this.recoveredField3898;
   }

   public void method_28847(boolean var1) {
      if (var1 != this.recoveredField3902) {
         CheatBreaker.getInstance().getConfigManager().method_25097();
      }

      this.recoveredField3899 = var1;
   }

   public void method_28825(ResourceLocation var1) {
      this.previewIconLocation = var1;
   }

   public void method_28781(boolean var1) {
      this.recoveredField3912 = var1;
   }

   public CBPositionEnum getPosition() {
      return CBAnchorHelper.getHorizontalPositionEnum(this.guiAnchor);
   }

   public void method_28804(CBGuiAnchor var1) {
      this.defaultGuiAnchor = var1;
   }

   public void method_28817(MouseClickEvent var1) {
      if (var1.method_05882() != 0) {
         if (var1.method_05882() == this.recoveredField3911.method_08912() && this.recoveredField3911.method_08879()) {
            this.setState(!this.enabled);
         }

         if (var1.method_05882() == this.recoveredField3903.method_08912() && this.recoveredField3903.method_08879() && !this.recoveredField3912) {
            this.method_28847(!this.recoveredField3899);
         }

         if (var1.method_05882() == this.recoveredField3898.method_08912() && this.recoveredField3898.method_08879()) {
            this.method_28789(!this.recoveredField3905);
         }
      }
   }

   public void setPreviewIcon(ResourceLocation var1, int var2, int var3) {
      this.previewType = AbstractModule.PreviewType.ICON;
      this.previewIconLocation = var1;
      this.previewIconWidth = var2;
      this.previewIconHeight = var3;
   }

   public void addAllEvents() {
      for (Entry var2 : this.recoveredField3891.entrySet()) {
         CheatBreaker.getInstance().method_19817().method_21938((Class)var2.getKey(), (Consumer)var2.getValue());
      }
   }

   public Setting method_28849() {
      return this.recoveredField3895;
   }

   public void method_28843(float var1) {
      this.recoveredField3889 = var1;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void method_28786(boolean var1) {
      this.recoveredField3906 = var1;
   }

   public Map<Class<? extends EventBus$Event>, Consumer> method_28771() {
      return this.recoveredField3891;
   }

   public void method_28778(float var1) {
      this.previewLabelSize = var1;
   }

   public String[] method_28774() {
      return this.recoveredField3896;
   }

   public float method_28770() {
      float var1 = 1.0F;
      if ((Boolean)this.recoveredField3908.getValue().equals("Global")) {
         String var2 = (String)CheatBreaker.getInstance().getGlobalSettings().recoveredField570.getValue();
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
         String var4 = (String)this.recoveredField3908.getValue();
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

      return (Float)this.recoveredField3895.getValue() * (Float)CheatBreaker.getInstance().getGlobalSettings().recoveredField500.getValue() * var1;
   }

   public void method_28818(Setting var1) {
      this.recoveredField3908 = var1;
   }

   public void method_28862(boolean var1) {
      this.recoveredField3913 = var1;
   }

   public <T extends EventBus$Event> void method_28820(Class<T> var1, Consumer<T> var2) {
      this.recoveredField3891.put(var1, var2);
   }

   public void method_28854(Setting var1) {
      this.recoveredField3903 = var1;
   }

   public String getName() {
      return this.recoveredField3893;
   }

   public List<Setting> getSettingsList() {
      return this.recoveredField3890;
   }

   public List<String> method_28839() {
      return this.recoveredField3892;
   }

   public void setDefaultTranslations(float var1, float var2) {
      this.xTranslation = var1;
      this.yTranslation = var2;
      this.defaultXTranslation = var1;
      this.defaultYTranslation = var2;
   }

   public void scaleAndTranslate(ScaledResolution var1) {
      this.scaleAndTranslate(var1, this.recoveredField3889, this.recoveredField3894);
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
      return this.recoveredField3894;
   }

   public void method_28838(boolean var1) {
      this.recoveredField3904 = var1;
   }

   public float getPreviewIconHeight() {
      return this.previewIconHeight;
   }

   public List<Object> method_28782() {
      return this.recoveredField3900;
   }

   public String method_28798() {
      return this.recoveredField3909;
   }

   public void method_28789(boolean var1) {
      this.recoveredField3905 = var1;
   }

   public void method_28859(String[] var1) {
      this.recoveredField3897 = var1;
   }

   public void setPreviewLabel(String var1, float var2) {
      this.previewType = AbstractModule.PreviewType.LABEL;
      this.previewLabel = var1;
      this.previewLabelSize = var2;
   }

   public float getPreviewLabelSize() {
      return this.previewLabelSize;
   }

   public void method_28807(String... var1) {
      this.recoveredField3892 = Arrays.asList(var1);
   }

   public AbstractModule.PreviewType getPreviewType() {
      return this.previewType;
   }

   public void method_28803(Setting var1) {
      this.recoveredField3911 = var1;
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
      this.recoveredField3895 = var1;
   }

   public void method_28861(float var1) {
      this.defaultYTranslation = var1;
   }

   public boolean method_28866() {
      return this.recoveredField3899;
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
      this.recoveredField3896 = var1;
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
      return this.recoveredField3910;
   }

   public Setting method_28797() {
      return this.recoveredField3903;
   }

   public void method_28837(float var1) {
      this.previewIconWidth = var1;
   }

   public AbstractModule(String var1) {
      this.staffModuleEnabled = false;
      this.defaultState = false;
      this.recoveredField3899 = true;
      this.recoveredField3902 = true;
      this.recoveredField3912 = true;
      this.recoveredField3905 = false;
      this.enabled = false;
      this.recoveredField3906 = false;
      this.recoveredField3904 = false;
      this.recoveredField3913 = false;
      this.xTranslation = 0.0F;
      this.yTranslation = 0.0F;
      this.defaultXTranslation = 0.0F;
      this.defaultYTranslation = 0.0F;
      this.recoveredField3889 = 0.0F;
      this.recoveredField3894 = 0.0F;
      this.recoveredField3897 = new String[]{"OFF", "Above", "Below", "At", "Not At"};
      this.recoveredField3893 = var1;
      this.recoveredField3890 = new ArrayList<>();
      this.recoveredField3900 = new ArrayList<>();
      this.recoveredField3909 = "Global";
      this.recoveredField3895 = new Setting(this, "Scale", "Change the scale of the mod.")
         .setValue(1.0F)
         .setMinMax(0.5F, 1.5F)
         .method_08892("x")
         .method_08915("1.0x");
      this.recoveredField3908 = new Setting(this, "GUI Scale", "Change the scale of the mod.")
         .setValue(this.recoveredField3909)
         .acceptedValues("Global", "Default", "Small", "Normal", "Large", "Auto")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField3911 = new Setting(this, "Toggle Mod Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3903 = new Setting(this, "Toggle HUD Keybind").setValue(0).method_08909(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3898 = new Setting(this, "Temp Toggle HUD Keybind")
         .setValue(0)
         .method_08909(false)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.method_28856(KeyPressEvent.class, this::method_28813);
      this.method_28856(MouseClickEvent.class, this::method_28817);
   }

   public List<String> method_28808() {
      return this.recoveredField3907;
   }

   public boolean isStaffEnabledModule() {
      return this.staffModuleEnabled;
   }

   public void method_28780(String var1) {
      this.recoveredField3901 = var1;
   }

   public void setTranslations(float var1, float var2) {
      this.xTranslation = var1;
      this.yTranslation = var2;
   }

   public void method_28779(Setting var1) {
      this.recoveredField3898 = var1;
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
      return this.recoveredField3906;
   }

   public void method_28831(boolean var1) {
      this.recoveredField3902 = var1;
   }

   public void setXTranslation(float var1) {
      this.xTranslation = var1;
   }

   public static enum PreviewType {
      LABEL,
      ICON;
      // $VF: synthetic field
      public static AbstractModule.PreviewType[] recoveredField2025 = new AbstractModule.PreviewType[]{
         AbstractModule.PreviewType.LABEL, AbstractModule.PreviewType.ICON
      };
   }
}
