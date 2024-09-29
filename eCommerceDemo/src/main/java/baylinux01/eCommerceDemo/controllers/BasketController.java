package baylinux01.eCommerceDemo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.eCommerceDemo.services.BasketService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/basket")
public class BasketController {

	BasketService basketService;

	public BasketController(BasketService basketService) {
		super();
		this.basketService = basketService;
	}
	
	@PutMapping("/addProductToBasket")
	public String addProductToBasket(HttpServletRequest request, Long productToShowId)
	{
		return basketService.addProductToBasket(request,productToShowId);
	}
	
	@PutMapping("/removeProductFromBasket")
	public String removeProductFromBasket(HttpServletRequest request,Long productId)
	{
		return basketService.removeProductFromBasket(request,productId);
	}
	
	
}
