package cursedstate.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import savestate.actions.ActionState;
import thecursed.actions.ReleasedKnowledgeAction;

public class ReleasedKnowledgeActionState implements ActionState {
    @Override
    public AbstractGameAction loadAction() {
        return new ReleasedKnowledgeAction();
    }
}
