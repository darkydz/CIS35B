package scale;

import exception.AutoException;
import model.Automobile;

public class EditOptions implements Runnable {
	private Thread t;
	private String threadName;
	private Automobile a;
	private String setName;
	private String oldName;
	private String newName;

	
	public EditOptions(String name, Automobile auto) {
		threadName = name;
		t = new Thread(threadName);
		a = auto;
//		t.start();
	}

	@Override
	public void run() {
		System.out.println(threadName + " wants to update Option " + oldName);
		if (a.isEditable()) {
			try {
				if (a.updateOptionName(setName, oldName, newName))
				{
					System.out.println(threadName + " updated Option " + oldName + " to " + newName);
//					Thread.currentThread().sleep(1000);
				}
				a.setEditable(true);
			} catch (AutoException e) {
				System.out.println(threadName + " can't find Option " + oldName + "!");
			} 
//			catch (InterruptedException e) {
//				System.err.println(e);
//			}
		}	
	}
	
	public void updateOptionName(String setName, String oldName, String newName) {
		this.setName = setName;
		this.oldName = oldName;
		this.newName = newName;
	}

}
