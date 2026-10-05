package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import com.google.common.collect.ImmutableBiMap;
import io.netty.handler.codec.socks.SocksAuthRequestDecoder$1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.storage.MapStorage;

public class UnidentifiedClass0189 {
   public static ImmutableBiMap<Object, Object> field_0004 = ImmutableBiMap.builder()
      .put(0, UnidentifiedClass0030.class)
      .put(1, UnidentifiedClass0606.class)
      .put(2, UnidentifiedClass4798.class)
      .put(3, UnidentifiedClass4157.class)
      .put(4, UnidentifiedClass0998.class)
      .put(5, UnidentifiedClass1182.class)
      .put(6, UnidentifiedClass1790.class)
      .put(7, UnidentifiedClass1688.class)
      .put(8, UnidentifiedClass1113.class)
      .build();
   public boolean field_0007;
   public boolean field_0003;
   public Map<UUID, UnidentifiedClass0300> field_0006;
   public Map<UUID, ModelPlayer> field_0000;
   public UnidentifiedClass0300 field_0001;
   public List<Integer> field_0008 = new ArrayList<>();
   public MapStorage field_0005;
   public SocksAuthRequestDecoder$1 field_0002;

   public UnidentifiedClass0189() {
      this.field_0006 = new ConcurrentHashMap<>();
      this.field_0000 = new ConcurrentHashMap<>();
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_01375);
      CheatBreaker.getInstance().method_19817().method_21938(UnidentifiedClass3810.class, this::method_01378);
      CheatBreaker.getInstance().method_19817().method_21938(UnidentifiedClass3398.class, var1 -> {
         if (var1.method_21080() == 0 && var1.method_21079()) {
            this.method_01379(Minecraft.getMinecraft().thePlayer);
         }
      });
      CheatBreaker.getInstance()
         .method_19817()
         .method_21938(
            UnidentifiedClass0806.class,
            var1 -> {
               if (Minecraft.getMinecraft().currentScreen == null
                  && !this.field_0008.isEmpty()
                  && var1.method_05523() == CheatBreaker.getInstance().getGlobalSettings().field_0109.getKeyCode()) {
                  Minecraft.getMinecraft().displayGuiScreen(new UnidentifiedClass4400(var1.method_05523()));
               }
            }
         );
   }

   public UnidentifiedClass0300 method_01371() {
      return this.field_0001;
   }

   public UnidentifiedClass0300 method_01372(int var1) {
      if (!field_0004.containsKey(var1)) {
         return null;
      } else {
         try {
            return (UnidentifiedClass0300)((Class)field_0004.get(var1)).newInstance();
         } catch (Exception var3) {
            var3.printStackTrace();
            return null;
         }
      }
   }

   public void method_01378(UnidentifiedClass3810 var1) {
      UnidentifiedClass0300 var2 = this.field_0006.get(var1.method_23155().aK());
      if (var2 != null) {
         if (var1.method_23156() == UnidentifiedEnum0393.field_0000) {
            var2.method_00244(var1.method_23155(), var1.method_23158(), var1.method_23157());
         } else {
            var2.method_00243(var1.method_23155(), var1.method_23157());
         }
      }
   }

   public void method_01379(AbstractClientPlayer var1) {
      if (this.field_0006.containsKey(var1.aK())) {
         this.field_0001 = null;
         UnidentifiedClass0300 var2 = this.field_0006.get(var1.aK());
         var2.method_02054(var1);
         this.field_0006.remove(var1.aK());
      }
   }

   public Map<UUID, ModelPlayer> method_01383() {
      return this.field_0000;
   }

   public List<Integer> method_01369() {
      return this.field_0008;
   }

   public void method_01373(UnidentifiedClass0300 var1) {
      if (this.method_01370(var1)) {
         if (this.field_0007) {
            this.field_0007 = false;
            this.field_0003 = true;
         }

         this.field_0001 = var1;
         this.method_01379(Minecraft.getMinecraft().thePlayer);
         int var2 = (Integer)field_0004.inverse().get(var1.getClass());
         CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new UnidentifiedClass1954(Minecraft.getMinecraft().thePlayer.aK(), var2));
      }
   }

   public void method_01381(boolean var1) {
      this.field_0007 = var1;
   }

   public boolean method_01382() {
      return this.field_0003;
   }

   public boolean method_01367() {
      return this.field_0007;
   }

   public void method_01380(AbstractClientPlayer var1, UnidentifiedClass0300 var2) {
      if (var1.aK().equals(Minecraft.getMinecraft().thePlayer.aK())) {
         if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 || this.field_0003) {
            Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
            this.field_0007 = true;
         }

         this.field_0003 = false;
      }

      this.field_0001 = var2;
      this.field_0006.putIfAbsent(var1.aK(), var2);
   }

   public void method_01385(boolean var1) {
      this.field_0003 = var1;
   }

   public void method_01384(UnidentifiedClass0300 var1) {
      this.field_0001 = var1;
   }

   public boolean method_01370(UnidentifiedClass0300 var1) {
      return this.field_0008.contains(field_0004.inverse().get(var1));
   }

   public Map<UUID, UnidentifiedClass0300> method_01368() {
      return this.field_0006;
   }

   public void method_01375(TickEvent var1) {
      if (!this.field_0006.isEmpty()) {
         ArrayList var2 = new ArrayList();
         this.field_0006.forEach((var2x, var3) -> {
            if (Minecraft.getMinecraft().theWorld != null) {
               EntityPlayer var4 = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(var2x);
               if (var3.method_02055()) {
                  this.field_0001 = null;
                  this.field_0006.remove(var4.aK());
                  var3.method_02054((AbstractClientPlayer)var4);
                  ModelPlayer var5 = this.method_01383().get(var2x);
                  if (var5 == null || var5.bipedCape == null) {
                     return;
                  }

                  var5.bipedCape.rotateAngleZ = 0.0F;
                  this.method_01383().remove(var2x);
                  var2.add(var2x);
               }
            }
         });
         var2.forEach(this.field_0006::remove);
      }
   }
}
