package com.cheatbreaker.client.util.screenshot;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.List;

public class FileListTransferable implements Transferable {
   public List recoveredField2591;

   @Override
   public Object getTransferData(DataFlavor var1) {
      return this.recoveredField2591;
   }

   public FileListTransferable(List var1) {
      this.recoveredField2591 = var1;
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
