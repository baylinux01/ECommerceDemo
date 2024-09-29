package baylinux01.eCommerceDemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.eCommerceDemo.entities.Product;
import baylinux01.eCommerceDemo.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
	
	ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("/getAllProductsOfBasketOfCurrentUser")
	public List<Product> getAllProductsOfBasketOfCurrentUser(HttpServletRequest request)
	{
		return productService.getAllProductsOfBasketOfCurrentUser(request);
	}
	
	@PutMapping("/makeProductsStatusesPaidOfBasketOfCurrentUser")
	public String makeProductsStatusesPaidOfBasketOfCurrentUser(HttpServletRequest request)
	{
		return productService.makeProductsStatusesPaidOfBasketOfCurrentUser(request);
	}
	
	
	

}
