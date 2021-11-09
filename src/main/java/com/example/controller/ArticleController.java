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
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/bbs")
public class ArticleController {

	/**
	 * 各フォームクラスを使う為の設定。
	 */
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
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
	 
	 /**
	  * 記事を投稿する。
	  * リクエストパラメータをformで受け取り、ドメインにコピー
	  * @param form 記事内容用フォーム
	  * viewで入力し投稿された記事内容をデータベースに格納する。（更新）
	  */
	 @RequestMapping("/post")
	 public String insertArticle(ArticleForm form,Model model) {
		 //ドメインをオブジェクト化
		 Article article = new Article();
		 
		// フォームからドメインにプロパティ値をコピー
		// BeanUtils.copyProperties(articleForm, article);
		
		 article.setName(form.getName());
		 article.setContent(form.getContent());
		 return "bbs";
		}
	
	

}