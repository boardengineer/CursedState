package cursedstate.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import savestate.actions.ActionState;
import thecursed.actions.IncreasePlayersMaxHPAction;

public class IncreaseMaxHpActionState implements ActionState {
    private final int increaseHpAmount;


    public IncreaseMaxHpActionState(AbstractGameAction action) {
        this.increaseHpAmount = ReflectionHacks
                .getPrivate(action, IncreasePlayersMaxHPAction.class, "increaseHpAmount");
    }

    @Override
    public AbstractGameAction loadAction() {
        return new IncreasePlayersMaxHPAction(increaseHpAmount);
    }
}
