package adapter;

import exception.AutoException;

public interface AutoChoice {
	public double getTotal(String autoID) throws AutoException;
	public void setChoice(String autoID, String setName, String optionName) throws AutoException;
//	public String[] getOptionSetList(String autoID);
//	public String[] getOptionList(String autoID, String setName);
}
