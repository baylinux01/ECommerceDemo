package baylinux01.eCommerceDemo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.eCommerceDemo.entities.AppUserAddress;
import baylinux01.eCommerceDemo.services.AppUserAddressService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/appUserAddress")
public class AppUserAddressController {
	
	AppUserAddressService appUserAddressService;
	
	
	@Autowired
	public AppUserAddressController(AppUserAddressService appUserAddressService) {
		super();
		this.appUserAddressService = appUserAddressService;
	}



	@PostMapping("/createAppUserAddress")
	public String createAppUserAddress(HttpServletRequest request, String addressInfo)
	{
		
		return appUserAddressService.createAppUserAddress(request,addressInfo);
	}
	
	@GetMapping("/getAddressesOfCurrentAppUser")
	public List<AppUserAddress> getAddressesOfCurrentAppUser(HttpServletRequest request)
	{
		return appUserAddressService.getAddressesOfCurrentAppUser(request);
	}
	
	@GetMapping("/getAddressesOfAnotherAppUser")
	public List<AppUserAddress> getAddressesOfAnotherAppUser(HttpServletRequest request,String username)
	{
		return appUserAddressService.getAddressesOfAnotherAppUser(request,username);
	}
	
	@DeleteMapping("/deleteAppUserAddressOfCurrentUser")
	public String deleteAppUserAddressOfCurrentUser(HttpServletRequest request, long addressId)
	{
		return appUserAddressService.deleteAppUserAddressOfCurrentUser(request,addressId);
	}
	
	@PutMapping("/updateAppUserAddressOfCurrentUser")
	public String updateAppUserAddressOfCurrentUser(HttpServletRequest request, long addressId,String newAddressInfo)
	{
		return appUserAddressService.updateAppUserAddressOfCurrentUser(request,addressId,newAddressInfo);
	}

}
