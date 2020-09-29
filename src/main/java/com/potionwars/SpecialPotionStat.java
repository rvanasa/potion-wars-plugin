package com.potionwars;

public enum SpecialPotionStat {
	EXPLOSIVE_RADIUS("Explosive Force"),
	FLAMMABILITY("Flammability"),
	POISON("Poison"),
	DAMAGE("Damage"),
	RADIUS("Splash Radius"),
	ADD_NUM_BLOCKS("Construction"),
	JUMP("Jump Boost");

	private final String displayName;

	SpecialPotionStat(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
