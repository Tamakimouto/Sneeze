package object;

/**
 * Self Explanatory Object Model.
 *
 * Consists of getters and setters for use by Freemarker.
 *
 * @author  Anthony Zheng   <Anthony@fopen-dream.space>
 * @author  Robin Guice
 * @package object
 */
public class Sneeze {
    String user;
    String msg;

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
