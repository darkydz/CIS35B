package model;
import java.util.LinkedHashMap;

public class Fleet <T extends Automobile> {
	private LinkedHashMap<String, T> fleet;
	
	public Fleet ()
	{
		fleet = new LinkedHashMap<String, T>();
	}
	
	public T getAuto (String autoID)
	{
		return fleet.get(autoID);
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
}
