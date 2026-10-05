package net.minecraft.realms;

import io.netty.bootstrap.AbstractBootstrap$1;
import java.lang.reflect.Constructor;
import net.minecraft.block.BlockSapling$1;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.renderer.entity.RenderItem$5;
import net.minecraft.init.Bootstrap$13;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedClass1818;

public class RealmsBridge extends RealmsScreen {
   public BlockSapling$1 field_0003;
   public Bootstrap$13 field_0006;
   public AbstractBootstrap$1 field_0002;
   public AnvilConverterException field_0005;
   public RenderItem$5 field_0000;
   public UnidentifiedClass1818 field_0001;
   public GuiScreen previousScreen;
   public static Logger LOGGER = LogManager.getLogger();

   public GuiScreenRealmsProxy getNotificationScreen(GuiScreen var1) {
      try {
         this.previousScreen = var1;
         Class var2 = Class.forName("com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen");
         Constructor var3 = var2.getDeclaredConstructor(RealmsScreen.class);
         var3.setAccessible(true);
         Object var4 = var3.newInstance(this);
         return ((RealmsScreen)var4).getProxy();
      } catch (Exception var5) {
         LOGGER.error("Realms module missing", var5);
         return null;
      }
   }

   public void switchToRealms(GuiScreen var1) {
      this.previousScreen = var1;

      try {
         Class var2 = Class.forName("com.mojang.realmsclient.RealmsMainScreen");
         Constructor var3 = var2.getDeclaredConstructor(RealmsScreen.class);
         var3.setAccessible(true);
         Object var4 = var3.newInstance(this);
         Minecraft.getMinecraft().displayGuiScreen(((RealmsScreen)var4).getProxy());
      } catch (Exception var5) {
         LOGGER.error("Realms module missing", var5);
      }
   }

   @Override
   public void init() {
      Minecraft.getMinecraft().displayGuiScreen(this.previousScreen);
   }
}
