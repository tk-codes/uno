package ServerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {
		
	private /*@ spec_public nullable @*/ Server myServer;
	
	public void setServer(Server server){
		myServer = server;
	}
	
	public void drawCard() {
		if(myServer.canPlay)
			myServer.requestCard();	
	}
	
	public void sayUNO() {
		if(myServer.canPlay)
			myServer.submitSaidUNO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	
}
