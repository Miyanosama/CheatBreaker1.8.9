package net.minecraft.client.gui.stream;

import io.netty.channel.socket.DatagramPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager$ColorLogicState;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProviderEnd;
import net.optifine.entity.model.ModelAdapterCreeper;
import net.optifine.entity.model.ModelAdapterIronGolem;
import net.optifine.entity.model.anim.RenderEntityParameterBool$1;

public class GuiIngestServers extends GuiScreen {
   public GuiIngestServers$ServerList field_152311_g;
   public DatagramPacket field_0007;
   public WorldProviderEnd field_0003;
   public RenderEntityParameterBool$1 field_0006;
   public String field_152310_f;
   public Vec3 field_0001;
   public ModelAdapterIronGolem field_0008;
   public ModelAdapterCreeper field_0005;
   public GuiScreen field_152309_a;
   public GlStateManager$ColorLogicState field_0009;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.field_152311_g.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.field_152310_f, this.l / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void a_() {
      if (this.j.getTwitchStream().func_152908_z()) {
         this.j.getTwitchStream().func_152932_y().func_153039_l();
      }
   }

   @Override
   public void initGui() {
      this.field_152310_f = I18n.format("options.stream.ingest.title");
      this.field_152311_g = new GuiIngestServers$ServerList(this, this.j);
      if (!this.j.getTwitchStream().func_152908_z()) {
         this.j.getTwitchStream().func_152909_x();
      }

      this.n.add(new GuiButton(1, this.l / 2 - 155, this.m - 24 - 6, 150, 20, I18n.format("gui.done")));
      this.n.add(new GuiButton(2, this.l / 2 + 5, this.m - 24 - 6, 150, 20, I18n.format("options.stream.ingest.reset")));
   }

   public GuiIngestServers(GuiScreen var1) {
      this.field_152309_a = var1;
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 1) {
            this.j.displayGuiScreen(this.field_152309_a);
         } else {
            this.j.gameSettings.streamPreferredServer = "";
            this.j.gameSettings.saveOptions();
         }
      }
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.field_152311_g.handleMouseInput();
   }
}
