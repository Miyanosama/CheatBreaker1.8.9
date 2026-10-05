package net.optifine.shaders;

import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.entity.passive.EntityHorse;
import net.optifine.entity.model.anim.ModelUpdater;
import org.slf4j.helpers.BasicMDCAdapter$1;

public class ProgramStack {
   public Deque<Program> stack = new ArrayDeque<>();
   public EntityHorse field_0003;
   public BasicMDCAdapter$1 field_0000;
   public ModelUpdater field_0002;

   public void push(Program var1) {
      this.stack.addLast(var1);
      if (this.stack.size() > 100) {
         throw new RuntimeException("Program stack overflow: " + this.stack.size());
      }
   }

   public Program pop() {
      if (this.stack.isEmpty()) {
         throw new RuntimeException("Program stack empty");
      } else {
         return this.stack.pollLast();
      }
   }
}
