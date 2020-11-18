package com.vince.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vince.demo.entity.WebSiteEntity;
import com.vince.demo.repository.BlobRepository;
import com.vince.demo.repository.WebSiteRepository;


@Controller
public class PageCheckController {
	
	private static final String PAGE_RETURN = "/pagecheck/pagecheckSearch";
	
	@Autowired
	private WebSiteRepository webSiteRepository;
	@Autowired
	private BlobRepository blobRepository;	
	
	
    @GetMapping({"/pagecheck/search"})
    public String hello(Model model,
                        @RequestParam(value="name", required=false, defaultValue="World") String name) {
    	List<WebSiteEntity> list = (List<WebSiteEntity>) webSiteRepository.findAll();
        model.addAttribute("list", list);        
        return PAGE_RETURN;
    }
    
    
}
