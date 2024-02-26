package com.trustysidekick.dragonrealm.block.custom;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.Message;
import com.mojang.serialization.MapCodec;
import com.trustysidekick.dragonrealm.block.entity.TestBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TestBlock extends BlockWithEntity {
    public static final MapCodec<TestBlock> CODEC = createCodec(TestBlock::new);
    //public static final BooleanProperty CRAFTING;

    @Override
    public MapCodec<TestBlock> getCodec() {
        return CODEC;
    }


    public TestBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()));
    }

    //@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient()) {
            //if(state.get(CRAFTING)){

            //}
            player.sendMessage(Text.literal("Message"));
        }

        return ActionResult.PASS;
    }


    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TestBlockEntity(pos, state);
    }



    //static {
    //    CRAFTING = Properties.CRAFTING;
    //}


}
