package neu.edu.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import neu.edu.data.Post;
import neu.edu.data.converter.PostConvertor;

public class PostDao {

	private MongoCollection<Document> mongoCollectionUsers;

	public PostDao(MongoClient mongo) {
		this.mongoCollectionUsers = mongo.getDatabase("discussionboard").getCollection("posts");
	}
	
	public Post create(Post post) {
        Document postDoc = PostConvertor.toDocument(post);
        this.mongoCollectionUsers.insertOne(postDoc);
        ObjectId id = (ObjectId) postDoc.get("_id");
        post.setId(id.toString());
        return post;
    }
 
    public void update(Post post) {
        this.mongoCollectionUsers.updateOne(Filters.eq("_id", new ObjectId(post.getId())), new Document("$set", PostConvertor.toDocument(post)));
    }
 
    public void delete(String id) {
        this.mongoCollectionUsers.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }
 
    public boolean exists(String id) {
        FindIterable<Document>  doc = this.mongoCollectionUsers.find(Filters.eq("_id", id)).limit(1);
        return doc != null;
    }
 
    public List<Post> getList() {
        List<Post> posts = new ArrayList<Post>();
        MongoCursor<Document>  cursor = mongoCollectionUsers.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Post post = PostConvertor.toPost(doc);
                posts.add(post);
            }
        } finally {
            cursor.close();
        }
        return posts;
    }
 
    public Post getPost(String id) {
        Document doc = this.mongoCollectionUsers.find(Filters.eq("_id", new ObjectId(id))).first();
        return PostConvertor.toPost(doc);
    }

}
