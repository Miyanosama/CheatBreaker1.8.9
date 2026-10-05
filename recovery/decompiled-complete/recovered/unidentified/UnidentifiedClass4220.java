package recovered.unidentified;

import io.netty.handler.codec.CorruptedFrameException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.List;
import net.minecraft.command.CommandResultStats$1;

public class UnidentifiedClass4220 implements Transferable {
   public CorruptedFrameException field_0001;
   public List field_0002;
   public CommandResultStats$1 field_0000;

   @Override
   public Object getTransferData(DataFlavor var1) {
      return this.field_0002;
   }

   public UnidentifiedClass4220(List var1) {
      this.field_0002 = var1;
   }

   @Override
   public boolean isDataFlavorSupported(DataFlavor var1) {
      return DataFlavor.javaFileListFlavor.equals(var1);
   }

   @Override
   public DataFlavor[] getTransferDataFlavors() {
      return new DataFlavor[]{DataFlavor.javaFileListFlavor};
   }
}
