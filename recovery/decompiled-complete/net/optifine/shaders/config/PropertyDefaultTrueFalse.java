package net.optifine.shaders.config;

import com.cheatbreaker.client.module.type.keystrokes.KeystrokesModule;
import io.netty.handler.codec.marshalling.ContextBoundUnmarshallerProvider;
import net.minecraft.entity.monster.EntityGuardian$GuardianTargetSelector;
import net.optifine.Lang;
import recovered.unidentified.UnidentifiedClass5074;

public class PropertyDefaultTrueFalse extends Property {
   public static String[] USER_VALUES = new String[]{"Default", "ON", "OFF"};
   public static String[] PROPERTY_VALUES = new String[]{"default", "true", "false"};
   public KeystrokesModule field_0002;
   public ContextBoundUnmarshallerProvider field_0003;
   public UnidentifiedClass5074 field_0001;
   public EntityGuardian$GuardianTargetSelector field_0004;

   public PropertyDefaultTrueFalse(String var1, String var2, int var3) {
      super(var1, PROPERTY_VALUES, var2, USER_VALUES, var3);
   }

   public boolean isFalse() {
      return this.getValue() == 2;
   }

   public boolean isDefault() {
      return this.getValue() == 0;
   }

   public boolean isTrue() {
      return this.getValue() == 1;
   }

   @Override
   public String getUserValue() {
      return this.isDefault() ? Lang.getDefault() : (this.isTrue() ? Lang.getOn() : (this.isFalse() ? Lang.getOff() : super.getUserValue()));
   }
}
