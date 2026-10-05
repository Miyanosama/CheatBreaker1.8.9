package net.minecraft.client.gui;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.network.NetHandlerLoginClient$1;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.optifine.shaders.uniform.ShaderUniform2f;

public class GuiPageButtonList$EditBoxEntry extends GuiPageButtonList$GuiListEntry {
   public NetHandlerLoginClient$1 field_0001;
   public ShaderUniform2f field_0003;
   public NetworkPlayerInfo field_0000;
   public Predicate<String> field_178951_a;

   public Predicate<String> func_178950_a() {
      return this.field_178951_a;
   }

   public GuiPageButtonList$EditBoxEntry(int var1, String var2, boolean var3, Predicate<String> var4) {
      super(var1, var2, var3);
      this.field_178951_a = (Predicate<String>)Objects.firstNonNull(var4, Predicates.alwaysTrue());
   }
}
