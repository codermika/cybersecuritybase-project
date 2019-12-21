package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address,
    		@RequestParam String ssn, Model model) {
    	// A3:2017 Sensitive Data Exposure
    	// TextEncryptor encryptor = Encryptors.delux("kddskRdls!klslsk", "5c0744940b5c369b");
    	// ssn = encryptor.encrypt(ssn);
        signupRepository.save(new Signup(name, address, ssn));
        
        model.addAttribute("name", name);
        
        return "done";
    }

}
