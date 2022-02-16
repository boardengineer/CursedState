package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.LuckyCharmPower;

public class LuckyCharmPowerState extends PowerState {
    public LuckyCharmPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new LuckyCharmPower(targetAndSource, amount);
    }
}
