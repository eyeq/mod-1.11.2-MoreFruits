package eyeq.morefruits.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class ItemPearGold extends ItemAppleGold {
    public ItemPearGold(int amount, float saturation, boolean isWolfFood) {
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
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
        player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
        player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 6000, 0));
    }
}
