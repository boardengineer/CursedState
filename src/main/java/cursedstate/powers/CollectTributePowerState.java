package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.CollectTributePower;

public class CollectTributePowerState extends PowerState {
    public CollectTributePowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new CollectTributePower(targetAndSource, amount);
    }
}
