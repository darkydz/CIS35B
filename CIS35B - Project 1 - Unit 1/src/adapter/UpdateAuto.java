package adapter;

import exception.AutoException;

public interface UpdateAuto {
	public void updateOptionSetName(String autoID, String OptionSetname, String newName) throws AutoException;
	public void updateOptionPrice(String autoID, String Optionname, String Option, float newprice) throws AutoException;
		
}
