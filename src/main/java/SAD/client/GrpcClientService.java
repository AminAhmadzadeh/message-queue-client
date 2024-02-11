package SAD.client;

import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.PullServiceGrpc;
import web.Web;

@Slf4j
@Service
public class GrpcClientService {

    private final PullServiceGrpc.PullServiceBlockingStub blockingStub;

    public GrpcClientService(PullServiceGrpc.PullServiceBlockingStub blockingStub) {
        this.blockingStub = blockingStub;
    }

    public Web.Message pull() {
        try {
            return this.blockingStub.pull(Web.PullRequest.newBuilder().build());
        } catch (final StatusRuntimeException e) {
            log.error("Pull request failed with error: {}", e.getStatus().getCode().name());
        }
        return null;
    }

    public Web.PushResponse push(Web.Message message) {
        try {
            return this.blockingStub.push(message);
        } catch (final StatusRuntimeException e) {
            log.error("Push request failed with error: {}", e.getStatus().getCode().name());
        }
        return null;
    }
}