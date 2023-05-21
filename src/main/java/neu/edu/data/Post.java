package neu.edu.data;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

public class Post {
	private String id;
	
	private String username;
	private String title;
	private String content;
	private String date;
	
	private ObjectId replyid;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ObjectId getReplyid() {
		return replyid;
	}

	public void setReplyid(ObjectId replyid) {
		this.replyid = replyid;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", username=" + username + ", title=" + title + ", content=" + content + ", date="
				+ date + ", replyid=" + replyid + "]";
	}

	
	
	
}
