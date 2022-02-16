package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.ManaCirculationPower;

public class ManaCirculationPowerState extends PowerState {
    public ManaCirculationPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new ManaCirculationPower(targetAndSource, amount);
    }
}
