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
		t = new Thread(this,threadName);
		a = auto;
		// t.start();
	}

	@Override
	public synchronized void run() {
		System.out.println(threadName + " wants to update Option " + oldName);
		while (!a.isEditable()) {
			try {
				System.out.println(threadName + " waits to update Option " + oldName);
				wait();
			} catch (InterruptedException e) {
				System.out.println(threadName + " begins to update Option " + oldName);
			}
		}
		System.out.println(threadName + " starts to update Option " + oldName);
		try {
			a.updateOptionName(setName, oldName, newName);
			System.out.println(threadName + " updated Option " + oldName + " to " + newName);
			notifyAll();
			System.out.println(threadName + " notifies All!");
			a.print();
		} catch (AutoException e) {
			System.out.println(threadName + " can't find Option " + oldName + "!");
		}
	}

	public void updateOptionName(String setName, String oldName, String newName) {
		this.setName = setName;
		this.oldName = oldName;
		this.newName = newName;
	}
	
	public void start() {
		t.start();
	}

}
