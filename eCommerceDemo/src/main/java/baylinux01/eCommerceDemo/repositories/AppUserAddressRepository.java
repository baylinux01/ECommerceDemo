package baylinux01.eCommerceDemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import baylinux01.eCommerceDemo.entities.AppUserAddress;

@Repository
public interface AppUserAddressRepository extends JpaRepository<AppUserAddress, Long>{

	//@Query(value="select * from address a where address_owner",nativeQuery=true)
	List<AppUserAddress> findByOwnerUsername(String username);

}
