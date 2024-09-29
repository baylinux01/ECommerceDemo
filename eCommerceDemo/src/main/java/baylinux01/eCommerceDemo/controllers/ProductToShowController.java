package baylinux01.eCommerceDemo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import baylinux01.eCommerceDemo.entities.ProductToShow;
import baylinux01.eCommerceDemo.services.ProductToShowService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/productToShow")
public class ProductToShowController {
	
	ProductToShowService productToShowService;

	@Autowired
	public ProductToShowController(ProductToShowService productToShowService) {
		super();
		this.productToShowService = productToShowService;
	}
	@PostMapping("/createProductToShow")
	public String createProductToShow(
			HttpServletRequest request
			,String name
			,MultipartFile multipartFileForImage
			,double price) throws IOException
	{
		return productToShowService.createProductToShow(request,name,multipartFileForImage,price);
	}
	
	@PutMapping("/updateProductToShow")
	public String updateProductToShow(
			long productToShowId,
			HttpServletRequest request
			,String name
			,MultipartFile multipartFileForImage
			,double price) throws IOException
	{
		return productToShowService.updateProductToShow(productToShowId,request,name,multipartFileForImage,price);
	}
	
	@GetMapping("/getAllProductsToShow")
	public List<ProductToShow> getAllProductsToShow()
	{
		return productToShowService.getAllProductsToShow();
	}
	

}
