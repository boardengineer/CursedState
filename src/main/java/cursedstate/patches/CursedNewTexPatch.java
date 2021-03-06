package cursedstate.patches;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;
import org.clapper.util.classutil.*;
import savestate.SaveStateMod;
import thecursed.cards.curse.Dregs;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.util.ArrayList;

@SpirePatch(
        clz = CardCrawlGame.class,
        method = SpirePatch.CONSTRUCTOR
)
public class CursedNewTexPatch {
    public static void Raw(CtBehavior ctBehavior) throws NotFoundException, CannotCompileException {
        ClassFinder finder = new ClassFinder();

        finder.add(new File(Loader.STS_JAR));

        for (ModInfo modInfo : Loader.MODINFOS) {
            if (modInfo.ID.equals("thecursed") && modInfo.jarURL != null) {
                try {
                    finder.add(new File(modInfo.jarURL.toURI()));
                } catch (URISyntaxException e) {
                    // do nothing
                }
            }
        }

        ClassFilter filter = new AndClassFilter(
                new NotClassFilter(new InterfaceOnlyClassFilter()),
                new ClassModifiersClassFilter(Modifier.PUBLIC),
                new RegexClassFilter("thecursed.relics.*")
        );

        ArrayList<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass ctClass = ctBehavior.getDeclaringClass().getClassPool()
                                        .get(classInfo.getClassName());

            try {
                CtConstructor[] methods = ctClass.getConstructors();
                for (CtConstructor m : methods) {
                    m.instrument(new ExprEditor() {
                        @Override
                        public void edit(NewExpr f) throws CannotCompileException {
                            if (f.getClassName().equals(Texture.class.getName())) {
                                f.replace(String.format("if(%s.isSimulation()) {" +
                                        "$_ = %s.tex; } else {" +
                                        "$_ = $proceed($$);" +
                                        "}", CursedNewTexPatch.class
                                        .getName(), CursedNewTexPatch.class.getName()));
                            }
                        }
                    });
                }
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        }

        //
        ClassFilter cardsFilter = new AndClassFilter(
                new NotClassFilter(new InterfaceOnlyClassFilter()),
                new ClassModifiersClassFilter(Modifier.PUBLIC),
                new RegexClassFilter("thecursed.cards.*")
        );

        ClassFinder cardFinder = new ClassFinder();

        cardFinder.add(new File(Loader.STS_JAR));

        for (ModInfo modInfo : Loader.MODINFOS) {
            if (modInfo.ID.equals("thecursed") && modInfo.jarURL != null) {
                try {
                    cardFinder.add(new File(modInfo.jarURL.toURI()));
                } catch (URISyntaxException e) {
                    // do nothing
                }
            }
        }

        ArrayList<ClassInfo> foundCardClasses = new ArrayList<>();
        cardFinder.findClasses(foundCardClasses, cardsFilter);

        for (ClassInfo classInfo : foundCardClasses) {
            CtClass ctClass = ctBehavior.getDeclaringClass().getClassPool()
                                        .get(classInfo.getClassName());

            try {
                CtConstructor[] methods = ctClass.getConstructors();
                for (CtConstructor m : methods) {
                    m.instrument(new ExprEditor() {
                        @Override
                        public void edit(NewExpr f) throws CannotCompileException {
                            if (f.getClassName().equals(Dregs.class.getName())) {
                                f.replace(String.format("if(%s.isSimulation()) {" +
                                        "$_ = %s.dregs; } else {" +
                                        "$_ = $proceed($$);" +
                                        "}", CursedNewTexPatch.class
                                        .getName(), CursedNewTexPatch.class.getName()));
                            }
                        }
                    });
                }
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }

        }
    }

    //TODO: Make blank texture
    public static final Texture tex = ImageMaster.BLOCK_BAR_B;
    public static AbstractCard dregs;

    public static boolean isSimulation() {
        return SaveStateMod.shouldGoFast;
    }
}