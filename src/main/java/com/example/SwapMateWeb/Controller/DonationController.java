package com.example.SwapMateWeb.Controller;
        import com.example.SwapMateWeb.Service.DonationService;
        import com.example.SwapMateWeb.Model.Donation;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.concurrent.ExecutionException;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController
public class DonationController {
    @Autowired
    public DonationService donationService;

    public DonationController(DonationService donationService){
        this.donationService = donationService;
    }


    @GetMapping("/getDonationDetails")
    public Donation getDonation(@RequestParam String document_id ) throws InterruptedException, ExecutionException {
        return donationService.getDonation(document_id);
    }

    @GetMapping("/donation")
    public List<Donation> getAllDonation() throws InterruptedException, ExecutionException{
        return donationService.getAllDonation();
    }

    @PostMapping("/donation")
    public String createDonation(@RequestBody Donation donation ) throws InterruptedException, ExecutionException {
        return donationService.createDonation(donation);
    }

    @PutMapping("/updateDonation")
    public String updateDonation(@RequestBody Donation donation  ) throws InterruptedException, ExecutionException {
        return donationService.updateDonation(donation);
    }

    @DeleteMapping("/donation")
    public String deleteDonation(@RequestParam String document_id){
        return donationService.deleteDonation(document_id);
    }
}
