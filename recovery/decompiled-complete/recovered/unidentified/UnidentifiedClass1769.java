package recovered.unidentified;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import junit.awtui.TestRunner;
import net.minecraft.command.PlayerSelector$5;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;
import org.java_websocket.exceptions.InvalidHandshakeException;

public class UnidentifiedClass1769 implements TextListener {
   public BiomeGenHills field_0003;
   public StructureMineshaftPieces field_0005;
   public EntityItem field_0002;
   public PlayerSelector$5 field_0004;
   public TestRunner field_0000;
   public InvalidHandshakeException field_0001;

   public void textValueChanged(TextEvent var1) {
      this.field_0000.fRun.setEnabled(this.field_0000.fSuiteField.getText().length() > 0);
      this.field_0000.fStatusLine.setText("");
   }

   public UnidentifiedClass1769(TestRunner var1) {
      this.field_0000 = var1;
   }
}
