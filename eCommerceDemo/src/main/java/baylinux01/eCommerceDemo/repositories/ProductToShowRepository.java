package baylinux01.eCommerceDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baylinux01.eCommerceDemo.entities.ProductToShow;

@Repository
public interface ProductToShowRepository extends JpaRepository<ProductToShow, Long> {

}
