package cauldren.editor;

import com.potionwars.PotionWarsPlugin;
import org.bukkit.entity.Player;

import cauldren.CaulCrafting;
import cauldren.CraftArray;

public class PlayerEditor {

	public static boolean isInEditor(Player player) {
		if (PotionWarsPlugin.editors.containsKey(player.getUniqueId()))
			return true;
		return false;
	}

	public static Editor getEditor(Player player) {
		if (isInEditor(player)) {
			return (PotionWarsPlugin.editors.get(player.getUniqueId()));
		}
		return null;
	}

	public static CraftArray getEditorCraft(Player player) {
		Editor editor = getEditor(player);

		if (editor != null)
			return (editor.getCraft());
		return null;
	}

	public static void exitEditor(Player player) {
		if (isInEditor(player))
			PotionWarsPlugin.editors.remove(player.getUniqueId());
	}
}