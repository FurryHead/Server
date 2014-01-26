package org.hyperion.rs2.model.content.combat;

import org.hyperion.rs2.action.impl.AttackAction;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Graphic;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.container.Equipment;
import org.hyperion.rs2.model.content.combat.magic.MagicAssistant;

/**
 * Combat state of a player.
 * @author Thomas Nappo
 */
public class CombatAssistant {
	
	/**
	 * Constructs a new combat state.
	 * @param player The player who's states we are managing.
	 */
	public CombatAssistant(Player player) {
		this.player = player;
	}
	
	/**
	 * The player who's states we are managing.
	 */
	private Player player;
	
	/**
	 * The attack action of this player.
	 */
	private AttackAction attackAction;
	
	/**
	 * Gets the attack action of this player.
	 * @return The attack action of this player.
	 */
	public AttackAction getAttackAction() {
		return attackAction;
	}
	
	/**
	 * Sets this player's attack action.
	 * @param action The attack action of this player.
	 */
	public void setAttackAction(AttackAction action) {
		this.attackAction = action;
	}
	
	/**
	 * The special handler for the player.
	 */
	private SpecialHandler specialHandler = new SpecialHandler(player);
	
	/**
	 * Gets the special handler for the player.
	 * @return The special handler for the player.
	 */
	public SpecialHandler getSpecialHandler() {
		return specialHandler;
	}
	
	/**
	 * The magic assistant for the player.
	 */
	private MagicAssistant magicAssistant = new MagicAssistant(player);
	
	/**
	 * Gets the magic assistant for the player.
	 * @return the magic assistant for the player.
	 */
	public MagicAssistant getMagicAssistant() {
		return magicAssistant;
	}
	
	/**
	 * Gets the player's attack animation.
	 * @return The player's attack animation.
	 */
	public Animation getAttackAnimation() {
		int anim = 422;
		Item weapon = player.getEquipment().get(Equipment.SLOT_WEAPON);
		if(weapon != null) {
			String name = weapon.getDefinition().getName();
			if(name.contains("2h")) {
				anim = 407;
			}
			if(name.contains("scimitar")) {
				anim = 451;
			}
			if(name.contains("drag") && name.contains("dagger")) {
				anim = 407;
			}
			if(name.contains("longsword")) {
				anim = 407;
			}
			switch(weapon.getId()) {
			case 4734: //Karils cross
				anim = 2075;
					break;
			case 4151: // Whip
				anim = 1658;
				break;
			case 841: // Bows
			case 843:
			case 845:
			case 849:
			case 853:
			case 851:
			case 861:
				anim = 426;
				break;
			case 4153: // Gmaul
				anim = 1665;
				break;
			case 4718: // Dharok
				anim = 2067;
				break;
			}
		}
		return Animation.create(anim);
	}
	
	/**
	 * Gets the attack animation for the weapon wielded.
	 * @return
	 */
	public Animation getDefenseAnimation() {
		if(player.getEquipment().get(Equipment.SLOT_SHIELD) != null) {
			return Animation.create(0x328);
		}
		int anim = 424;
		Item weapon = player.getEquipment().get(Equipment.SLOT_WEAPON);
		if(weapon != null) {
			switch(weapon.getId()) {
			case 4151: // Whip
				anim = 1659;
				break;
			case 4153:// Gmaul
				anim = 1666;
				break;
			case 4718: // Dharok
				anim = 2063;
				break;
			}
		}
		return Animation.create(anim);
	}
	
	/**
	 * An enum for all arrow types, this includes the drop rate percentage of this arrow 
	 * (the higher quality the less likely it is to disappear).
	 * @author Michael Bull
	 * @author Sir Sean
	 */
	public static enum ArrowType {
		
		BRONZE_ARROW(0.75, Graphic.create(19, 0, 100), 10),
		
		IRON_ARROW(0.7, Graphic.create(18, 0, 100), 9),
		
		STEEL_ARROW(0.65, Graphic.create(20, 0, 100), 11),
		
		MITHRIL_ARROW(0.6, Graphic.create(21, 0, 100), 12),
		
		ADAMANT_ARROW(0.5, Graphic.create(22, 0, 100), 13),
		
		RUNE_ARROW(0.4, Graphic.create(24, 0, 100), 15),
		
		BOLT_RACK(1.1, null, 27),
		
		DRAGON_ARROW(0.3, Graphic.create(1111, 0, 100), 1115),
		
		BRONZE_BOLT(0.75, null, 27),
		
		IRON_BOLT(0.7, null, 27),
		
		STEEL_BOLT(0.65, null, 27),
		
		MITHRIL_BOLT(0.6, null, 27),
		
		ADAMANT_BOLT(0.5, null, 27),
		
		RUNE_BOLT(0.4, null, 27),
		
		CRYSTAL_ARROW(1.1, Graphic.create(249, 0, 100), 250);

		/**
		 * The percentage chance for the arrow to disappear once fired.
		 */
		private double dropRate;
		
		/**
		 * The pullback graphic.
		 */
		private Graphic pullback;
		
		/**
		 * The projectile id.
		 */
		private int projectile;
		
		/**
		 * Constructs a new arrow type.
		 * @param dropRate The percentage chance for the arrow to disappear once fired.
		 * @param pullback The pullback graphic.
		 * @param projectile The projectile id.
		 */
		private ArrowType(double dropRate, Graphic pullback, int projectile) {
			this.dropRate = dropRate;
			this.pullback = pullback;
			this.projectile = projectile;
		}
		
		/**
		 * Gets the arrow's percentage chance to disappear once fired
		 * @return The arrow's percentage chance to disappear once fired.
		 */
		public double getDropRate() {
			return dropRate;
		}
		
		/**
		 * Gets the arrow's pullback graphic.
		 * @return The arrow's pullback graphic.
		 */
		public Graphic getPullbackGraphic() {
			return pullback;
		}
		
		/**
		 * Gets the arrow's projectile id.
		 * @return The arrow's projectile id.
		 */
		public int getProjectileId() {
			return projectile;
		}
	}
	
	/**
	 * Gets a pullback graphic for the player's arrow equipped.
	 * @return A pullback graphic for the player's arrow equipped.
	 */
	public Graphic getPullbackGraphic() {
		Item arrow = player.getEquipment().get(Equipment.SLOT_ARROWS);
		if(arrow == null) {
			return Graphic.create(-1);
		}
		String name = arrow.getDefinition().getName().toUpperCase().replaceAll(" ", "_");
		for(ArrowType entry : ArrowType.values()) {
			if(entry.name().equalsIgnoreCase(name)) {
				return entry.getPullbackGraphic();
			}
		}
		return Graphic.create(-1);
	}
	
	/**
	 * Gets a projectile for the player's arrow equipped.
	 * @return A projectile for the player's arrow equipped.
	 */
	public int getProjectile() {
		Item arrow = player.getEquipment().get(Equipment.SLOT_ARROWS);
		if(arrow == null) {
			return -1;
		}
		String name = arrow.getDefinition().getName().toUpperCase().replaceAll(" ", "_");
		for(ArrowType entry : ArrowType.values()) {
			if(entry.name().equalsIgnoreCase(name)) {
				return entry.getProjectileId();
			}
		}
		return -1;
	}

}
