package com.example.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/bbs")
public class ArticleController {
	
	// フォームクラスをっ使うための設定
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
	 * 投稿された記事を反映する。
	 *
	 */
	@RequestMapping("/Reflect")
	public String Reflect(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList",articleList);
		return "bbs";
	}
	

	
	
}
