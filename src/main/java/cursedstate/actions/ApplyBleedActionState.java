package cursedstate.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import savestate.actions.ActionState;
import thecursed.actions.ApplyBleedAction;

public class ApplyBleedActionState implements ActionState {
    private final int amount;
    private final int sourceIndex;
    private final int targetIndex;

    public ApplyBleedActionState(AbstractGameAction action) {
        this.amount = action.amount;
        this.sourceIndex = ActionState.indexForCreature(action.source);
        this.targetIndex = ActionState.indexForCreature(action.target);
    }

    @Override
    public AbstractGameAction loadAction() {
        return new ApplyBleedAction(ActionState.creatureForIndex(targetIndex), ActionState
                .creatureForIndex(sourceIndex), amount);
    }
}
