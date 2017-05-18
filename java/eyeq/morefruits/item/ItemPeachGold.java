package eyeq.morefruits.item;

import eyeq.util.entity.EntityLivingBaseUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class ItemPeachGold extends ItemAppleGold {
    public ItemPeachGold(int amount, float saturation, boolean isWolfFood) {
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
        EntityLivingBaseUtils.clearActiveBadPotions(player);
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 2));
        player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 6000, 2));
    }
}
