package secondApp.jpa.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import secondApp.jpa.crud.domain.Customer;
import secondApp.jpa.crud.domain.CustomerRepository;


@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listPosts(Model model) {
		
		model.addAttribute("customerList", repository.findAll());
		
		return "customer/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		repository.delete(id);
		return new ModelAndView("redirect:/customer/list");
	}

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String newProject() {
		return "customer/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam("custName") String custName) {
		Customer customer = new Customer();
		customer.setCustName(custName);
		
		repository.save(customer);
		return new ModelAndView("redirect:/customer/list");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	    public ModelAndView update(@RequestParam("post_id") long id,
	                               @RequestParam("custName") String message) {
	        Customer post = repository.findOne(id);
	        post.setCustName(message);
	        repository.save(post);
	        return new ModelAndView("redirect:/customer/list");
	    }

	    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	    public String edit(@PathVariable long id,
	                       Model model) {
	    	Customer post = repository.findOne(id);
	        model.addAttribute("customer", post);
	        return "customer/edit";
	    }
}
