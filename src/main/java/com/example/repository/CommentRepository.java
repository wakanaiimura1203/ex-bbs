package com.example.repository;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ラムダ式でローマッパーインターフェイスを実装。
	 * リザルトセットから各要素を取得してhotelオブジェクトにセット。
	 * ※この時要素名データベースに合わせる。
	 */
	public static final RowMapper<Comment> COMMENT_ROW_MAPPER =
			new BeanPropertyRowMapper<>(Comment.class);

}
