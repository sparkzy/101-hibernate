package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.Post;

public class PostDao implements DAO<Post> {
	
	/************************************************************************************
	 * Constructors
	 ************************************************************************************/
	/**
	 * Create new PostDao
	 */
	public PostDao() {
		super();
		log.info("Constructed new " + this.getClass());
	}
	
	/************************************************************************************
	 * toString
	 ************************************************************************************/
	/**
	 * Represent PostDao as a String of its fields
	 */
	@Override
    public String toString() {
        return "PostDao []";
    }
	
	/************************************************************************************
	 * Create
	 ************************************************************************************/
	/**
	 * Create a new Post in the 101 DB's post table
	 * Creates a new session and transaction then saves the post,
	 * commits the transaction, and closes the session
	 * 
	 * @param Post newPost
	 * 
	 * @return Post newPost
	 */
	@Override
	public Post save(Post newPost) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();
		
		log.info("Saving " + newPost + " to the database and getting its new id");
		int id = (int) sess.save(newPost);
		log.trace("The generated post id is: " + id);
		
		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();
		
		log.info("Returning saved Post: " + newPost);
		return newPost;
	}

	/************************************************************************************
	 * Retrieve
	 ************************************************************************************/
	/**
	 * Get a Post from the 101 DB's post table
	 * Creates a new session then gets the post of a given id,
	 * then closes the session
	 * 
	 * @param int id
	 * 
	 * @return Post post
	 */
	@Override
	public Post getById(int id) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Getting post with id: " + id);
		Post post = (Post) sess.get(Post.class, id);
		log.trace("Read post: " + post);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning read post: " + post);
		return post;
	}

	/**
	 * Get a List of all Posts from the 101 DB's post table
	 * Creates a new session then lists all posts,
	 * then closes the session
	 * 
	 * @return List<Post> posts
	 */
	@Override
	public List<Post> getAll() {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Creating a new Criteria for Posts");
		Criteria crit = sess.createCriteria(Post.class);
		
		log.info("Reading a list of posts");
		List<Post> posts = crit.list();
		log.trace("Read liast of posts: " + posts);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning all Posts: " + posts);
		return posts;
	}

	/************************************************************************************
	 * Update
	 ************************************************************************************/
	/**
	 * Updates a post in the 101 DB's post table
	 * Creates a new session and transaction,
	 * updates a post with new values based on the passed post,
	 * commit the transaction and close the session
	 * 
	 * @param Post updatedFC
	 * 
	 * @return Post updatedFC
	 */
	@Override
	public Post update(Post updatedPost) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();

		log.info("Updating post with id " + updatedPost.getPostId() + " to " + updatedPost);
		sess.update(updatedPost);
		log.trace("Updated post " + updatedPost + ", it is now persistent");

		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();
		
		log.info("Returning updated post: " + updatedPost);
		return updatedPost;
	}

	/************************************************************************************
	 * Delete
	 ************************************************************************************/
	/**
	 * Deletes a post from the 101 DB's post table
	 * Creates a new session and transaction,
	 * deletes a post that matches the values of the passed post,
	 * commit the transaction and close the session
	 */
	@Override
	public void delete(Post post) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();

		log.info("Deleting post with id " + post.getPostId());
		sess.delete(post);
		log.trace("Updated post " + post + ", it is now persistent");

		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();	
	}

}
