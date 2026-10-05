package junit.awtui;

import io.netty.handler.codec.spdy.DefaultSpdyPingFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.net.URL;
import net.minecraft.client.particle.EntityCrit2FX$Factory;
import net.minecraft.item.ItemArmorStand;
import net.optifine.util.LinkedList$1;

public class Logo extends Canvas {
   public EntityCrit2FX$Factory field_0003;
   public LinkedList$1 field_0006;
   public ItemArmorStand field_0002;
   public Image fImage = this.loadImage("logo.gif");
   public static Class class$0;
   public DefaultSpdyPingFrame field_0001;
   public int fHeight;
   public int fWidth;

   public Logo() {
      MediaTracker var1 = new MediaTracker(this);
      var1.addImage(this.fImage, 0);

      try {
         var1.waitForAll();
      } catch (Exception var3) {
      }

      if (this.fImage != null) {
         this.fWidth = this.fImage.getWidth(this);
         this.fHeight = this.fImage.getHeight(this);
      } else {
         this.fWidth = 20;
         this.fHeight = 20;
      }

      this.setSize(this.fWidth, this.fHeight);
   }

   public void paint(Graphics var1) {
      this.paintBackground(var1);
      if (this.fImage != null) {
         var1.drawImage(this.fImage, 0, 0, this.fWidth, this.fHeight, this);
      }
   }

   public void paintBackground(Graphics var1) {
      var1.setColor(SystemColor.control);
      var1.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
   }

   public static Class method_29184(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }

   public Image loadImage(String var1) {
      Toolkit var2 = Toolkit.getDefaultToolkit();

      try {
         URL var3 = (class$0 == null ? (class$0 = method_29184("junit.runner.BaseTestRunner")) : class$0).getResource(var1);
         return var2.createImage((ImageProducer)var3.getContent());
      } catch (Exception var4) {
         return null;
      }
   }
}
