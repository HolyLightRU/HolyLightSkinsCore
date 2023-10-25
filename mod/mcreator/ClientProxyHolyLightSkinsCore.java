package mod.mcreator;

public class ClientProxyHolyLightSkinsCore extends CommonProxyHolyLightSkinsCore {

    @Override
    public void registerRenderers(HolyLightSkinsCore ins) {
        ins.mcreator_0.registerRenderers();
        ins.mcreator_1.registerRenderers();

    }
}
