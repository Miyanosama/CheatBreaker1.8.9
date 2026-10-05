package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.handler.ssl.JettyNpnSslEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import junit.runner.TestCaseClassLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RoomCrossing;
import net.optifine.Mipmaps;

public class UnidentifiedClass4790 {
   public TestCaseClassLoader field_0002;
   public StructureStrongholdPieces$RoomCrossing field_0004;
   public Mipmaps field_0001;
   public Minecraft field_0003 = Minecraft.getMinecraft();
   public JettyNpnSslEngine field_0000;

   public UnidentifiedClass4790() {
      CheatBreaker.getInstance().method_19817().method_21938(UnidentifiedClass1944.class, this::method_28652);
   }

   public void method_28652(UnidentifiedClass1944 var1) {
      HashMap var2 = new HashMap();

      for (String var4 : this.field_0003.gameSettings.resourcePacks) {
         var2.put(var4, null);
      }

      for (ResourcePackRepository$Entry var7 : this.field_0003.getResourcePackRepository().getRepositoryEntries()) {
         var2.put(var7.getResourcePackName(), var7);
      }

      for (ResourcePackRepository$Entry var8 : UnidentifiedClass4327.method_26229()) {
         var2.put(var8.getResourcePackName(), var8);
      }

      var2.values().removeIf(Objects::isNull);
      this.field_0003.getResourcePackRepository().setRepositories(new ArrayList<>(var2.values()));
      this.field_0003.refreshResources();
      Runtime.getRuntime().addShutdownHook(new Thread(UnidentifiedClass3581::method_22036));
   }
}
