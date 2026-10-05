package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.EventBus$Event;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import net.minecraft.util.MinecraftError;

public class UnidentifiedClass3556 {
   public ConcurrentHashMap<Class<? extends EventBus$Event>, CopyOnWriteArrayList<Consumer<EventBus$Event>>> field_0000 = new ConcurrentHashMap<>();
   public MinecraftError field_0001;

   public <T extends EventBus$Event> boolean method_21938(Class<T> var1, Consumer<T> var2) {
      return this.field_0000.computeIfAbsent(var1, var0 -> new CopyOnWriteArrayList<>()).add(var2);
   }

   public void method_21935(EventBus$Event var1) {
      try {
         for (Class var2 = var1.getClass(); var2 != null && var2 != EventBus$Event.class; var2 = var2.getSuperclass()) {
            CopyOnWriteArrayList var3 = this.field_0000.get(var2);
            if (var3 != null) {
               var3.forEach(var1x -> var1x.accept(var1));
            }
         }
      } catch (Exception var4) {
         System.err.println("EventBus [" + var1.getClass() + "]");
         var4.printStackTrace();
      }
   }

   public UnidentifiedClass3556() {
      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Created EventBus");
   }

   public <T extends EventBus$Event> void method_21939(Class<T> var1, Consumer<T> var2) {
      CopyOnWriteArrayList var3 = this.field_0000.get(var1);
      if (var3 != null) {
         var3.remove(var2);
      }
   }
}
