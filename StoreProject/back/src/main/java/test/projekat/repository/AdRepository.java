package test.projekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import test.projekat.model.Ad;

@Component
public interface AdRepository extends JpaRepository<Ad, Long> {

	public Page<Ad> findByTitleContains(String title, Pageable page);
	
}
