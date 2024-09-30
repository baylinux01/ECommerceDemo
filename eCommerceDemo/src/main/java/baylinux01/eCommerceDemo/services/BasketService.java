package baylinux01.eCommerceDemo.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.eCommerceDemo.entities.AppUser;
import baylinux01.eCommerceDemo.entities.Basket;
import baylinux01.eCommerceDemo.entities.Product;
import baylinux01.eCommerceDemo.entities.ProductStatuses;
import baylinux01.eCommerceDemo.entities.ProductToShow;
import baylinux01.eCommerceDemo.repositories.AppUserRepository;
import baylinux01.eCommerceDemo.repositories.BasketRepository;
import baylinux01.eCommerceDemo.repositories.ProductRepository;
import baylinux01.eCommerceDemo.repositories.ProductToShowRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class BasketService {

	BasketRepository basketRepository;
	ProductRepository productRepository;
	AppUserRepository appUserRepository;
	ProductToShowRepository productToShowRepository;
	
	@Autowired
	public BasketService(
			BasketRepository basketRepository
			,ProductRepository productRepository
			,AppUserRepository appUserRepository
			,ProductToShowRepository productToShowRepository) {
		super();
		this.basketRepository = basketRepository;
		this.productRepository=productRepository;
		this.appUserRepository=appUserRepository;
		this.productToShowRepository=productToShowRepository;
	}
	@Transactional
	public String addProductToBasket(HttpServletRequest request,Long productToShowId) {
		/*jwt olmadan requestten kullanıcı adını alma kodları başlangıcı*/		
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		/*jwt olmadan requestten kullanıcı adını alma kodları sonu*/
		if(username!=null)
		{
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null)
			{
				Basket basket=appUser.getBasket();
				ProductToShow productToShow=productToShowRepository.findById(productToShowId).orElse(null);
				if(productToShow!=null)
				{
					Product product0=productRepository.findByCode(productToShow.getCode());
					if(product0!=null&&product0.getStatus().equals(ProductStatuses.UNPAID)) 
					{
						product0.setNumber(product0.getNumber()+1);
						productRepository.save(product0);
						return "product number increased";
					}
					else
					{
					Product product=new Product();
					product.setBasket(basket);
					product.setName(productToShow.getName());
					product.setCode(productToShow.getCode());
					product.setNumber(1);
					product.setUnit_price(productToShow.getUnit_price());
					product.setImage(productToShow.getImage());
					product.setStatus(ProductStatuses.UNPAID);
					productRepository.save(product);
					List<Product> productsOfBasket=basket.getProducts();
					productsOfBasket.add(product);
					basket.setProducts(productsOfBasket);
					basketRepository.save(basket);
					return "success";
					}
				}
				else
				{
					return "fail";
				}
				
				
			}
			else
			{
				return "fail";
			}
		}
		else
		{
			return "fail";
		}
		
		
	}
	@Transactional
	public String removeProductFromBasket(HttpServletRequest request, Long productId) {
		/*jwt olmadan requestten kullanıcı adını alma kodları başlangıcı*/		
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		/*jwt olmadan requestten kullanıcı adını alma kodları sonu*/
		if(username!=null)
		{
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null)
			{
				Basket basket=appUser.getBasket();
				Product product=productRepository.findById(productId).orElse(null);
				if(product!=null&&product.getStatus().equals(ProductStatuses.UNPAID))
				{
					if(product.getNumber()>1)
					{
						product.setNumber(product.getNumber()-1);
						productRepository.save(product);
						return "product number decreased";
					}
					else
					{
						List<Product> productsOfBasket=basket.getProducts();
						productsOfBasket.remove(product);
						basket.setProducts(productsOfBasket);
						productRepository.delete(product);
						basketRepository.save(basket);
						return "product successfully removed from basket";
					}
					
					
				}
				else
				{
					return "fail";
				}
				
				
			}
			else
			{
				return "fail";
			}
		}
		else
		{
			return "fail";
		}
	}
	
	
}
