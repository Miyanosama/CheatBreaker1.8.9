package junit.swingui;

import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.util.dash.CBDashManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.minecraft.block.BlockHalfStoneSlabNew;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.world.biome.BiomeGenSnow;

public class TestSelector$KeySelectListener extends KeyAdapter {
   public GuiScreen field_0003;
   public TestSelector field_0005;
   public PlayerProfileCache field_0002;
   public CBDashManager field_0004;
   public BlockHalfStoneSlabNew field_0000;
   public BiomeGenSnow field_0001;
   public GlobalSettings field_0006;

   public void keyTyped(KeyEvent var1) {
      this.field_0005.keySelectTestClass(var1.getKeyChar());
   }

   public TestSelector$KeySelectListener(TestSelector var1) {
      this.field_0005 = var1;
   }
}
