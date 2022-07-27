package core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(category = Category.MISC, name = "StraightToTheGE", author = "MimicVirus", version = 1.0, description = "Just a simple script that walks to the Grand Exchange.")
public class Main extends AbstractScript {
    private final Area GE = new Area(3160, 3486, 3169, 3480);
    @Override
    public void onStart() {
        super.onStart();
        log("Script successfully started.");
    }

    @Override
    public int onLoop() {
        if (!GE.contains(Players.localPlayer())) {
            do {
                Walking.walk(GE.getRandomTile());
                walkDelay();
            } while (!GE.contains(Players.localPlayer()));
        }

        if (GE.contains(Players.localPlayer())) {
            ScriptManager.getScriptManager()
                    .stop();
        }

        return 1000;
    }

    private void walkDelay() {
        Main.sleepUntil(() ->
                Walking.getDestinationDistance() < Calculations.random(3, 8), Calculations.random(3000, 6000));
    }
}
