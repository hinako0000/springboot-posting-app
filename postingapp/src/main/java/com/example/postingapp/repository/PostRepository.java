package com.example.postingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postingapp.entity.Post;
import com.example.postingapp.entity.User;

public interface PostRepository extends JpaRepository<Post,Integer>{
	//作成日時が新しい順に取得
	public List<Post> findByUserOrderByCreatedAtDesc(User user);
	
	//更新日時が古い順に取得
	public List<Post> findByUserOrderByUpdatedAtAsc(User user);
	
	//idが最も大きい投稿を取得する
	public Post findFirstByOrderByIdDesc();

}
