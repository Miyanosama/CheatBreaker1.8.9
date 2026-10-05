package com.cheatbreaker.client.command;

import com.cheatbreaker.client.module.AbstractModule;

public abstract class ModuleCommand {
   public AbstractModule recoveredField2810;
   public String recoveredField2811;

   public String method_00002() {
      return this.recoveredField2811;
   }

   public AbstractModule method_00003() {
      return this.recoveredField2810;
   }

   public abstract void method_00001();

   public ModuleCommand(AbstractModule var1, String var2) {
      this.recoveredField2810 = var1;
      this.recoveredField2811 = var2;
   }
}
