package org.apache.log4j.chainsaw;

import net.optifine.shaders.uniform.ShaderUniformM4;

public class MyTableModel$Processor implements Runnable {
   public ShaderUniformM4 field_0000;
   public MyTableModel this$0;

   public MyTableModel$Processor(MyTableModel var1, MyTableModel$1 var2) {
      this(var1);
   }

   public void run() {
      while (true) {
         try {
            Thread.sleep(8878178238528095210L & -8878178239308883987L);
         } catch (InterruptedException var7) {
         }

         synchronized (MyTableModel.access$000(this.this$0)) {
            if (!MyTableModel.access$100(this.this$0)) {
               boolean var2 = true;
               boolean var3 = false;

               for (EventDetails var5 : MyTableModel.access$200(this.this$0)) {
                  MyTableModel.access$300(this.this$0).add(var5);
                  var2 = var2 && var5 == MyTableModel.access$300(this.this$0).first();
                  var3 = var3 || MyTableModel.access$400(this.this$0, var5);
               }

               MyTableModel.access$200(this.this$0).clear();
               if (var3) {
                  MyTableModel.access$500(this.this$0, var2);
               }
            }
         }
      }
   }

   public MyTableModel$Processor(MyTableModel var1) {
      this.this$0 = var1;
      super();
   }
}
