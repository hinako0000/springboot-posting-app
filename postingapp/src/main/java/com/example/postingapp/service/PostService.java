package com.example.postingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.postingapp.entity.Post;
import com.example.postingapp.entity.User;
import com.example.postingapp.form.PostEditForm;
import com.example.postingapp.form.PostRegisterForm;
import com.example.postingapp.repository.PostRepository;



@Service
public class PostService {
	private final PostRepository postRepository;
	
	public PostService(PostRepository postReposiotry) {
		this.postRepository = postReposiotry;
	}
	
	//特定のユーザーに紐づく投稿の一覧を作成日時が新しい順で取得する
	public List<Post> findPostsByUserOrderedByCreatedAtDesc(User user){
		return postRepository.findByUserOrderByCreatedAtDesc(user);
	}
	
	//指定したidを持つ投稿を取得する
	public Optional<Post> findPostById(Integer id){
		return postRepository.findById(id);
	}
	
	//idが最も大きい投稿を取得する
	public Post findFirstPostByOrderByIdDesc() {
		return postRepository.findFirstByOrderByIdDesc();
	}
	
	//特定のユーザーに紐づく投稿の一覧を更新日時が古い順で取得する
	public List<Post> findPostsByUserOrderedByUpdatedAtAsc(User user) {
		return postRepository.findByUserOrderByUpdatedAtAsc(user);
	}
	
	//フォームから送信された投稿をpostsテーブルに追加する
	@Transactional
	public void createPost(PostRegisterForm postRegisterForm, User user) {
		Post post = new Post();
		
		post.setTitle(postRegisterForm.getTitle());
		post.setContent(postRegisterForm.getContent());
		post.setUser(user);
		
		postRepository.save(post);
	}
	
	//フォームから送信された更新内容でpostテーブルを更新する
	@Transactional
	public void updatePost(PostEditForm postEditForm, Post post) {
		post.setTitle(postEditForm.getTitle());
		post.setContent(postEditForm.getContent());
		
		postRepository.save(post);
	}
	
	//postsテーブルのデータを削除する
	@Transactional
	public void deletePost(Post post) {
		postRepository.delete(post);
	}

}
