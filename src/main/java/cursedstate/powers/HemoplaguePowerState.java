package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.HemoplaguePower;

public class HemoplaguePowerState extends PowerState {
    public HemoplaguePowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new HemoplaguePower(targetAndSource, amount);
    }
}
