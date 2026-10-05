/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public enum RenderEntityParameterFloat implements IExpressionFloat
{
    LIMB_SWING("limb_swing"),
    LIMB_SWING_SPEED("limb_speed"),
    AGE("age"),
    HEAD_YAW("head_yaw"),
    HEAD_PITCH("head_pitch"),
    SCALE("scale"),
    HEALTH("health"),
    HURT_TIME("hurt_time"),
    IDLE_TIME("idle_time"),
    MAX_HEALTH("max_health"),
    MOVE_FORWARD("move_forward"),
    MOVE_STRAFING("move_strafing"),
    PARTIAL_TICKS("partial_ticks"),
    POS_X("pos_x"),
    POS_Y("pos_y"),
    POS_Z("pos_z"),
    REVENGE_TIME("revenge_time"),
    SWING_PROGRESS("swing_progress");

    public String name;
    public static RenderEntityParameterFloat[] VALUES;
    public RenderManager renderManager;

    static {
        VALUES = RenderEntityParameterFloat.values();
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.FLOAT;
    }

    @Override
    public float eval() {
        Render render = this.renderManager.renderRender;
        if (render == null) {
            return 0.0f;
        }
        if (render instanceof RendererLivingEntity) {
            RendererLivingEntity rendererLivingEntity = (RendererLivingEntity)render;
            switch (this) {
                case LIMB_SWING: {
                    return rendererLivingEntity.recoveredField20;
                }
                case LIMB_SWING_SPEED: {
                    return rendererLivingEntity.recoveredField18;
                }
                case AGE: {
                    return rendererLivingEntity.recoveredField23;
                }
                case HEAD_YAW: {
                    return rendererLivingEntity.recoveredField19;
                }
                case HEAD_PITCH: {
                    return rendererLivingEntity.recoveredField21;
                }
                case SCALE: {
                    return rendererLivingEntity.recoveredField24;
                }
            }
            EntityLivingBase entityLivingBase = rendererLivingEntity.renderEntity;
            if (entityLivingBase == null) {
                return 0.0f;
            }
            switch (this) {
                case HEALTH: {
                    return entityLivingBase.getHealth();
                }
                case HURT_TIME: {
                    return entityLivingBase.au;
                }
                case IDLE_TIME: {
                    return entityLivingBase.bh();
                }
                case MAX_HEALTH: {
                    return entityLivingBase.getMaxHealth();
                }
                case MOVE_FORWARD: {
                    return entityLivingBase.ba;
                }
                case MOVE_STRAFING: {
                    return entityLivingBase.aZ;
                }
                case POS_X: {
                    return (float)entityLivingBase.s;
                }
                case POS_Y: {
                    return (float)entityLivingBase.t;
                }
                case POS_Z: {
                    return (float)entityLivingBase.u;
                }
                case REVENGE_TIME: {
                    return entityLivingBase.getRevengeTimer();
                }
                case SWING_PROGRESS: {
                    return entityLivingBase.getSwingProgress(rendererLivingEntity.recoveredField22);
                }
            }
        }
        return 0.0f;
    }

    public String getName() {
        return this.name;
    }

    public static RenderEntityParameterFloat parse(String string) {
        if (string == null) {
            return null;
        }
        for (int i = 0; i < VALUES.length; ++i) {
            RenderEntityParameterFloat renderEntityParameterFloat = VALUES[i];
            if (!renderEntityParameterFloat.getName().equals(string)) continue;
            return renderEntityParameterFloat;
        }
        return null;
    }

    RenderEntityParameterFloat(String string2) {
        this.name = string2;
        this.renderManager = Minecraft.getMinecraft().getRenderManager();
    }
}

