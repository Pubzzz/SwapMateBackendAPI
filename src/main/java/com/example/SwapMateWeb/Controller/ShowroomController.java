package com.example.SwapMateWeb.Controller;

import com.example.SwapMateWeb.Model.Image;
import com.example.SwapMateWeb.Model.Showroom;
import com.example.SwapMateWeb.Service.ShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController
public class ShowroomController {
    @Autowired
    public ShowroomService showroomService;

    public ShowroomController(ShowroomService showroomService){
        this.showroomService = showroomService;
    }


    @GetMapping("/getShowroomDetails")
    public Showroom getShowroom(@RequestParam String document_id ) throws InterruptedException, ExecutionException{
        return showroomService.getShowroom(document_id);
    }

    @GetMapping("/showroom")
    public List<Showroom> getAllShowroom() throws InterruptedException, ExecutionException{
        return showroomService.getAllShowroom();
    }

    @PostMapping("/showroom")
    public String createShowroom(@RequestBody Showroom showroom ) throws InterruptedException, ExecutionException {
        return showroomService.createShowroom(showroom);
    }

    @PutMapping("/updateShowroom")
    public String updateShowroom(@RequestBody Showroom showroom  ) throws InterruptedException, ExecutionException {
        return showroomService.updateShowroom(showroom);
    }

    @DeleteMapping("/showroom")
    public String deleteShowroom(@RequestParam String document_id){
        return showroomService.deleteShowroom(document_id);
    }

    @PatchMapping("/showroom")
    public String updateImages(@RequestParam String document_id,@RequestBody Image image) throws ExecutionException, InterruptedException {

        return showroomService.updateImages(document_id,image);
    }
}
