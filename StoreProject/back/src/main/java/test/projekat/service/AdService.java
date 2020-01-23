package test.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import test.projekat.model.Ad;
import test.projekat.repository.AdRepository;

@Component
public class AdService {

	@Autowired
	AdRepository adRepo;
	
	public Page<Ad> findAllPages (String title, Pageable page){
		return adRepo.findByTitleContains(title, page);
	}
	
	public List<Ad> findAll() {
		return adRepo.findAll();
	}
	
	public Page<Ad> findAll(Pageable page){
		return adRepo.findAll(page);
	}
	
	public Ad findOne(Long id) {
		return adRepo.findOne(id);
	}
	
	public Ad save (Ad ad) {
		return adRepo.save(ad);
	}
	public void delete (Long id) {
		adRepo.delete(id);
	}
}
