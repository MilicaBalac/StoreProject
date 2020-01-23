package test.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.projekat.model.Auction;
import test.projekat.repository.AuctionRepository;

@Component
public class AuctionService {

	@Autowired
	AuctionRepository auctionRepo;
	
	public Auction save (Auction auction) {
		return auctionRepo.save(auction);
	}
	
	public List<Auction> findAll() {
		return auctionRepo.findAll();
	}
	
	public List<Auction> findByAdId(Long adId) {
		List <Auction> auction = auctionRepo.findByAdId(adId);
		System.out.println("dobavio iz repositorya");
		return auction;
	}
}
