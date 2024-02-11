package SAD.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import web.PullServiceGrpc;

@Configuration
public class GrpcConfiguration {

    @Value("${grpc.local-server.host}")
    private String grpcServerHost;

    @Value("${grpc.local-server.port}")
    private int grpcServerPort;

    @Bean
    public PullServiceGrpc.PullServiceBlockingStub pullServiceBlockingStub(ManagedChannel channel) {
        return PullServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public ManagedChannel channel() {
        return ManagedChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext()
                .build();
    }
}
