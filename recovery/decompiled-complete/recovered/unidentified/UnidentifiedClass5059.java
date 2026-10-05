package recovered.unidentified;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.attribute.BasicFileAttributes;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.world.gen.structure.StructureVillagePieces$Field1;
import net.optifine.entity.model.ModelAdapterHeadHumanoid;
import net.optifine.texture.PixelFormat;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryElement;

public class UnidentifiedClass5059 extends SimpleFileVisitor<Path> {
   public StructureVillagePieces$Field1 field_0003;
   public PixelFormat field_0005;
   public BlockWorkbench field_0002;
   public ModelAdapterHeadHumanoid field_0000;
   public CategoryElement field_0001;
   public UnidentifiedClass4240 field_0006;

   public FileVisitResult method_30046(Path var1, BasicFileAttributes var2) {
      File var3 = var1.toFile();
      if (UnidentifiedClass4327.method_26228(var3)) {
         var1.register(
            UnidentifiedClass4855.method_28979(this.field_0004),
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY,
            StandardWatchEventKinds.ENTRY_DELETE
         );
         return FileVisitResult.CONTINUE;
      } else {
         return UnidentifiedClass4327.method_26232(var3) ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
      }
   }

   public UnidentifiedClass5059(UnidentifiedClass4855 var1) {
      this.field_0004 = var1;
      super();
   }
}
