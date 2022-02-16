package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.EmpowerCirclesPower;

public class EmpowerCirclesPowerState extends PowerState {
    public EmpowerCirclesPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new EmpowerCirclesPower(targetAndSource, amount);
    }
}
