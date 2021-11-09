package com.example.repository;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

	// SQLの実行処理をするために必要
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ラムダ式でローマッパーインターフェイスを実装。 リザルトセットから各要素を取得してcommentオブジェクト（ドメイン）にセット。
	 * ※この時要素名データベースに合わせる。
	 */
	public static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);

	/**
	 * 記事idからコメントを取得。
	 * findByArticleIdメソッドが呼ばれたときにcommentListを返す。
	 * @param 取得したい記事id
	 * @return 各idに対応するコメント
	 * 
	 */
	public List<Comment> findByArticleId(int articleId){
		String sql = "SELECT * FROM comments WHERE article_id=:article_id ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("article_id",articleId);
		
		List<Comment> commentList = template.query(sql,param,COMMENT_ROW_MAPPER);
		return commentList;
	}


}
