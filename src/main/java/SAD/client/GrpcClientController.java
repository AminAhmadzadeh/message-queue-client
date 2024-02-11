package SAD.client;

import SAD.client.dto.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.Web;

@RestController
public class GrpcClientController {

    @Autowired
    private GrpcClientService grpcClientService;

    @PostMapping("/push")
    public String push(@RequestBody Record record) {
        var message = Web.Message.newBuilder()
                .setKey(record.getKey())
                .setValue(record.getValue())
                .build();
        return grpcClientService.push(message).getValue();
        //// TODO: 2/11/24 return value must be what?
    }

    @GetMapping("/pull")
    public Record pull() {
        var message = grpcClientService.pull();
        return Record.builder()
                .key(message.getKey())
                .value(message.getValue())
                .build();
    }
}