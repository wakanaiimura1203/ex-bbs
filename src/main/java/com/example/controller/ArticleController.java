package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;


@Controller
@RequestMapping("/bbs")
public class ArticleController {

	/**
	 * 各フォームクラスをrequestスコープに格納
	 * @return フォームオブジェクトがrequestスコープに格納される
	 * 
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
	 * 投稿された記事とコメントが反映される。
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		
		for (Article article : articleList) {
			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList",articleList);

		return "bbs";
	}

	
	 /**
	  * 記事を投稿する。
	  * 
	  * リクエストパラメータを受け取り、リポジトリ―メソッドを呼び出し、値をリポにわたす
	  * ドメインにコピー
	  * @param nameとcontent
	  * viewで入力し投稿された記事内容をデータベースに格納する。（更新）
	  * 
	  */
	 @RequestMapping("/post")
	 public String insertArticle(ArticleForm form) {
		 //ドメインをオブジェクト化
		 Article article = new Article();
		 
		// フォームからドメインに値をコピー
		// ↓教科書に書いてあったやつ。処理は８４，８５行目と同じらしい
		// BeanUtils.copyProperties(articleForm, article);
		
		 article.setName(form.getName());
		 article.setContent(form.getContent());
		 
		 articleRepository.insert(article);
		 
		 return "redirect:/bbs";
		}
	 
	 /**
	  * コメントを投稿する。
	  * 
	  * リクエストパラメータを受け取り、リポジトリ―にわたす
	  * ドメインにコピー
	  * @param nameとcontent
	  * viewで入力し投稿された記事内容をデータベースに格納する。（更新）
	  */
	 @RequestMapping("/post-comment")
	 public String insertComment(CommentForm form,Integer articleId) {
		 System.out.println("qwertyuiop");
		 System.out.println(articleId);
		 // commentドメインをオブジェクト化
		 Comment comment = new Comment();
		 
		 // BeanUtils.copyProperties(commentForm, comment);でも同じ処理が行われる
		 comment.setName(form.getName());
		 comment.setContent(form.getContent());
		 comment.setArticleId(articleId);
		 commentRepository.insert(comment);
		 return "redirect:/bbs";
	 }
	 
	 /**
	  * 記事とコメントを削除する。
	  * 
	  */
	 @RequestMapping("/delete")
	 public String deleteArticle(Integer id) {
		 articleRepository.deleteById(id);
		 commentRepository.deleteByArticleId(id);
		 return "redirect:/bbs";
	 }
}