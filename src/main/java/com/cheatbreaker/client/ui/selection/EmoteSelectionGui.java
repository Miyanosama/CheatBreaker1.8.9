package com.cheatbreaker.client.ui.selection;

import com.cheatbreaker.client.ui.selection.SelectionGui;

import com.cheatbreaker.client.ui.selection.SelectionOption;

import com.cheatbreaker.client.CheatBreaker;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmoteSelectionGui extends SelectionGui {
   public EmoteSelectionGui(int var1) {
      super(
         var1,
         CheatBreaker.getInstance()
            .method_19783()
            .method_01369()
            .stream()
            .map(CheatBreaker.getInstance().method_19783()::method_01372)
            .filter(Objects::nonNull)
            .limit(8L)
            .map(var0 -> new SelectionOption(var0, var0.method_02056(), var0.method_02053()))
            .collect(Collectors.toList())
      );
      this.recoveredField1386 = var0 -> {};
   }

   @Override
   public boolean b_() {
      return false;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
   }
}
