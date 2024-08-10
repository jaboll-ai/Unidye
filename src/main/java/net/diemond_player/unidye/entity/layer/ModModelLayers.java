package net.diemond_player.unidye.entity.layer;

import net.diemond_player.unidye.Unidye;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer CUSTOM_SHULKER =
            new EntityModelLayer(new Identifier(Unidye.MOD_ID, "shulker_custom"), "main");
}