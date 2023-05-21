package neu.edu.data.converter;

import java.time.LocalDateTime;

import org.bson.Document;
import org.bson.types.ObjectId;
import neu.edu.data.Post;

public class PostConvertor {
	
	public static Document toDocument(Post post) {

		Document doc = new Document("username",post.getUsername())
				.append("title",post.getTitle())
				.append("content", post.getContent())
				.append("date", post.getDate().toString())
				.append("replyId",post.getReplyid());

		if (post.getId() != null) {
			doc.append("_id", new ObjectId(post.getId()));
		}
		return doc;
	}
	
	public static Post toPost(Document postDoc) {
		System.out.println(postDoc);

		Post post = new Post();
		post.setUsername((String)postDoc.get("username","saparia.m"));
		post.setTitle((String)postDoc.get("title"));
		post.setContent((String)postDoc.get("content"));
		post.setDate((String)postDoc.get("date"));
		post.setReplyid((ObjectId)postDoc.get("replyId"));
		post.setId(((ObjectId) postDoc.get("_id")).toString());
		System.out.println(post);

		return post;
	}
}
