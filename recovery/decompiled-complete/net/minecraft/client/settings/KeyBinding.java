package net.minecraft.client.settings;

import com.cheatbreaker.client.nethandler.server.PacketUpdateNametags;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.netty.channel.AbstractChannel$AbstractUnsafe$3;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.util.IntHashMap;
import recovered.unidentified.UnidentifiedClass1852;

public class KeyBinding implements Comparable<KeyBinding> {
   public int keyCodeDefault;
   public String keyCategory;
   public UnidentifiedClass1852 field_0005;
   public String keyDescription;
   public S38PacketPlayerListItem field_0001;
   public static Set<String> keybindSet = Sets.newHashSet();
   public PacketUpdateNametags field_0012;
   public int keyCode;
   public AbstractChannel$AbstractUnsafe$3 field_0003;
   public boolean field_0013;
   public int pressTime;
   public static List<KeyBinding> keybindArray = Lists.newArrayList();
   public boolean pressed;
   public static IntHashMap<KeyBinding> hash = new IntHashMap<>();

   public static void onTick(int var0) {
      if (var0 != 0) {
         KeyBinding var1 = hash.lookup(var0);
         if (var1 != null) {
            var1.pressTime++;
         }
      }
   }

   public String getKeyCategory() {
      return this.keyCategory;
   }

   public static void setKeyBindState(int var0, boolean var1) {
      if (var0 != 0) {
         KeyBinding var2 = hash.lookup(var0);
         if (var2 != null) {
            var2.pressed = var1;
         }
      }
   }

   public String getKeyDescription() {
      return this.keyDescription;
   }

   public static void unPressAllKeys() {
      for (KeyBinding var1 : keybindArray) {
         var1.unpressKey();
      }
   }

   public boolean isPressed() {
      if (this.pressTime == 0) {
         return false;
      } else {
         this.pressTime--;
         return true;
      }
   }

   public static void resetKeyBindingArrayAndHash() {
      hash.clearMap();

      for (KeyBinding var1 : keybindArray) {
         hash.addKey(var1.keyCode, var1);
      }
   }

   public int getKeyCodeDefault() {
      return this.keyCodeDefault;
   }

   public void unpressKey() {
      this.pressTime = 0;
      this.pressed = false;
   }

   public boolean isKeyDown() {
      return this.pressed;
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   public KeyBinding(String var1, int var2, String var3, boolean var4) {
      this.pressed = false;
      this.keyDescription = var1;
      this.keyCode = var2;
      this.keyCodeDefault = var2;
      this.keyCategory = var3;
      this.field_0013 = var4;
      keybindArray.add(this);
      hash.addKey(var2, this);
      keybindSet.add(var3);
   }

   public static Set<String> getKeybinds() {
      return keybindSet;
   }

   public void setKeyCode(int var1) {
      this.keyCode = var1;
   }

   public KeyBinding(String var1, int var2, String var3) {
      this.keyDescription = var1;
      this.keyCode = var2;
      this.keyCodeDefault = var2;
      this.keyCategory = var3;
      keybindArray.add(this);
      hash.addKey(var2, this);
      keybindSet.add(var3);
   }

   public int compareTo(KeyBinding var1) {
      int var2 = I18n.format(this.keyCategory).compareTo(I18n.format(var1.keyCategory));
      if (var2 == 0) {
         var2 = I18n.format(this.keyDescription).compareTo(I18n.format(var1.keyDescription));
      }

      return var2;
   }
}
