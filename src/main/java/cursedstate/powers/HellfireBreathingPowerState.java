package cursedstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import thecursed.powers.HellfireBreathingPower;

public class HellfireBreathingPowerState extends PowerState {
    public HellfireBreathingPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        return new HellfireBreathingPower(targetAndSource, amount);
    }
}
