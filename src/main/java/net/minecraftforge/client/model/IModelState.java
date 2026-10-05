package net.minecraftforge.client.model;

import com.google.common.base.Optional;
import net.minecraftforge.client.model.IModelPart;

public interface IModelState {
   Optional<TRSRTransformation> apply(Optional<? extends IModelPart> var1);
}
