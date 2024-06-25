package dev.drtheo.aitenergized.compat;

import net.fabricmc.loader.api.FabricLoader;

public class DependencyChecker {

	private static final boolean HAS_BOTARIUM = doesModExist("botarium");

	public static boolean doesModExist(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}

	public static boolean hasBotarium() {
		return HAS_BOTARIUM;
	}
}
