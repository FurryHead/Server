package org.hyperion.rs2.model;

public class Bonus {

	private Player player;

	public Bonus(Player player) {
		this.player = player;
	}

	public String BonusName[] = { "Stab", "Slash", "Crush", "Magic", "Range",
			"Stab", "Slash", "Crush", "Magic", "Range", "Strength", "Prayer" };

	public void setBonus() {
		for (int index = 0; index < 13; index++) {
			if (player.getEquipment().get(index) == null)
				continue;
			for (int bonuses = 0; bonuses < 12; bonuses++) {
				player.setBonuses(bonuses, player.getBonusList().get(bonuses)
						+ ItemDefinition.forId(
								player.getEquipment().get(index).getId()).getBonuses()[bonuses]);
			}
		}
	}

	public void sendBonus() {
		int offset = 0;
		String send = "";

		for (int i = 0; i < 12; i++) {
			player.setBonuses(i, 0);
		}

		setBonus();
		for (int i = 0; i < 12; i++) {
			if (player.getBonuses(i) >= 0) {
				send = BonusName[i] + ": +" + player.getBonuses(i);
			} else {
				send = BonusName[i] + ": -" + Math.abs(player.getBonuses(i));
			}

			if (i == 10) {
				offset = 1;
			}
			player.getActionSender().sendString((1675 + i + offset), send);
		}
	}
}
