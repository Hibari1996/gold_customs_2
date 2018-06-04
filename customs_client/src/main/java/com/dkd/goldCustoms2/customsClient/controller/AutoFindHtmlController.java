package com.dkd.goldCustoms2.customsClient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AutoFindHtmlController {

    @RequestMapping("static/{html}")
    public ModelAndView autoFindHtml(@PathVariable("html") String html){
        return new ModelAndView(html);
    }

    @RequestMapping("static/{path}/{html}")
    public String autoFindHtml2(@PathVariable("path") String path,
                               @PathVariable("html") String html){
      if (path != null) return path+"/"+html;
        return html;
    }
}
