package org.ds.dsyouth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BrigeCardController {

	@RequestMapping(value = "bridgecard/main", method = RequestMethod.GET)
    public String main() {
        return "bridgecard/main";
    }

    @RequestMapping(value = "bridgecard/discription", method = RequestMethod.GET)
    public String dscription() {
        return "bridgecard/discription";
    }

    @RequestMapping(value = "bridgecard/play", method = RequestMethod.GET)
    public String play() {
        return "bridgecard/play";
    }
	
	
}
