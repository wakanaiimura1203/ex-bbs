package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


import com.example.domain.Article;

@Repository
public class ArticleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * ラムダ式でローマッパーインターフェイスを実装。
	 * 記事とコメントのローマッパーを定義。
	 * リザルトセットから各要素を取得してオブジェクトにセット。
	 */
	public static final RowMapper<Article> ARTICLE_ROW_MAPPER =
			new BeanPropertyRowMapper<>(Article.class);
	
	/**
	 * 記事を全件取得。
	 * 
	 */
	public List<Article> findAll(){
		String sql = "SELECT * FROM articles;";
		
		// SQLの実行処理。複数の値を返す。
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articleList;
	} 
	
	/**
	 * 記事を投稿するメソッド。
	 * INSERT文で記事を追加。
	 * @param 投稿者名、記事内容
	 * @return 
	 */
	public Article insert(Article article) {
		// Mapをインスタンス化
		SqlParameterSource param = new MapSqlParameterSource();
		String insertSql = "INSERT INTO articles(name,content) VALUES(:name,:content);";
		template.update(insertSql,param);
		return article ;
	}
	
	/**
	 * 主キーを使って１件の投稿を削除するメソッド。
	 * DELETE文で投稿・コメントをすべて削除
	 * @param 記事id
	 */
	public void deleteById(Integer id) {
		String deleteSql = "DELETE FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql,param);
	}
	
	
	
	
	
	

}
