package cursedstate.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import savestate.SaveStateMod;
import thecursed.relics.SoulVessel;

public class RelicDescriptionPatches {
    @SpirePatch(clz = SoulVessel.class, method = "updateDescriptionWithCounter")
    public static class SoulVesselNoDesUpdatePatch {
        @SpirePrefixPatch
        public static SpireReturn doNothing(SoulVessel relic) {
            if (SaveStateMod.shouldGoFast) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
