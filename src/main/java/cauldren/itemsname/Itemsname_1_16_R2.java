package cauldren.itemsname;
//import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Itemsname_1_16_R2 implements Itemsname{

	@Override
	public String getItemStackName(ItemStack stack){

		String name = "";
		int amt = stack.getAmount();

		try {
			name =  stack.getType().toString();
		} catch (Exception e) {
			name = stack.getType().toString();
		}

		if(amt > 1)
			name += " §3x " + amt;

		return name;
	}

}