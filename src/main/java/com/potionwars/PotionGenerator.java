package com.potionwars;

import org.bukkit.potion.PotionData;

import java.util.Random;

public class PotionGenerator {

    private static final Random RANDOM = new Random();

    public PotionGenerator(){
    }

    public static SpecialPotion generatePotion(SpecialPotion a, SpecialPotion b){

        RANDOM.setSeed(a.getSeed() ^ b.getSeed());
        SpecialPotion childPotion = new SpecialPotion(RANDOM.nextInt());
        //Loop through all stats and randomly select properties
        for(SpecialPotionStat stat: SpecialPotionStat.values()){
            childPotion.setStat(stat, RANDOM.nextBoolean()? a.getStat(stat):b.getStat(stat));
        }
        return  childPotion;
    }

}
