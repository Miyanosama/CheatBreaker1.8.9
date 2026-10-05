package net.minecraft.crash;

import com.cheatbreaker.client.util.friend.FriendsManager;
import java.util.concurrent.Callable;
import net.minecraft.block.state.pattern.BlockPattern$CacheLoader;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.item.ItemEditableBook;
import net.optifine.util.CacheObjectArray;
import recovered.unidentified.UnidentifiedClass1750;

public class CrashReport$2 implements Callable<String> {
   public CacheObjectArray field_0003;
   public ServerAddress field_0005;
   public FriendsManager field_0002;
   public ItemEditableBook field_0004;
   public UnidentifiedClass1750 field_0000;
   public BlockPattern$CacheLoader field_0001;

   public String call() {
      return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
   }

   public CrashReport$2(CrashReport var1) {
      this.this$0 = var1;
      super();
   }
}
