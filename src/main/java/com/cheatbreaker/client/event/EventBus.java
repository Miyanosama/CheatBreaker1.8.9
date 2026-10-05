package com.cheatbreaker.client.event;

import com.cheatbreaker.client.CheatBreaker;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventBus {
   public ConcurrentHashMap<Class<? extends EventBus$Event>, CopyOnWriteArrayList<Consumer<EventBus$Event>>> recoveredField2205 = new ConcurrentHashMap<>();

   public <T extends EventBus$Event> boolean method_21938(Class<T> var1, Consumer<T> var2) {
      return this.recoveredField2205.computeIfAbsent(var1, var0 -> new CopyOnWriteArrayList<>()).add((Consumer<EventBus$Event>)(Consumer<?>)var2);
   }

   public void method_21935(EventBus$Event var1) {
      try {
         for (Class var2 = var1.getClass(); var2 != null && var2 != EventBus$Event.class; var2 = var2.getSuperclass()) {
            CopyOnWriteArrayList<Consumer<EventBus$Event>> var3 = this.recoveredField2205.get(var2);
            if (var3 != null) {
               var3.forEach(var1x -> var1x.accept(var1));
            }
         }
      } catch (Exception var4) {
         System.err.println("EventBus [" + var1.getClass() + "]");
         var4.printStackTrace();
      }
   }

   public EventBus() {
      CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Created EventBus");
   }

   public <T extends EventBus$Event> void method_21939(Class<T> var1, Consumer<T> var2) {
      CopyOnWriteArrayList<Consumer<EventBus$Event>> var3 = this.recoveredField2205.get(var1);
      if (var3 != null) {
         var3.remove(var2);
      }
   }
}
