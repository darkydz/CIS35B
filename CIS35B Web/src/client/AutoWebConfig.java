package client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface AutoWebConfig {
	public String getSelectAutoPage() throws IOException;
	public String getConfigurePage(String autoID) throws IOException;
	public String getQuotePage(HttpServletRequest request);
}
