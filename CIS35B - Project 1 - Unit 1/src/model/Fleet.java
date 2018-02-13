package model;
import java.util.LinkedHashMap;

import exception.AutoException;

public class Fleet <T extends Automobile> {
	private LinkedHashMap<String, T> fleet;
	
	public Fleet ()
	{
		fleet = new LinkedHashMap<String, T>();
	}
	
	public T getAuto (String autoID) throws AutoException
	{
		if (fleet.get(autoID) != null)
			return fleet.get(autoID);
		else throw new AutoException(204);
	}
	
	public void addAuto (T a)
	{
		fleet.put(a.getAutoID(), a);
	}
	
	public T deleteAuto (String autoID)
	{
		return fleet.remove(autoID);
	}
	
	public T updateAuto (String autoID, T a)
	{
		return fleet.replace(autoID, a);
	}
	
	public boolean isEmpty() {
		return fleet.isEmpty();
	}
	
	public boolean containsKey(String autoID) {
		return fleet.containsKey(autoID);
	}
}
