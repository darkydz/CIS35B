package adapter;

import exception.AutoException;

public interface AutoChoice {
	public double getTotal(String autoID) throws AutoException;
	public void setChoice(String autoID, String setName, String optionName) throws AutoException;
}
