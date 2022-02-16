package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.SpitefulPlanPower;

public class SpitefulPlanPowerState extends PowerState {
    public SpitefulPlanPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new SpitefulPlanPower(targetAndSource, amount);
    }
}
