package eyeq.morefruits.event;

import com.google.common.collect.Maps;
import eyeq.morefruits.MoreFruits;
import eyeq.util.world.storage.loot.LootTableUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MoreFruitsEventHandler {
    private static final Map<BlockPlanks.EnumType, Item> leaveFruits = Maps.newEnumMap(BlockPlanks.EnumType.class);
    private static final Map<BlockPlanks.EnumType, Item> leaveFruitsGold = Maps.newEnumMap(BlockPlanks.EnumType.class);

    static {
        leaveFruits.put(BlockPlanks.EnumType.OAK, Items.APPLE);
        leaveFruits.put(BlockPlanks.EnumType.OAK, Items.GOLDEN_APPLE);
        leaveFruits.put(BlockPlanks.EnumType.SPRUCE, MoreFruits.grape);
        leaveFruits.put(BlockPlanks.EnumType.SPRUCE, MoreFruits.grapeGold);
        leaveFruits.put(BlockPlanks.EnumType.BIRCH, MoreFruits.peach);
        leaveFruits.put(BlockPlanks.EnumType.BIRCH, MoreFruits.peachGold);
        leaveFruits.put(BlockPlanks.EnumType.JUNGLE, MoreFruits.pineapple);
        leaveFruits.put(BlockPlanks.EnumType.JUNGLE, MoreFruits.pineappleGold);
        leaveFruits.put(BlockPlanks.EnumType.ACACIA, MoreFruits.persimmon);
        leaveFruits.put(BlockPlanks.EnumType.ACACIA, MoreFruits.persimmonGold);
        leaveFruits.put(BlockPlanks.EnumType.DARK_OAK, MoreFruits.pear);
        leaveFruits.put(BlockPlanks.EnumType.DARK_OAK, MoreFruits.pearGold);
    }

    @SubscribeEvent
    public void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        World world = event.getWorld();
        if(world.isRemote) {
            return;
        }
        IBlockState state = event.getState();
        Block block = state.getBlock();
        if(!(block instanceof BlockLeaves)) {
            return;
        }
        BlockPlanks.EnumType type = ((BlockLeaves) block).getWoodType(block.getMetaFromState(state));
        Item fruit = leaveFruits.get(type);
        Item fruitGold = leaveFruitsGold.get(type);
        if(fruit == null) {
            return;
        }
        if(fruitGold == null) {
            fruitGold = fruit;
        }

        List<ItemStack> drops = event.getDrops();
        for(int i = 0; i < drops.size(); i++) {
            ItemStack drop = drops.get(i);
            if(drop != null && drop.getItem() == Items.APPLE) {
                drops.remove(drop);
            }
        }
        int fortune = event.getFortuneLevel();
        int chance = 200;
        if(fortune > 0) {
            chance -= 10 << fortune;
            if(chance < 40) {
                chance = 40;
            }
        }
        Random rand = world.rand;
        if(rand.nextInt(chance) == 0) {
            ItemStack itemStack = rand.nextInt(chance * 16) == 0 ? new ItemStack(fruitGold, 1, 1) : new ItemStack(fruit);
            drops.add(itemStack);
        }
    }

    private void addLootPool(LootPool pool, LootEntry entry) {
        if(entry != null) {
            pool.addEntry(entry);
        }
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        for(LootPool pool : LootTableUtils.getLootPools(event.getTable())) {
            if(pool == null) {
                continue;
            }
            LootEntry entry = pool.getEntry(Items.APPLE.getRegistryName().toString());
            if(entry != null) {
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.grape, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.peach, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.pineapple, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.persimmon, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.pear, entry, ""));
            }
            entry = pool.getEntry(Items.GOLDEN_APPLE.getRegistryName().toString());
            if(entry != null) {
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.grapeGold, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.peachGold, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.pineappleGold, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.persimmonGold, entry, ""));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.pearGold, entry, ""));
            }
            entry = pool.getEntry(Items.GOLDEN_APPLE.getRegistryName().toString() + "#0");
            if(entry != null) {
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.grapeGold, entry, "#0"));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.peachGold, entry, "#0"));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.pineappleGold, entry, "#0"));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.persimmonGold, entry, "#0"));
                addLootPool(pool, LootTableUtils.getLootEntryItem(MoreFruits.pearGold, entry, "#0"));
            }
        }
    }
}
