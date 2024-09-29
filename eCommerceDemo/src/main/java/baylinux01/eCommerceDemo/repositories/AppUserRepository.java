package baylinux01.eCommerceDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import baylinux01.eCommerceDemo.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long>{

	AppUser findByUsername(String username);
	
	

}
