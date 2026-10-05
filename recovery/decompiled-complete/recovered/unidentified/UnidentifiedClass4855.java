package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class UnidentifiedClass4855 {
   public WatchService field_0000;

   public boolean method_28978() {
      boolean var2 = false;
      WatchKey var1;
      if (this.field_0000 != null && (var1 = this.field_0000.poll()) != null) {
         for (WatchEvent var4 : var1.pollEvents()) {
            File var5 = ((Path)var1.watchable()).resolve(var4.context().toString()).toFile();
            if (UnidentifiedClass4327.method_26232(var5) || UnidentifiedClass4327.method_26228(var5)) {
               var2 = true;
               break;
            }
         }

         var1.reset();
      }

      return var2;
   }

   public UnidentifiedClass4855(Path var1) {
      try {
         this.field_0000 = FileSystems.getDefault().newWatchService();
         Files.walkFileTree(var1, new UnidentifiedClass5059(this));
      } catch (IOException var3) {
         CheatBreaker.getInstance().method_19789().error("Failed to create watch service", var3);
      }
   }
}
