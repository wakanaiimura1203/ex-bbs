package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 記事とコメントの内容をリポジトリーから受け取り、ビューへ返す。
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 * トップページを表示するメソッド
	 *
	 */
	@RequestMapping("")
	public String index() {
		return "bbs";
	}
	
	/**
	 * 投稿された記事をbbsに反映する。
	 *
	 */
	@RequestMapping("/post")
	public String insertArticle(ArticleForm articleForm) {
		Article article = articleRepository.insert(article);
		model.addAttribute("article",article);
		return "article";
	}

	
	
}
