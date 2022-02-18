package cursedstate;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import battleaimod.BattleAiMod;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import cursedstate.actions.ExhaustCurseThenActivateActionState;
import cursedstate.actions.IncreaseMaxHpActionState;
import cursedstate.actions.ReleasedKnowledgeActionState;
import cursedstate.heuristics.CursesAndStatusesFirstHeuristic;
import cursedstate.heuristics.PlayHeuristic;
import cursedstate.patches.HellsGateOpenPowerPatch;
import cursedstate.powers.*;
import savestate.StateFactories;
import savestate.actions.ActionState;
import savestate.actions.CurrentActionState;
import savestate.powers.PowerState;
import thecursed.actions.ExhaustCurseThenActivateAction;
import thecursed.actions.IncreasePlayersMaxHPAction;
import thecursed.actions.ReleasedKnowledgeAction;
import thecursed.cards.skill.PrepareRite;
import thecursed.powers.*;

import static thecursed.enums.AbstractCardEnum.THE_CURSED_PURPLE;

@SpireInitializer
public class CursedState implements PostInitializeSubscriber, EditCardsSubscriber {
    public static void initialize() {
        BaseMod.subscribe(new CursedState());
    }

    @Override
    public void receivePostInitialize() {
        populateCurrentActionsFactory();
        populateActionsFactory();
        populatePowerFactory();

        BattleAiMod.actionHeuristics
                .put(ExhaustCurseThenActivateAction.class, new CursesAndStatusesFirstHeuristic());
        BattleAiMod.cardPlayHeuristics.add(new PlayHeuristic());


        HellsGateOpenPowerPatch.createCursedCardPoorl();
    }

    private void populateCurrentActionsFactory() {
        StateFactories.currentActionByClassMap
                .put(ExhaustCurseThenActivateAction.class, new CurrentActionState.CurrentActionFactories(action -> new ExhaustCurseThenActivateActionState(action)));
    }

    private void populateActionsFactory() {
        StateFactories.actionByClassMap
                .put(ReleasedKnowledgeAction.class, new ActionState.ActionFactories(action -> new ReleasedKnowledgeActionState()));
        StateFactories.actionByClassMap
                .put(IncreasePlayersMaxHPAction.class, new ActionState.ActionFactories(action -> new IncreaseMaxHpActionState(action)));
    }

    private void populatePowerFactory() {
        StateFactories.powerByIdMap
                .put(BleedPower.POWER_ID, new PowerState.PowerFactories(power -> new BleedPowerState(power)));
        StateFactories.powerByIdMap
                .put(CollectTributePower.POWER_ID, new PowerState.PowerFactories(power -> new CollectTributePowerState(power)));
        StateFactories.powerByIdMap
                .put(DemonicPactPower.POWER_ID, new PowerState.PowerFactories(power -> new DemonicPactPowerState(power)));
        StateFactories.powerByIdMap
                .put(EmpowerCirclesPower.POWER_ID, new PowerState.PowerFactories(power -> new EmpowerCirclesPowerState(power)));
        StateFactories.powerByIdMap
                .put(HellfireBreathingPower.POWER_ID, new PowerState.PowerFactories(power -> new HellfireBreathingPowerState(power)));
        StateFactories.powerByIdMap
                .put(HellsGateOpenPower.POWER_ID, new PowerState.PowerFactories(power -> new HellsGateOpenPowerState(power)));
        StateFactories.powerByIdMap
                .put(HemoplaguePower.POWER_ID, new PowerState.PowerFactories(power -> new HemoplaguePowerState(power)));
        StateFactories.powerByIdMap
                .put(LuckyCharmPower.POWER_ID, new PowerState.PowerFactories(power -> new LuckyCharmPowerState(power)));
        StateFactories.powerByIdMap
                .put(ManaCirculationPower.POWER_ID, new PowerState.PowerFactories(power -> new ManaCirculationPowerState(power)));
        StateFactories.powerByIdMap
                .put(OutForBloodPower.POWER_ID, new PowerState.PowerFactories(power -> new OutForBloodPowerState(power)));
        StateFactories.powerByIdMap
                .put(SpitefulPlanPower.POWER_ID, new PowerState.PowerFactories(power -> new SpitefulPlanPowerState(power)));
        StateFactories.powerByIdMap
                .put(VoidFormPower.POWER_ID, new PowerState.PowerFactories(power -> new VoidFormPowerState(power)));
    }

    @Override
    public void receiveEditCards() {
        // Needs Action State
        BaseMod.removeCard(PrepareRite.ID, THE_CURSED_PURPLE);
    }
}