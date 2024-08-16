package net.diemond_player.unidye.entity.layer;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.entity.client.model.DyeableShulkerEntityModel;
import net.diemond_player.unidye.entity.client.renderer.DyeableBedBlockEntityRenderer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.data.client.Model;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer CUSTOM_SHULKER =
            new EntityModelLayer(new Identifier(Unidye.MOD_ID, "shulker_custom"), "main");
    public static final EntityModelLayer CUSTOM_BED_FOOT =
            new EntityModelLayer(new Identifier(Unidye.MOD_ID, "custom_bed_foot"), "main");
    public static final EntityModelLayer CUSTOM_BED_HEAD =
            new EntityModelLayer(new Identifier(Unidye.MOD_ID, "custom_bed_head"), "main");
}