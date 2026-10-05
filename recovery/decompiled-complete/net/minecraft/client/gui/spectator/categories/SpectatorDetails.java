package net.minecraft.client.gui.spectator.categories;

import com.google.common.base.Objects;
import io.netty.channel.AbstractChannel$AbstractUnsafe$2;
import io.netty.channel.embedded.EmbeddedChannel$LastInboundHandler;
import io.netty.util.ThreadDeathWatcher$Watcher;
import java.util.List;
import javax.vecmath.Color4b;
import javax.vecmath.Tuple3b;
import junit.swingui.TestSelector$DoubleClickListener;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.world.gen.ChunkProviderSettings$1;

public class SpectatorDetails {
   public int field_178683_c;
   public EmbeddedChannel$LastInboundHandler field_0007;
   public AbstractChannel$AbstractUnsafe$2 field_0003;
   public TestSelector$DoubleClickListener field_0006;
   public ThreadDeathWatcher$Watcher field_0000;
   public Tuple3b field_0001;
   public ChunkProviderSettings$1 field_0008;
   public Color4b field_0005;
   public ISpectatorMenuView field_178684_a;
   public List<ISpectatorMenuObject> field_178682_b;

   public SpectatorDetails(ISpectatorMenuView var1, List<ISpectatorMenuObject> var2, int var3) {
      this.field_178684_a = var1;
      this.field_178682_b = var2;
      this.field_178683_c = var3;
   }

   public ISpectatorMenuObject func_178680_a(int var1) {
      return var1 >= 0 && var1 < this.field_178682_b.size()
         ? (ISpectatorMenuObject)Objects.firstNonNull(this.field_178682_b.get(var1), SpectatorMenu.field_178657_a)
         : SpectatorMenu.field_178657_a;
   }

   public int func_178681_b() {
      return this.field_178683_c;
   }
}
