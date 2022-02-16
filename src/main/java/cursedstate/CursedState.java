package cursedstate;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class CursedState implements PostInitializeSubscriber {
    public static void initialize() {
        BaseMod.subscribe(new CursedState());
    }

    @Override
    public void receivePostInitialize() {
        System.err.println("hello world");
    }
}