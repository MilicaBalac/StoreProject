package test.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import test.projekat.model.Auction;
@Component
public interface AuctionRepository extends JpaRepository<Auction, Long> {

	public List<Auction> findByAdId(Long adId);
	
}
