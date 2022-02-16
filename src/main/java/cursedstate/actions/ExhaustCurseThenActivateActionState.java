package cursedstate.actions;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import savestate.actions.ActionState;
import savestate.actions.CurrentActionState;
import thecursed.actions.ExhaustCurseThenActivateAction;

import java.util.ArrayList;

public class ExhaustCurseThenActivateActionState implements CurrentActionState {
    ArrayList<ActionState> targetActionStates;

    public ExhaustCurseThenActivateActionState(AbstractGameAction action) {
        ArrayList<AbstractGameAction> targetActions = ReflectionHacks
                .getPrivate(action, ExhaustCurseThenActivateAction.class, "targetActions");
        targetActionStates = ActionState.toActionStateArray(targetActions);
    }

    @Override
    public AbstractGameAction loadCurrentAction() {
        // The string is only cosmetic
        ExhaustCurseThenActivateAction result = new ExhaustCurseThenActivateAction(ActionState
                .toGameActions(targetActionStates), "");

        // This should make the action only trigger the second half of the update
        ReflectionHacks
                .setPrivate(result, AbstractGameAction.class, "duration", 0);

        return result;
    }

    @SpirePatch(
            clz = ExhaustCurseThenActivateAction.class,
            paramtypez = {},
            method = "update"
    )
    public static class HalfDoneActionPatch {
        public static void Postfix(ExhaustCurseThenActivateAction _instance) {
            // Force the action to stay in the the manager until cards are selected
            if (AbstractDungeon.isScreenUp) {
                _instance.isDone = false;
            }
        }
    }
}
