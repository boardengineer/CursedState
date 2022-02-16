package cursedstate;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import battleaimod.BattleAiMod;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import cursedstate.actions.ExhaustCurseThenActivateActionState;
import cursedstate.heuristics.CursesAndStatusesFirstHeuristic;
import cursedstate.patches.HellsGateOpenPowerPatch;
import cursedstate.powers.*;
import savestate.StateFactories;
import savestate.actions.CurrentActionState;
import savestate.powers.PowerState;
import thecursed.actions.ExhaustCurseThenActivateAction;
import thecursed.powers.*;

@SpireInitializer
public class CursedState implements PostInitializeSubscriber {
    public static void initialize() {
        BaseMod.subscribe(new CursedState());
    }

    @Override
    public void receivePostInitialize() {
        populateCurrentActionsFactory();
        populatePowerFactory();

        BattleAiMod.actionHeuristics
                .put(ExhaustCurseThenActivateAction.class, new CursesAndStatusesFirstHeuristic());


        HellsGateOpenPowerPatch.createCursedCardPoorl();
    }

    private void populateCurrentActionsFactory() {
        StateFactories.currentActionByClassMap
                .put(ExhaustCurseThenActivateAction.class, new CurrentActionState.CurrentActionFactories(action -> new ExhaustCurseThenActivateActionState(action)));
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
}