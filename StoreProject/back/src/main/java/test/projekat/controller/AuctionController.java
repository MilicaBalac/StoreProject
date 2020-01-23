package test.projekat.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import test.projekat.dto.AuctionDTO;
import test.projekat.model.Ad;
import test.projekat.model.Auction;
import test.projekat.service.AdService;
import test.projekat.service.AuctionService;

@RestController
public class AuctionController {

	@Autowired
	AuctionService auctionService;
	
	@Autowired
	AdService adService;
	
	@PostMapping(value = "api/auctions")
	ResponseEntity<AuctionDTO> save (@RequestBody AuctionDTO auctionDTO) {
		
		Auction auction = new Auction();
		
		auction.setId(auctionDTO.getId());
		auction.setUser(auctionDTO.getUser());
		auction.setText(auctionDTO.getText());
		
		
		System.out.println("AD ID: " + auctionDTO.getAdDTO().getId());
		Ad ad = adService.findOne(auctionDTO.getAdDTO().getId());
		
		if (ad == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		List<Auction> adAuctions = auctionService.findByAdId(auctionDTO.getAdDTO().getId());
		double adPrice = auctionDTO.getAdDTO().getPrice();
		
		  if(auctionDTO.getAmount() < adPrice) {
			  return new ResponseEntity<AuctionDTO>(HttpStatus.BAD_REQUEST);
		  }
		  
		  if(!adAuctions.isEmpty()) {
		  if(auctionDTO.getAmount() <  adAuctions.get(adAuctions.size()-1).getAmount()) {
			  return new ResponseEntity<AuctionDTO>(HttpStatus.BAD_REQUEST);
		  }
		  }
		auction.setAmount(auctionDTO.getAmount());
		auction.setAd(ad);
		
		Auction savedAuction = auctionService.save(auction);
		
		return new ResponseEntity<>(new AuctionDTO(savedAuction), HttpStatus.CREATED);
		
	}
	
	@GetMapping(value="api/ads/{id}/auctions")
	ResponseEntity<List<AuctionDTO>> findAll(@PathVariable Long id) {
		System.out.println("ID:: " + id);
		List<AuctionDTO> auction = auctionService.findByAdId(id).stream()
				.map(AuctionDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(auction, HttpStatus.OK);
	}
	
}
