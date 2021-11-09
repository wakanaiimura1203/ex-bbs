package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.domain.Article;

@Repository
public class ArticleRepository {
	
	// SQL実行処理のために設定
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * ラムダ式でローマッパーインターフェイスを実装。
	 * 記事とコメントのローマッパーを定義。
	 * リザルトセットから各要素を取得してオブジェクトにセット。
	 * 
	 */
	public static final RowMapper<Article> ARTICLE_ROW_MAPPER =
			new BeanPropertyRowMapper<>(Article.class);
	
	/**
	 * データベースから記事を全件取得。
	 * 取得した内容をarticleListに代入する。
	 * findAll()メソッドが呼ばれた時、articleListを返す
	 */
	public List<Article> findAll(){
		String sql = "SELECT * FROM articles;";
		
		// SQLの実行処理。複数の値を返す。
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articleList;
	} 
	
	
	
	

}
