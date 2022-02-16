package cursedstate.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ludicrousspeed.LudicrousSpeedMod;
import thecursed.cards.attack.*;
import thecursed.cards.skill.CursedAmulet;
import thecursed.cards.skill.CursedArmor;
import thecursed.cards.skill.CursedSpellbook;
import thecursed.powers.HellsGateOpenPower;

import java.util.ArrayList;

public class HellsGateOpenPowerPatch {
    public static ArrayList<AbstractCard> cursedCardPool;

    public static void createCursedCardPoorl() {
        cursedCardPool = new ArrayList();
        cursedCardPool.add(new CursedBlade());
        cursedCardPool.add(new CursedBoomerang());
        cursedCardPool.add(new CursedMace());
        cursedCardPool.add(new CursedRelics());
        cursedCardPool.add(new CursedShiv());
        cursedCardPool.add(new CursedStaff());
        cursedCardPool.add(new CursedWand());
        cursedCardPool.add(new CursedAmulet());
        cursedCardPool.add(new CursedArmor());
        cursedCardPool.add(new CursedSpellbook());
    }


    @SpirePatch(clz = HellsGateOpenPower.class, method = SpirePatch.CONSTRUCTOR)
    public static class NoStaticCreationInConstructorPatch {
        @SpireInsertPatch(loc = 35)
        public static SpireReturn<Object> doLess(HellsGateOpenPower hellsGateOpenPower, AbstractCreature owner, int amount) {
            if (LudicrousSpeedMod.plaidMode) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = HellsGateOpenPower.class, method = "atStartOfTurn")
    public static class UseStaticMapPatch {
        @SpirePrefixPatch
        public static SpireReturn useStaticMap(HellsGateOpenPower power) {
            if (LudicrousSpeedMod.plaidMode) {
                for (int i = 0; i < power.amount; ++i) {
                    AbstractDungeon.actionManager
                            .addToBottom(new MakeTempCardInHandAction(cursedCardPool
                                    .get(AbstractDungeon.cardRandomRng
                                            .random(cursedCardPool.size() - 1))));
                }
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }


}
