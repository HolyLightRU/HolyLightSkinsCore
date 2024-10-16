
package de.HolyLight.skinshifter.common.skin.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import de.HolyLight.skinshifter.common.skin.Skin;

public class SkinRecorderService {

    // The skins that are currently worn by the players with the given names
    private static Map<String, Skin> recordedSkins = new HashMap<>();

    public static Optional<Skin> getRecordedSkinOf(String playerName) {

        Validate.notBlank(playerName, "Cannot retrieve the recorded skin of a player with a blank name");

        return Optional.ofNullable(recordedSkins.get(playerName));
    }

    public static void recordSkinSet(String playerName, Skin skin) {

        Validate.notBlank(playerName, "Cannot record the skin of a player with a blank name");
        Validate.notNull(skin, "Cannot record a null skin");

        recordedSkins.put(playerName, skin);
    }

    public static void recordSkinClear(String playerName) {

        Validate.notBlank(playerName, "Cannot record the skin clear action for a player with a blank name");

        recordedSkins.remove(playerName);
    }

    private SkinRecorderService() {}

}
