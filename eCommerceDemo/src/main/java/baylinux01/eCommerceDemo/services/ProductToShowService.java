package baylinux01.eCommerceDemo.services;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import baylinux01.eCommerceDemo.entities.AppUser;
import baylinux01.eCommerceDemo.entities.ProductToShow;
import baylinux01.eCommerceDemo.repositories.AppUserRepository;
import baylinux01.eCommerceDemo.repositories.ProductToShowRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductToShowService {
	
	ProductToShowRepository productToShowRepository;
	AppUserRepository appUserRepository;

	@Autowired
	public ProductToShowService(
			ProductToShowRepository productToShowRepository
			,AppUserRepository appUserRepository) {
		super();
		this.productToShowRepository = productToShowRepository;
		this.appUserRepository=appUserRepository;
	}

	public String createProductToShow(
			HttpServletRequest request
			,String name
			,MultipartFile multipartFileForImage
			,double price,String code) throws IOException 
	{
		if(name==null || name.equals("")) return "product name cannot be null";
		if(!name.matches("^[öüÖÜĞğşŞçÇıİ|a-z|A-Z|0-9]{2,20}(\\s[öüÖÜĞğşŞçÇıİ|a-z|A-Z|0-9]{2,20}){2,10}$")) 
			return "product name is not suitable to the format";
		if(!code.matches("^[öüÖÜĞğşŞçÇıİ|a-z|A-Z|0-9]{2,20}")) 
			return "product code is not suitable to the format";
		if(price<=0) return "price cannot be negative or zero";
		if(multipartFileForImage==null) return "product image cannot be null";
		if(!multipartFileForImage.getContentType().equals("image/jpeg")
				&&!multipartFileForImage.getContentType().equals("image/png"))
		return "product image format is not suitable";
		
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		
		if(username!=null)
		{
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null && appUser.getRoles().contains("ADMIN"))
			{
				ProductToShow productToShow=new ProductToShow();
				productToShow.setName(name);
				productToShow.setImage(multipartFileForImage.getBytes());
				productToShow.setUnit_price(price);
				productToShow.setCode(code);
				
				productToShowRepository.save(productToShow);
				
				return "productToShow is successfully created";
			}else return "authorized user not found";
		}
		else
			return "authorized user not found";
		
		
	}

	public String updateProductToShow(long productToShowId, HttpServletRequest request, String name,
			MultipartFile multipartFileForImage, double price,String code) throws IOException {
		{
			if(name==null || name.equals("")) return "product name cannot be null";
			if(!name.matches("^[öüÖÜĞğşŞçÇıİ|a-z|A-Z|0-9]{2,20}(\\s[öüÖÜĞğşŞçÇıİ|a-z|A-Z|0-9]{2,20}){2,10}$")) 
				return "product name is not suitable to the format";
			if(!code.matches("^[öüÖÜĞğşŞçÇıİ|a-z|A-Z|0-9]{2,20}")) 
				return "product code is not suitable to the format";
			if(price<=0) return "price cannot be negative or zero";
			if(multipartFileForImage==null) return "product image cannot be null";
			if(!multipartFileForImage.getContentType().equals("image/jpeg")
					&&!multipartFileForImage.getContentType().equals("image/png"))
			return "product image format is not suitable";
			
			Principal pl=request.getUserPrincipal();
			String username=pl.getName();
			
			if(username!=null)
			{
				AppUser appUser=appUserRepository.findByUsername(username);
				if(appUser!=null && appUser.getRoles().contains("ADMIN"))
				{
					
						ProductToShow productToShow=productToShowRepository
								.findById(productToShowId).orElse(null);
						if(productToShow!=null)
						{
							productToShow.setName(name);
							productToShow.setImage(multipartFileForImage.getBytes());
							productToShow.setUnit_price(price);
							if(code!=null)productToShow.setCode(code);
							
							productToShowRepository.save(productToShow);
							return "productToShow is successfully updated";
						}else return "productToShow cannot be found";
								
					
					
					
					
				}else return "authorized user not found";
			}
			else
				return "authorized user not found";
			
			
		}
	}

	public List<ProductToShow> getAllProductsToShow() {
		// TODO Auto-generated method stub
		return productToShowRepository.findAll();
	}
	
	

}
