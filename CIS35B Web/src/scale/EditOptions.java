package scale;

import model.Automobile;

public class EditOptions implements Runnable {
	private Thread t;
	private String threadName;
	private Automobile a;
	private String setName;
	private String oldName;
	private String newName;
	private int opNum;

	public EditOptions(String name, Automobile auto) {
		threadName = name;
		t = new Thread(this, threadName);
		a = auto;
	}

	/**
	 * Switch operation based on opNum
	 */
	public void ops() {
		switch (opNum) {
		case 0:
			System.out.println(threadName + " is too lazy to run!");
			break;
		case 1:
			a.updateOptionName(threadName,setName, oldName, newName);
			break;
		case 2:
			a.updateOptionNamenotSync(threadName,setName, oldName, newName);
			break;
		}
	}
	
	@Override
	public void run() {
		ops();		
	}

	/**
	 * Assign values to be updated
	 * @param setName
	 * @param oldName
	 * @param newName
	 */
	public void prepUpdateOptionName(int op,String setName, String oldName, String newName) {
		this.setName = setName;
		this.oldName = oldName;
		this.newName = newName;
		opNum = op;
	}

	public void start() {
		t.start();
	}

}
