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
import baylinux01.eCommerceDemo.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductService {

	ProductRepository productRepository;
	AppUserRepository appUserRepository;
	@Autowired
	public ProductService(
			ProductRepository productRepository
			,AppUserRepository appUserRepository) {
		super();
		this.productRepository = productRepository;
		this.appUserRepository=appUserRepository;
	}

	public List<Product> getAllProductsOfBasketOfCurrentUser(HttpServletRequest request) {
		/*jwt olmadan requestten kullanıcı adını alma kodları başlangıcı*/		
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		/*jwt olmadan requestten kullanıcı adını alma kodları sonu*/
		if(username!=null)
		{
				AppUser appUser=appUserRepository.findByUsername(username);
				if(appUser!=null)
				{
					List<Product> products=productRepository.findByBasketId(appUser.getBasket().getId());
					return products;
				}
				else
				{
					return null;
				}
				
				
		}
		else
		{
			return null;
		}
		
		
	}

	public String makeProductsStatusesPaidOfBasketOfCurrentUser(HttpServletRequest request) {
		
		/*jwt olmadan requestten kullanıcı adını alma kodları başlangıcı*/		
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		/*jwt olmadan requestten kullanıcı adını alma kodları sonu*/
		if(username!=null)
		{
				AppUser appUser=appUserRepository.findByUsername(username);
				if(appUser!=null)
				{
					List<Product> products=productRepository.findByBasketId(appUser.getBasket().getId());
					double total=0;
					for(Product product: products)
					{
						if(product.getStatus().equals(ProductStatuses.UNPAID))
						total+=product.getNumber()*product.getUnit_price();
					}
					//send virtual pos a request here
					boolean virtual_pos_result=true;
					//end of virtual_pos_request
					if(virtual_pos_result==true)
					{
						for(Product product: products)
						{
							if(product.getStatus().equals(ProductStatuses.UNPAID))
							{
								product.setStatus(ProductStatuses.PAID);
								productRepository.save(product);
							}
							
						}
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
		return "fail";
	}
	
	
}
