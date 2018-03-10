package server;

import java.io.*;
import java.net.*;
import java.util.Properties;
import adapter.*;
import client.*;

public class Server extends ProxyAutomobile {
	public void readPropObjectIntoAuto(Properties props)
	{
		buildAuto(props);
	}
}
