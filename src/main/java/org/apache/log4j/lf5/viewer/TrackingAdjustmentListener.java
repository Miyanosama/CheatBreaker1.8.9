package org.apache.log4j.lf5.viewer;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class TrackingAdjustmentListener implements AdjustmentListener {
   public int _lastMaximum = -1;

   public void adjustmentValueChanged(AdjustmentEvent var1) {
      Adjustable var2 = var1.getAdjustable();
      int var3 = var2.getMaximum();
      if (var2.getMaximum() != this._lastMaximum) {
         int var4 = var2.getValue() + var2.getVisibleAmount();
         if (var4 + var2.getUnitIncrement() >= this._lastMaximum) {
            var2.setValue(var2.getMaximum());
         }

         this._lastMaximum = var3;
      }
   }
}
