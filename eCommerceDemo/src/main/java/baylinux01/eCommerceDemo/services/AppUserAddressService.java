package baylinux01.eCommerceDemo.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.eCommerceDemo.entities.AppUser;
import baylinux01.eCommerceDemo.entities.AppUserAddress;
import baylinux01.eCommerceDemo.repositories.AppUserAddressRepository;
import baylinux01.eCommerceDemo.repositories.AppUserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AppUserAddressService {
	
	AppUserAddressRepository appUserAddressRepository;
	AppUserRepository appUserRepository;

	@Autowired
	public AppUserAddressService(
			AppUserAddressRepository appUserAddressRepository
			,AppUserRepository appUserRepository) {
		super();
		this.appUserAddressRepository = appUserAddressRepository;
		this.appUserRepository=appUserRepository;
	}

	public String createAppUserAddress(HttpServletRequest request, String addressInfo) {
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		if(username!=null)
		{
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null)
			{
				AppUserAddress appUserAddress=new AppUserAddress();
				appUserAddress.setOwner(appUser);
				appUserAddress.setInfo(addressInfo);
				appUserAddressRepository.save(appUserAddress);
				return "success";
			}else return "appUser not found";
			
		}
		else
		{
			return "appUser username not found";
		}
	}

	public List<AppUserAddress> getAddressesOfCurrentAppUser(HttpServletRequest request) {
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		if(username!=null)
		{
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null)
			{
				List<AppUserAddress> addressesOfAppUser=appUserAddressRepository.findByOwnerUsername(username);
				return addressesOfAppUser;
				
			}else 
			{
				return null;
			} 
			
		}
		else
		{
			return null;
		}
	}

	public List<AppUserAddress> getAddressesOfAnotherAppUser(HttpServletRequest request, String username) {
		Principal pl=request.getUserPrincipal();
		String usernameOfRequestingUser=pl.getName();
		
		if(username!=null&& usernameOfRequestingUser!=null)
		{
			AppUser requestingUser=appUserRepository.findByUsername(usernameOfRequestingUser);
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null && requestingUser!=null && requestingUser.getRoles().contains("ADMIN"))
			{
				List<AppUserAddress> addressesOfAppUser=appUserAddressRepository.findByOwnerUsername(username);
				return addressesOfAppUser;
				
			}else 
			{
				return null;
			} 
			
		}
		else
		{
			return null;
		}
	}

	public String deleteAppUserAddressOfCurrentUser(HttpServletRequest request, long addressId) {
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		if(username!=null)
		{
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null)
			{
				AppUserAddress addressOfAppUser=appUserAddressRepository.findById(addressId).orElse(null);
				if(addressOfAppUser!=null&&addressOfAppUser.getOwner().getUsername().equals(username))
				{
					appUserAddressRepository.delete(addressOfAppUser);
					return "address successfuly deleted";
				}
				else return "fail";
			}else 
			{
				return "fail";
			} 
			
		}
		else
		{
			return "fail";
		}
	}

	public String updateAppUserAddressOfCurrentUser(HttpServletRequest request, long addressId, String newAddressInfo) {
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		if(username!=null)
		{
			AppUser appUser=appUserRepository.findByUsername(username);
			if(appUser!=null)
			{
				AppUserAddress addressOfAppUser=appUserAddressRepository.findById(addressId).orElse(null);
				if(addressOfAppUser!=null&&addressOfAppUser.getOwner().getUsername().equals(username))
				{
					addressOfAppUser.setInfo(newAddressInfo);
					appUserAddressRepository.save(addressOfAppUser);
					return "address successfully updated";
				}
				else return "fail";
			}else 
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
