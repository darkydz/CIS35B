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
		t = new Thread(this, threadName);
		a = auto;
		// t.start();
	}

	@Override
	public synchronized void run() {
		test();
	}

	public void updateOptionName(String setName, String oldName, String newName) {
		this.setName = setName;
		this.oldName = oldName;
		this.newName = newName;
	}
	
	private synchronized void test()
	{
		System.out.println(threadName + " wants to update Option " + oldName);
		while (!a.isEditable()) {
			try {
				System.out.println(threadName + " waits to update Option " + oldName);
				t.wait();
			} catch (InterruptedException e) {
				System.out.println(threadName + " begins to update Option " + oldName);
			}
		}
		System.out.println(threadName + " starts to update Option " + oldName);
		a.setEditable(false);
		if (a.updateOptionName(setName, oldName, newName)) {
			System.out.println(threadName + " updated Option " + oldName + " to " + newName);
			a.print();
		} else {
			System.out.println(threadName + " can't find Option " + oldName + "!");
		}
		a.setEditable(true);
		System.out.println(threadName + " made Auto editable again!");
		t.notifyAll();
		System.out.println(threadName + " notifies All!");
	}

	public void start() {
		t.start();
	}

}
