package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.BleedPower;

public class BleedPowerState extends PowerState {
    public BleedPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        // All bleeding so far has come from the player, this may be a problem if it chanes
        return new BleedPower(AbstractDungeon.player, targetAndSource, amount);
    }
}
