package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.HellsGateOpenPower;

public class HellsGateOpenPowerState extends PowerState {
    public HellsGateOpenPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new HellsGateOpenPower(targetAndSource, amount);
    }
}
