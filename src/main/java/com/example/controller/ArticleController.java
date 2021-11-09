package com.example.controller;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/bbs")
public class ArticleController {
	
	// フォームクラスをっ使うための設定
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	/**
	 * 各リポジトリ―を使う為の設定。
	 */
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	/**
	 * トップページを表示するメソッド
	 *
	 */
	@RequestMapping("")
	public String index() {
		return "bbs";
	}
	
	/**
	 * 投稿された記事とコメントをbbsに反映する。
	 */
	@RequestMapping("/Reflect")
	public String reflect(Integer articleId,Model model) {
		List<Article> articleList = articleRepository.findAll();
		List<Comment> commentList = commentRepository.findByArticleId(articleId);
		model.addAttribute("articleList",articleList);
		model.addAttribute("commentList",commentList);
		return "bbs";
	}
	
}
