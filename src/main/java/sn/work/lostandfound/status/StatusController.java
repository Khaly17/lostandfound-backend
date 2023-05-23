package sn.work.lostandfound.status;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/find")
public class StatusController {
    private final StatusServiceImpl statusService;
    public StatusController(
            StatusServiceImpl statusService
    ){
        this.statusService = statusService;
    }

    @GetMapping("/status/all")
    public List<StatusDto> findStatusAll(){
        return this.statusService.findAllStatus();
    }

    @PostMapping("/status/add")
    public StatusDto addStatus(@RequestBody StatusDto statusDto){
        return  statusService.addStatus(statusDto);
    }

    @DeleteMapping("/status/delete/{id}")
    public void deleteStatus(@PathVariable("id") Long id){
        statusService.deleteStatus(id);
    }

    @PutMapping("/status/update/{id}")
    public StatusDto updateStatus(@PathVariable("id") Long id, @RequestBody StatusDto statusDto){
        return statusService.updateStatus(id,statusDto);
    }
}
