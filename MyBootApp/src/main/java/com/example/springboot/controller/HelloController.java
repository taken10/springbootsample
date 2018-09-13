package com.example.springboot.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot.Entity.UserData;
import com.example.springboot.repository.UserDaterepository;

@RestController
public class HelloController {

	@Autowired
	UserDaterepository repository;

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView Index(@ModelAttribute("formModel")UserData data, ModelAndView mav ) {
		mav.setViewName("index");
		mav.addObject("msg","this is sample");
		mav.addObject("formModel",data);
		Iterable<UserData> list = repository.findAll();
		mav.addObject("datalist",list);
		return mav;
	}

	@RequestMapping(value="/" ,method=RequestMethod.POST)
	@Transactional
	public ModelAndView form(@ModelAttribute("formModel")@Validated UserData data,
			BindingResult result,
			ModelAndView mav) {
		ModelAndView res = null;
		if(!result.hasErrors()) {
			repository.saveAndFlush(data);
			res=new ModelAndView("redirect:/");
		}else {
			mav.setViewName("index");
			mav.addObject("msg","Sorry");
			Iterable<UserData> list= repository.findAll();
			mav.addObject("list",list);
			res=mav;
		}
		return res;
	}
}