package object;

public class Sneeze {
	private String user;
	private String msg;
	
	public Sneeze(String user, String msg) {
		this.user = user;
		this.msg = msg;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
