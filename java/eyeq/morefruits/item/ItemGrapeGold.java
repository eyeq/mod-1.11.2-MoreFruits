package eyeq.morefruits.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class ItemGrapeGold extends ItemAppleGold {
    public ItemGrapeGold(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if(world.isRemote) {
            return;
        }
        if(stack.getMetadata() == 0) {
            super.onFoodEaten(stack, world, player);
            return;
        }
        player.addStat(AchievementList.OVERPOWERED);
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 0));
        player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 6000, 2));
        player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 6000, 0));
        player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 6000, 0));
    }
}
